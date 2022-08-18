package ch.bissbert.bissfx.data.csv;

import ch.bissbert.bissfx.data.TestClass;
import ch.bissbert.bissfx.data.mapper.ObjectMapper;
import ch.bissbert.bissfx.data.mapper.ObjectMapperProvider;
import ch.bissbert.bissfx.data.mapper.ObjectMappers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CsvReaderTest {

    private static CsvConfig<TestClass> config;
    private static CsvReader<TestClass> reader;

    @BeforeEach
    void beforeEach() {
        config = CsvConfig.builder(TestClass.class).build();
        reader = new CsvReader<>(config);
    }

    @Test
    void getMapper() {
        assertEquals(ObjectMappers.Providers.STRING, CsvReader.getMapper(String.class));

        //check integer
        assertEquals(ObjectMappers.Providers.INTEGER, CsvReader.getMapper(int.class));
        assertEquals(ObjectMappers.Providers.INTEGER, CsvReader.getMapper(Integer.class));

        //check long
        assertEquals(ObjectMappers.Providers.LONG, CsvReader.getMapper(long.class));
        assertEquals(ObjectMappers.Providers.LONG, CsvReader.getMapper(Long.class));

        //check double
        assertEquals(ObjectMappers.Providers.DOUBLE, CsvReader.getMapper(double.class));
        assertEquals(ObjectMappers.Providers.DOUBLE, CsvReader.getMapper(Double.class));

        //check boolean
        assertEquals(ObjectMappers.Providers.BOOLEAN, CsvReader.getMapper(boolean.class));
        assertEquals(ObjectMappers.Providers.BOOLEAN, CsvReader.getMapper(Boolean.class));

        //check float
        assertEquals(ObjectMappers.Providers.FLOAT, CsvReader.getMapper(float.class));
        assertEquals(ObjectMappers.Providers.FLOAT, CsvReader.getMapper(Float.class));

        //check short
        assertEquals(ObjectMappers.Providers.SHORT, CsvReader.getMapper(short.class));
        assertEquals(ObjectMappers.Providers.SHORT, CsvReader.getMapper(Short.class));

        //check byte
        assertEquals(ObjectMappers.Providers.BYTE, CsvReader.getMapper(byte.class));
        assertEquals(ObjectMappers.Providers.BYTE, CsvReader.getMapper(Byte.class));

        //check localdate
        assertEquals(ObjectMappers.Providers.LOCAL_DATE, CsvReader.getMapper(LocalDate.class));

        //check localdatetime
        assertEquals(ObjectMappers.Providers.LOCAL_DATE_TIME, CsvReader.getMapper(LocalDateTime.class));
    }

    @Test
    void registerMapper() {
        ObjectMapperProvider<ObjectMapper<TestClass>> testClassObjectMapperProvider =
                ((ObjectMapper<TestClass>) value -> TestClass.builder().name(value).build())
                        .provider();
        ObjectMapperProvider<ObjectMapper<TestClass>> oldClassObjectMapperProvider = CsvReader.getMapper(TestClass.class);
        CsvReader.registerMapper(TestClass.class, testClassObjectMapperProvider);
        assertEquals(testClassObjectMapperProvider, CsvReader.getMapper(TestClass.class));
        assertNotEquals(oldClassObjectMapperProvider, CsvReader.getMapper(TestClass.class));
        assertNotEquals(testClassObjectMapperProvider, oldClassObjectMapperProvider);
    }

    @Test
    void readByIndex() {
        String test = "1;\"TEST\"";
        TestClass testClass = reader.read(test);
        assertEquals(1, testClass.getId());
        assertEquals("TEST", testClass.getName());

        //to with values that are not used in the class
        test = "1;\"TEST\";\"TEST\"";
        testClass = reader.read(test);
        assertEquals(1, testClass.getId());
        assertEquals("TEST", testClass.getName());

        //seperator
        test = "1,\"TEST\"";
        reader.getConfig().setSeparator(",");
        testClass = reader.read(test);
        assertEquals(1, testClass.getId());
        assertEquals("TEST", testClass.getName());

        //quotes
        test = "2,MY NAME";
        reader.getConfig().setQuote("");
        testClass = reader.read(test);
        assertEquals(2, testClass.getId());
        assertEquals("MY NAME", testClass.getName());

        //regex
        test = "2?MY NAME";
        reader.getConfig().setSeparator("?");
        testClass = reader.read(test);
        assertEquals(2, testClass.getId());
        assertEquals("MY NAME", testClass.getName());
    }

    @Test
    void readByHeader() {
        String test = "\"TEST\";1";
        String[] headers = {"name", "id"};
        config.setHeaders(headers);
        config.setFindByHeaders(true);
        TestClass testClass = reader.read(test);
        assertEquals(1, testClass.getId());
        assertEquals("TEST", testClass.getName());

        //mixed headers
        test = "2;\"TEST2\"";
        headers = new String[]{"id", "name"};
        config.setHeaders(headers);
        testClass = reader.read(test);
        assertEquals(2, testClass.getId());
        assertEquals("TEST2", testClass.getName());

        //with empty values
        test = "2;\"THIS SHOULD NOT APPEAR\";\"TEST2\"";
        headers = new String[]{"id", "test", "name"};
        config.setHeaders(headers);
        testClass = reader.read(test);
        assertEquals(2, testClass.getId());
        assertEquals("TEST2", testClass.getName());


    }

    @Test
    void readAll() {
        String singleLine = "1;\"TEST\"";
        String multiLine = "1;\"TEST\"\n2;\"TEST2\"";

        List<TestClass> singleLineTests = reader.readAll(singleLine);
        assertEquals(1, singleLineTests.size());
        assertEquals(1, singleLineTests.get(0).getId());
        assertEquals("TEST", singleLineTests.get(0).getName());

        List<TestClass> multiLineTests = reader.readAll(multiLine);
        assertEquals(2, multiLineTests.size());
        //check first line
        assertEquals(1, multiLineTests.get(0).getId());
        assertEquals("TEST", multiLineTests.get(0).getName());
        //check second line
        assertEquals(2, multiLineTests.get(1).getId());
        assertEquals("TEST2", multiLineTests.get(1).getName());

        //check with different line separator
        multiLine = "1;\"TEST\"{_}2;\"TEST2\"";
        reader.getConfig().setEscape("{_}");
        multiLineTests = reader.readAll(multiLine);
        assertEquals(2, multiLineTests.size());
        //check first line
        assertEquals(1, multiLineTests.get(0).getId());
        assertEquals("TEST", multiLineTests.get(0).getName());
        //check second line
        assertEquals(2, multiLineTests.get(1).getId());
        assertEquals("TEST2", multiLineTests.get(1).getName());
    }

    @Test
    void getConfig() {
        assertEquals(config, reader.getConfig());
    }
}