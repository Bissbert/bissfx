package ch.bissbert.bissfx.data.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public final class ObjectMappers {
    public static final ObjectMapper<String> STRING = new LambdaMapper<>(s -> s);
    public static final ObjectMapper<Integer> INTEGER = new LambdaMapper<>(Integer::parseInt);
    public static final ObjectMapper<Double> DOUBLE = new LambdaMapper<>(Double::parseDouble);
    public static final ObjectMapper<Boolean> BOOLEAN = new LambdaMapper<>(Boolean::parseBoolean);
    public static final ObjectMapper<Long> LONG = new LambdaMapper<>(Long::parseLong);
    public static final ObjectMapper<Float> FLOAT = new LambdaMapper<>(Float::parseFloat);
    public static final ObjectMapper<Short> SHORT = new LambdaMapper<>(Short::parseShort);
    public static final ObjectMapper<Byte> BYTE = new LambdaMapper<>(Byte::parseByte);
    public static final ObjectMapper<LocalDate> LOCAL_DATE = new LambdaMapper<>(LocalDate::parse);
    public static final ObjectMapper<LocalDateTime> LOCAL_DATE_TIME = new LambdaMapper<>(LocalDateTime::parse);

    private ObjectMappers() {
    }

    public static class Providers {
        private Providers() {
        }

        public static final ObjectMapperProvider<ObjectMapper<String>> STRING = ObjectMappers.STRING.provider();
        public static final ObjectMapperProvider<ObjectMapper<Integer>> INTEGER = ObjectMappers.INTEGER.provider();
        public static final ObjectMapperProvider<ObjectMapper<Double>> DOUBLE = ObjectMappers.DOUBLE.provider();
        public static final ObjectMapperProvider<ObjectMapper<Boolean>> BOOLEAN = ObjectMappers.BOOLEAN.provider();
        public static final ObjectMapperProvider<ObjectMapper<Long>> LONG = ObjectMappers.LONG.provider();
        public static final ObjectMapperProvider<ObjectMapper<Float>> FLOAT = ObjectMappers.FLOAT.provider();
        public static final ObjectMapperProvider<ObjectMapper<Short>> SHORT = ObjectMappers.SHORT.provider();
        public static final ObjectMapperProvider<ObjectMapper<Byte>> BYTE = ObjectMappers.BYTE.provider();
        public static final ObjectMapperProvider<ObjectMapper<LocalDate>> LOCAL_DATE = ObjectMappers.LOCAL_DATE.provider();
        public static final ObjectMapperProvider<ObjectMapper<LocalDateTime>> LOCAL_DATE_TIME = ObjectMappers.LOCAL_DATE_TIME.provider();
    }
}
