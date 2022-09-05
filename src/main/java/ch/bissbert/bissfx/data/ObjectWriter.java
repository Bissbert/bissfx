package ch.bissbert.bissfx.data;

/**
 * A class to write an object to a string.
 *
 * @param <T> the type of the object to write
 * @author Bissbert
 */
public interface ObjectWriter<T> {
    /**
     * Returns a string representation of the object passed as argument.
     *
     * @param value the object to write
     * @return the string representation of the object
     */
    String write(T value);
}
