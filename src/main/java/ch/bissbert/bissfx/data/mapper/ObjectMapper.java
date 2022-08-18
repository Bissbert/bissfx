package ch.bissbert.bissfx.data.mapper;

/**
 * An object mapper is a function that maps a string to an object.
 *
 * @param <T>
 */
@FunctionalInterface
public interface ObjectMapper<T> {
    default ObjectMapperProvider<ObjectMapper<T>> provider() {
        return new ObjectMapperProvider<>(this);
    }

    T map(String value);
}
