package ch.bissbert.bissfx.data;

public interface ObjectReader<T> {
    T read(String value);
}
