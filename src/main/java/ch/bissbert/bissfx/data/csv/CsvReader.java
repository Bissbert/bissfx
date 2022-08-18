package ch.bissbert.bissfx.data.csv;

import ch.bissbert.bissfx.data.ObjectReader;
import ch.bissbert.bissfx.data.mapper.ObjectMapper;
import ch.bissbert.bissfx.data.mapper.ObjectMapperProvider;
import ch.bissbert.bissfx.data.mapper.ObjectMappers;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Reads a single line of csv and maps it to an object.
 *
 * @param <T> the type of the object to map to
 * @author bissbert
 * @version 1.0.0
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public final class CsvReader<T> implements ObjectReader<T> {

    private static final Map<Class<?>, ObjectMapperProvider<?>> mappers = new HashMap<>();

    static {
        //register all mappers from ObjectMappers.class
        registerMapper(String.class, ObjectMappers.Providers.STRING);
        registerMapper(Integer.class, ObjectMappers.Providers.INTEGER);
        registerMapper(int.class, ObjectMappers.Providers.INTEGER);
        registerMapper(Double.class, ObjectMappers.Providers.DOUBLE);
        registerMapper(double.class, ObjectMappers.Providers.DOUBLE);
        registerMapper(Boolean.class, ObjectMappers.Providers.BOOLEAN);
        registerMapper(boolean.class, ObjectMappers.Providers.BOOLEAN);
        registerMapper(Long.class, ObjectMappers.Providers.LONG);
        registerMapper(long.class, ObjectMappers.Providers.LONG);
        registerMapper(Float.class, ObjectMappers.Providers.FLOAT);
        registerMapper(float.class, ObjectMappers.Providers.FLOAT);
        registerMapper(Short.class, ObjectMappers.Providers.SHORT);
        registerMapper(short.class, ObjectMappers.Providers.SHORT);
        registerMapper(Byte.class, ObjectMappers.Providers.BYTE);
        registerMapper(byte.class, ObjectMappers.Providers.BYTE);
        registerMapper(LocalDate.class, ObjectMappers.Providers.LOCAL_DATE);
        registerMapper(LocalDateTime.class, ObjectMappers.Providers.LOCAL_DATE_TIME);
    }

    /**
     * fetches the mapper for the given class
     * <p>
     * returns null if no mapper is available
     * </br>
     * mappers for classes other than primitives have to be registered manually using {@link #registerMapper(Class, ObjectMapperProvider)}
     *
     * @param clazz the class to get the mapper for
     * @param <T>   the class to get the mapper for
     * @return the mapper for the given class
     */
    public static <T> ObjectMapperProvider<ObjectMapper<T>> getMapper(Class<T> clazz) {
        return (ObjectMapperProvider<ObjectMapper<T>>) mappers.get(clazz);
    }

    /**
     * registers a mapper for a class
     * <p>
     * mappers for classes other than primitives have to be registered manually using this method.<br/>
     * if a mapper for the given class is already registered, it will be overwritten
     *
     * @param clazz  the class to register the mapper for
     * @param mapper the mapper to register
     * @param <T>    the class to register the mapper for
     */
    public static <T> void registerMapper(Class<T> clazz, ObjectMapperProvider<ObjectMapper<T>> mapper) {
        mappers.put(clazz, mapper);
    }

    private CsvConfig<T> config;

    /**
     * reads a single line of csv and maps it to an object
     *
     * @param value the line to map
     * @return the mapped object
     */
    @Override
    public T read(String value) {
        String[] values = split(value);
        return mapToObject(values);
    }

    /**
     * maps a string to a list of objects by splitting lines at the configured separator
     *
     * @param value the string to split and map
     * @return the list of mapped objects
     */
    public List<T> readAll(String value) {
        List<T> result = new ArrayList<>();
        for (String line : value.split(Pattern.quote(config.getEscape()))) {
            result.add(read(line));
        }
        return result;
    }

    /**
     * maps a string array to an object
     *
     * @param values the string array to map
     * @return the mapped object
     */
    private T mapToObject(String[] values) {
        final Field[] fieldsToMap = findFieldsToMap(config.getClazz());
        if (config.isFindByHeaders()) {
            if (config.getHeaders() == null) {
                throw new IllegalStateException("Headers are not set but findByHeaders is true in config");
            }
            return mapToObjectByName(values, fieldsToMap);
        }
        return mapToObjectByIndex(values, fieldsToMap);
    }

    /**
     * maps an array of values to their fields using the headers defined in the config and annotated with {@link CsvValue}
     *
     * @param values      the values to map
     * @param fieldsToMap the fields to map to
     * @return the mapped object
     */
    private T mapToObjectByName(String[] values, final Field[] fieldsToMap) {
        try {
            T instance = config.getClazz().getDeclaredConstructor().newInstance();
            List<String> headerList = Arrays.asList(config.getHeaders());
            for (final Field field : fieldsToMap) {
                final String value = values[headerList.indexOf(field.getAnnotation(CsvValue.class).name())];
                field.setAccessible(true);
                field.set(instance, map(value, field.getType()));
            }
            return instance;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * maps an array of values to their fields using the index defined in the annotation
     *
     * @param values      the values to map
     * @param fieldsToMap the fields to map to
     * @return the mapped object
     */
    private T mapToObjectByIndex(String[] values, final Field[] fieldsToMap) {
        try {
            T instance = config.getClazz().getDeclaredConstructor().newInstance();
            for (final Field field : fieldsToMap) {
                final String value = values[field.getAnnotation(CsvValue.class).index()];
                field.setAccessible(true);
                field.set(instance, map(value, field.getType()));
            }
            return instance;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * finds all fields that are configured to be mapped from csv
     *
     * @param clazz the class to find the fields for
     * @return the fields that are configured to be mapped from csv
     */
    private Field[] findFieldsToMap(Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        ArrayList<Field> fieldsToMap = new ArrayList<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(CsvValue.class)) {
                fieldsToMap.add(field);
            }
        }
        return fieldsToMap.toArray(new Field[0]);
    }

    /**
     * splits a csv line into its values using the configured separator
     *
     * @param value the line to split
     * @return the values of the line
     */
    private String[] split(String value) {
        return value.split(Pattern.quote(config.getSeparator()));
    }

    /**
     * maps a single value to an object using the configured mapper
     *
     * @param value the value to map
     * @param clazz the class to map to
     * @param <M>   the class to map to
     * @return the mapped object
     */
    private <M> M map(String value, Class<M> clazz) {
        if (clazz.equals(String.class)) {
            value = removeEscape(value);
        }
        return getMapper(clazz).getData().map(value);
    }

    private String removeEscape(String value) {
        return reverse(reverse(value.replaceFirst(config.getQuote(), "")).replaceFirst(config.getQuote(), ""));
    }

    private String reverse(String value) {
        return new StringBuilder(value).reverse().toString();
    }
}
