package ch.bissbert.bissfx.data.csv.mapper;

import ch.bissbert.bissfx.data.csv.CsvConfig;
import ch.bissbert.bissfx.data.csv.CsvConfigurable;
import ch.bissbert.bissfx.data.csv.CsvReader;
import ch.bissbert.bissfx.data.csv.CsvWriter;
import ch.bissbert.bissfx.data.mapper.ObjectMapper;

import java.util.List;

/**
 * A class usable to map a list of objects to a csv string and vice versa.
 *
 * @param <T> the type of the objects to map
 * @author Bissbert
 */
public class CsvListObjectMapper<T> implements ObjectMapper<List<T>>, CsvConfigurable<T> {
    private final CsvConfig<T> config;
    private final CsvReader<T> reader;
    private final CsvWriter<T> writer;

    public CsvListObjectMapper(CsvConfig<T> config) {
        this.config = config;
        this.reader = new CsvReader<>(config);
        this.writer = new CsvWriter<>(config);
    }

    @Override
    public List<T> map(String value) {
        return reader.readAll(value);
    }

    @Override
    public String unmap(List<T> value) {
        return writer.writeMultiple(value);
    }

    @Override
    public CsvConfig<T> getCsvConfig() {
        return this.config;
    }
}
