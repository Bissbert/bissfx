package ch.bissbert.bissfx.data.csv;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(builderMethodName = "hiddenBuilder")
@Getter
@Setter
public class CsvConfig<T> {
    @Builder.Default
    private String separator = ";";
    @Builder.Default
    private String quote = "\"";
    @Builder.Default
    private String escape = "\n";
    private String[] headers;
    private Class<T> clazz;


    public static <T> CsvConfigBuilder<T> builder(Class<T> clazz) {
        return ((CsvConfigBuilder<T>) hiddenBuilder()).clazz(clazz);
    }

}
