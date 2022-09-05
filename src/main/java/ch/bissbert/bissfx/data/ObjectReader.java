package ch.bissbert.bissfx.data;

/**
 * A class to read an object from a string.
 * @param <T> the type of the object to read
 */
public interface ObjectReader<T> {
    /**
     * Returns and instance of this class generated from a string passed as argument.
     * @param value the string to parse
     * @return the parsed object
     */
    T read(String value);
}
