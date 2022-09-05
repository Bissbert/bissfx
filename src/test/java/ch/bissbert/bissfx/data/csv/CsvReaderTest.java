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
        reader.getCsvConfig().setSeparator(",");
        testClass = reader.read(test);
        assertEquals(1, testClass.getId());
        assertEquals("TEST", testClass.getName());

        //quotes
        test = "2,MY NAME";
        reader.getCsvConfig().setQuote("");
        testClass = reader.read(test);
        assertEquals(2, testClass.getId());
        assertEquals("MY NAME", testClass.getName());

        //regex
        test = "2?MY NAME";
        reader.getCsvConfig().setSeparator("?");
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
        reader.getCsvConfig().setEscape("{_}");
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
    void getCsvConfig() {
        assertEquals(config, reader.getCsvConfig());
    }
}