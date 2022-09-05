package ch.bissbert.bissfx.data.provider;

/**
 * A class to provide data.
 *
 * @param <T> the type of the data
 * @author Bissbert
 */
public interface BissDataProvider<T> {
    /**
     * Returns the data provided by this class.
     *
     * @return the data
     */
    T getData();
}
