package ch.bissbert.bissfx.data.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

public final class ObjectMappers {
    public static final ObjectMapper<String> STRING = s -> s;
    public static final ObjectMapper<Integer> INTEGER = Integer::parseInt;
    public static final ObjectMapper<Double> DOUBLE = Double::parseDouble;
    public static final ObjectMapper<Boolean> BOOLEAN = Boolean::parseBoolean;
    public static final ObjectMapper<Long> LONG = Long::parseLong;
    public static final ObjectMapper<Float> FLOAT = Float::parseFloat;
    public static final ObjectMapper<Short> SHORT = Short::parseShort;
    public static final ObjectMapper<Byte> BYTE = Byte::parseByte;
    public static final ObjectMapper<LocalDate> LOCAL_DATE = LocalDate::parse;
    public static final ObjectMapper<LocalDateTime> LOCAL_DATE_TIME = LocalDateTime::parse;

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
