package ch.bissbert.bissfx.data.csv;

import ch.bissbert.bissfx.data.ObjectWriter;
import ch.bissbert.bissfx.data.mapper.ObjectMapper;

import java.lang.reflect.Field;
import java.util.*;

/**
 * A CSV writer.
 *
 * @param <T> the type of the objects to write
 * @author bissbert
 * @version 1.0.0
 * @since 1.0.0
 */
public class CsvWriter<T> extends CsvMapper implements CsvConfigurable<T>, ObjectWriter<T> {
    private final CsvConfig<T> config;

    public CsvWriter(CsvConfig<T> config) {
        this.config = config;
    }

    @Override
    public CsvConfig<T> getCsvConfig() {
        return this.config;
    }

    /**
     * Writes a single object to a CSV string.
     *
     * @param t the object to write
     * @return the CSV string
     */
    public String write(T t) {
        if (getCsvConfig().isFindByHeaders() && getCsvConfig().getHeaders() != null) {
            return writeByHeaders(t);
        }
        return writeByIndex(t);

    }

    /**
     * Writes a list of objects to a CSV string.
     *
     * @param ts the list of objects to write
     * @return the CSV string
     */
    public String writeMultiple(List<T> ts) {
        StringJoiner joiner = new StringJoiner(getCsvConfig().getEscape());
        for (T t : ts) {
            joiner.add(write(t));
        }
        return joiner.toString();
    }

    private String writeByIndex(T t) {
        List<Field> annotatedFields = getAnnotatedFields(getCsvConfig().getClazz());

        Field[] indexSortedFields = new Field[
                annotatedFields
                        .stream()
                        .mapToInt(field -> field.getAnnotation(CsvValue.class).index())
                        .max().getAsInt() + 1
                ];
        annotatedFields.forEach(field -> {
            CsvValue annotation = field.getAnnotation(CsvValue.class);
            indexSortedFields[annotation.index()] = field;
        });

        return writeFromFieldArray(t, indexSortedFields);
    }

    private String writeByHeaders(T t) {
        List<Field> annotatedFields = getAnnotatedFields(getCsvConfig().getClazz());

        Field[] headerSortedFields = new Field[getCsvConfig().getHeaders().length];
        List<String> headers = Arrays.asList(getCsvConfig().getHeaders());
        annotatedFields.forEach(field -> {
            CsvValue annotation = field.getAnnotation(CsvValue.class);
            if (headers.contains(annotation.name())) {
                headerSortedFields[headers.indexOf(annotation.name())] = field;
            }
        });

        return writeFromFieldArray(t, headerSortedFields);
    }

    private List<Field> getAnnotatedFields(Class<?> aClass) {
        Field[] fields = aClass.getDeclaredFields();
        return Arrays.stream(fields)
                .filter(field -> field.isAnnotationPresent(CsvValue.class))
                .toList();
    }

    private String writeFromFieldArray(T t, Field[] fields) {
        StringJoiner joiner = new StringJoiner(getCsvConfig().getSeparator());
        for (Field field : fields) {
            if (field != null) {
                setFieldValueInJoiner(t, joiner, field);
            } else {
                joiner.add("");
            }
        }
        return joiner.toString();
    }

    private void setFieldValueInJoiner(T t, StringJoiner joiner, Field field) {
        try {
            field.setAccessible(true);
            ObjectMapper mapper = getMapperProvider(field.getType()).getData();
            if (field.getType().equals(String.class)) {
                joiner.add(config.getQuote() + mapper.write(field.get(t)) + config.getQuote());
            } else {
                joiner.add(mapper.write(field.get(t)));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
