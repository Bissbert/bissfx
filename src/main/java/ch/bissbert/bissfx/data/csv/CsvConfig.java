package ch.bissbert.bissfx.data.csv;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(builderMethodName = "hiddenBuilder")
@Getter
@Setter
/**
 * Configuration for {@link CsvReader} and {@link CsvWriter}.
 * @author bissbert
 * @since 1.0.0
 * @version 1.0.0
 * @see CsvReader
 * @see CsvWriter
 */
public class CsvConfig<T> {
    @Builder.Default
    private String separator = ";";
    @Builder.Default
    private String quote = "\"";
    @Builder.Default
    private String escape = "\n";
    @Builder.Default
    private boolean findByHeaders = false;
    private String[] headers;
    private Class<T> clazz;


    public static <T> CsvConfigBuilder<T> builder(Class<T> clazz) {
        return ((CsvConfigBuilder<T>) hiddenBuilder()).clazz(clazz);
    }

}
