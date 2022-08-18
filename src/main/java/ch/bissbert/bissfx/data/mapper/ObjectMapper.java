package ch.bissbert.bissfx.data.mapper;

public interface ObjectMapper<T> {
    ObjectMapperProvider<ObjectMapper<T>> provider();
    T map(String value);
}
