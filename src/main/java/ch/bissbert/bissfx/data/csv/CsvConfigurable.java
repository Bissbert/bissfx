package ch.bissbert.bissfx.data.csv;

/**
 * A class implementing this interface can be configured with a {@link CsvConfig}.
 *
 * @param <T> the type of object the configuration is for
 */
public interface CsvConfigurable<T> {
    /**
     * Returns the configuration.
     *
     * @return the configuration
     */
    CsvConfig<T> getCsvConfig();
}
