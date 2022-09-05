package ch.bissbert.bissfx.data.mapper;

import ch.bissbert.bissfx.data.ObjectReader;
import ch.bissbert.bissfx.data.ObjectWriter;

/**
 * An object mapper is a function that maps a string to an object.
 *
 * @param <T> the type of the object to map
 * @author Bissbert
 */
public interface ObjectMapper<T> extends ObjectReader<T>, ObjectWriter<T> {
    default ObjectMapperProvider<ObjectMapper<T>> provider() {
        return new ObjectMapperProvider<>(this);
    }

    /**
     * Returns and instance of this class generated from a string passed as argument.
     *
     * @param value the string to parse
     * @return the parsed object
     */
    default T read(String value) {
        return map(value);
    }

    /**
     * Returns a string representation of an object.
     *
     * @param value the object to write
     * @return the string representation
     */
    default String write(T value) {
        return unmap(value);
    }

    /**
     * Maps a string to an object.
     *
     * @param value the string to map
     * @return the mapped object
     */
    T map(String value);

    /**
     * Maps an object to a string.
     *
     * @param value the object to map
     * @return the mapped string
     */
    String unmap(T value);
}
