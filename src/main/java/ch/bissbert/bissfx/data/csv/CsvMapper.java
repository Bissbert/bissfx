package ch.bissbert.bissfx.data.csv;

import ch.bissbert.bissfx.data.mapper.ObjectMapper;
import ch.bissbert.bissfx.data.mapper.ObjectMapperProvider;
import ch.bissbert.bissfx.data.mapper.ObjectMappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * A class implementing this abstract class can access a map of {@link ObjectMapper}s for different types.
 * The map contains the default mappers for the following types:
 * <ul>
 *     <li>{@link String}</li>
 *     <li>{@link Integer}</li>
 *     <li>{@link Long}</li>
 *     <li>{@link Short}</li>
 *     <li>{@link Byte}</li>
 *     <li>{@link Double}</li>
 *     <li>{@link Float}</li>
 *     <li>{@link Boolean}</li>
 *     <li>{@link LocalDate}</li>
 *     <li>{@link LocalDateTime}</li>
 * </ul>
 *
 * @author Bissbert
 */
public abstract class CsvMapper {
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
     * fetches the mapper provider for the given class
     * <p>
     * returns null if no mapper is available
     * </br>
     * mappers for classes other than primitives have to be registered manually using {@link #registerMapper(Class, ObjectMapperProvider)}
     *
     * @param clazz the class to get the mapper for
     * @param <T>   the class to get the mapper for
     * @return the mapper for the given class
     */
    public static <T> ObjectMapperProvider<ObjectMapper<T>> getMapperProvider(Class<T> clazz) {
        return (ObjectMapperProvider<ObjectMapper<T>>) mappers.get(clazz);
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
    public static <T> ObjectMapper<T> getMapper(Class<T> clazz) {
        return ((ObjectMapperProvider<ObjectMapper<T>>) mappers.get(clazz)).getData();
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
}
