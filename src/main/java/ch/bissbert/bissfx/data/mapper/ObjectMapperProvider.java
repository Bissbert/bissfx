package ch.bissbert.bissfx.data.mapper;

import ch.bissbert.bissfx.data.provider.BissDataProvider;

public class ObjectMapperProvider<T extends ObjectMapper<?>> implements BissDataProvider<T> {
    private final T mapper;

    public ObjectMapperProvider(T mapper) {
        this.mapper = mapper;
    }

    @Override
    public T getData() {
        return mapper;
    }
}
