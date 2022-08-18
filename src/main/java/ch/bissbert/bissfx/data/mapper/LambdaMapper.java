package ch.bissbert.bissfx.data.mapper;

import java.util.function.Function;

public class LambdaMapper<T> implements ObjectMapper<T> {
    private final ObjectMapperProvider<ObjectMapper<T>> provider = new ObjectMapperProvider<>(this);
    private final Function<String, T> mapper;

    public LambdaMapper(Function<String, T> function) {
        this.mapper = function;
    }

    @Override
    public ObjectMapperProvider<ObjectMapper<T>> provider() {
        return provider;
    }

    @Override
    public T map(String value) {
        return mapper.apply(value);
    }
}
