package ch.bissbert.bissfx.data.csv;

import ch.bissbert.bissfx.data.TestClass;
import ch.bissbert.bissfx.data.mapper.ObjectMapper;
import ch.bissbert.bissfx.data.mapper.ObjectMapperProvider;
import ch.bissbert.bissfx.data.mapper.ObjectMappers;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CsvMapperTest {

    @Test
    void getMapper() {
        assertEquals(ObjectMappers.Providers.STRING, CsvReader.getMapperProvider(String.class));

        //check integer
        assertEquals(ObjectMappers.Providers.INTEGER, CsvReader.getMapperProvider(int.class));
        assertEquals(ObjectMappers.Providers.INTEGER, CsvReader.getMapperProvider(Integer.class));

        //check long
        assertEquals(ObjectMappers.Providers.LONG, CsvReader.getMapperProvider(long.class));
        assertEquals(ObjectMappers.Providers.LONG, CsvReader.getMapperProvider(Long.class));

        //check double
        assertEquals(ObjectMappers.Providers.DOUBLE, CsvReader.getMapperProvider(double.class));
        assertEquals(ObjectMappers.Providers.DOUBLE, CsvReader.getMapperProvider(Double.class));

        //check boolean
        assertEquals(ObjectMappers.Providers.BOOLEAN, CsvReader.getMapperProvider(boolean.class));
        assertEquals(ObjectMappers.Providers.BOOLEAN, CsvReader.getMapperProvider(Boolean.class));

        //check float
        assertEquals(ObjectMappers.Providers.FLOAT, CsvReader.getMapperProvider(float.class));
        assertEquals(ObjectMappers.Providers.FLOAT, CsvReader.getMapperProvider(Float.class));

        //check short
        assertEquals(ObjectMappers.Providers.SHORT, CsvReader.getMapperProvider(short.class));
        assertEquals(ObjectMappers.Providers.SHORT, CsvReader.getMapperProvider(Short.class));

        //check byte
        assertEquals(ObjectMappers.Providers.BYTE, CsvReader.getMapperProvider(byte.class));
        assertEquals(ObjectMappers.Providers.BYTE, CsvReader.getMapperProvider(Byte.class));

        //check localdate
        assertEquals(ObjectMappers.Providers.LOCAL_DATE, CsvReader.getMapperProvider(LocalDate.class));

        //check localdatetime
        assertEquals(ObjectMappers.Providers.LOCAL_DATE_TIME, CsvReader.getMapperProvider(LocalDateTime.class));
    }

    @Test
    void registerMapper() {
        ObjectMapperProvider<ObjectMapper<TestClass>> testClassObjectMapperProvider = new ObjectMapper<TestClass>() {
            @Override
            public TestClass map(String value) {
                return TestClass.builder().name(value).build();
            }

            @Override
            public String unmap(TestClass value) {
                return value.getName();
            }
        }.provider();
        ObjectMapperProvider<ObjectMapper<TestClass>> oldClassObjectMapperProvider = CsvReader.getMapperProvider(TestClass.class);
        CsvReader.registerMapper(TestClass.class, testClassObjectMapperProvider);
        assertEquals(testClassObjectMapperProvider, CsvReader.getMapperProvider(TestClass.class));
        assertNotEquals(oldClassObjectMapperProvider, CsvReader.getMapperProvider(TestClass.class));
        assertNotEquals(testClassObjectMapperProvider, oldClassObjectMapperProvider);
    }
}