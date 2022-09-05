package ch.bissbert.bissfx.data.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * A class that contains some default {@link ObjectMapper}s.
 * <p>
 * The following mappers are available:
 * <ul>
 *     <li>String</li>
 *     <li>Integer</li>
 *     <li>Long</li>
 *     <li>Short</li>
 *     <li>Byte</li>
 *     <li>Double</li>
 *     <li>Float</li>
 *     <li>Boolean</li>
 *     <li>LocalDate</li>
 *     <li>LocalDateTime</li>
 * </ul>
 * These mappers can also be accessed using the {@link ObjectMapperProvider}s in {@link ObjectMappers.Providers}.
 * <p>
 *
 * @author Bissbert
 */
public final class ObjectMappers {
    public static final ObjectMapper<String> STRING = new ObjectMapper<>() {
        @Override
        public String map(String value) {
            return value;
        }

        @Override
        public String unmap(String value) {
            return value;
        }
    };
    public static final ObjectMapper<Integer> INTEGER = new ObjectMapper<>() {
        @Override
        public Integer map(String value) {
            return Integer.parseInt(value);
        }

        @Override
        public String unmap(Integer value) {
            return value.toString();
        }
    };
    public static final ObjectMapper<Double> DOUBLE = new ObjectMapper<>() {
        @Override
        public Double map(String value) {
            return Double.parseDouble(value);
        }

        @Override
        public String unmap(Double value) {
            return value.toString();
        }
    };
    public static final ObjectMapper<Boolean> BOOLEAN = new ObjectMapper<>() {
        @Override
        public Boolean map(String value) {
            return Boolean.parseBoolean(value);
        }

        @Override
        public String unmap(Boolean value) {
            return value.toString();
        }
    };
    public static final ObjectMapper<Long> LONG = new ObjectMapper<>() {
        @Override
        public Long map(String value) {
            return Long.parseLong(value);
        }

        @Override
        public String unmap(Long value) {
            return value.toString();
        }
    };
    public static final ObjectMapper<Float> FLOAT = new ObjectMapper<>() {
        @Override
        public Float map(String value) {
            return Float.parseFloat(value);
        }

        @Override
        public String unmap(Float value) {
            return value.toString();
        }
    };
    public static final ObjectMapper<Short> SHORT = new ObjectMapper<>() {
        @Override
        public Short map(String value) {
            return Short.parseShort(value);
        }

        @Override
        public String unmap(Short value) {
            return value.toString();
        }
    };
    public static final ObjectMapper<Byte> BYTE = new ObjectMapper<>() {
        @Override
        public Byte map(String value) {
            return Byte.parseByte(value);
        }

        @Override
        public String unmap(Byte value) {
            return value.toString();
        }
    };
    public static final ObjectMapper<LocalDate> LOCAL_DATE = new ObjectMapper<>() {
        @Override
        public LocalDate map(String value) {
            return LocalDate.parse(value);
        }

        @Override
        public String unmap(LocalDate value) {
            return value.toString();
        }
    };
    public static final ObjectMapper<LocalDateTime> LOCAL_DATE_TIME = new ObjectMapper<>() {
        @Override
        public LocalDateTime map(String value) {
            return LocalDateTime.parse(value);
        }

        @Override
        public String unmap(LocalDateTime value) {
            return value.toString();
        }
    };

    private ObjectMappers() {
    }

    /**
     * A class that contains {@link ObjectMapperProvider}s for the default {@link ObjectMapper}s.
     */
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
