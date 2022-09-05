package ch.bissbert.bissfx.data.csv.mapper;

import ch.bissbert.bissfx.data.csv.CsvConfig;
import ch.bissbert.bissfx.data.csv.CsvConfigurable;
import ch.bissbert.bissfx.data.csv.CsvReader;
import ch.bissbert.bissfx.data.csv.CsvWriter;
import ch.bissbert.bissfx.data.mapper.ObjectMapper;

/**
 * A class usable to map a single object to a csv string and vice versa.
 *
 * @param <T> the type of the object to map
 * @author Bissbert
 */
public class CsvObjectMapper<T> implements ObjectMapper<T>, CsvConfigurable<T> {


    private final CsvConfig<T> config;
    private final CsvReader<T> reader;
    private final CsvWriter<T> writer;

    public CsvObjectMapper(CsvConfig<T> config) {
        this.config = config;
        this.reader = new CsvReader<T>(config);
        this.writer = new CsvWriter<T>(config);
    }

    @Override
    public T map(String value) {
        return reader.read(value);
    }

    @Override
    public String unmap(T value) {
        return writer.write(value);
    }

    @Override
    public CsvConfig<T> getCsvConfig() {
        return this.config;
    }
}
