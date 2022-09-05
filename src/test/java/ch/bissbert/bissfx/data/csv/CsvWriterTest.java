package ch.bissbert.bissfx.data.csv;

import ch.bissbert.bissfx.data.TestClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CsvWriterTest {

    private static CsvConfig<TestClass> config;
    private static CsvWriter<TestClass> writer;

    @BeforeEach
    void before() {
        config = CsvConfig.builder(TestClass.class).build();
        writer = new CsvWriter<>(config);
    }

    @Test
    void write() {
        TestClass testClass = TestClass.builder().id(1).name("TEST").build();

        String test = writer.write(testClass);
        assertEquals("1;\"TEST\"", test);

        //change separator
        config.setSeparator(",");
        test = writer.write(testClass);
        assertEquals("1,\"TEST\"", test);

        testClass = TestClass.builder().id(2).name("MY NAME").build();

        //change quote
        config.setQuote("");
        test = writer.write(testClass);
        assertEquals("2,MY NAME", test);

        //use header
        config.setFindByHeaders(true);
        config.setHeaders(new String[]{"id", "name"});
        test = writer.write(testClass);
        assertEquals("2,MY NAME", test);

        //switch header order
        config.setHeaders(new String[]{"name", "id"});
        test = writer.write(testClass);
        assertEquals("MY NAME,2", test);

        //empty header inbetween
        config.setHeaders(new String[]{"id", "", "name"});
        test = writer.write(testClass);
        assertEquals("2,,MY NAME", test);
    }

    @Test
    void writeMultiple() {
        TestClass testClass = TestClass.builder().id(1).name("TEST").build();
        TestClass testClass2 = TestClass.builder().id(2).name("MY NAME").build();

        List<TestClass> testClasses = new ArrayList<>();
        testClasses.add(testClass);

        String test = writer.writeMultiple(testClasses);
        assertEquals("1;\"TEST\"", test);

        testClasses.add(testClass2);
        test = writer.writeMultiple(testClasses);
        assertEquals("1;\"TEST\"\n2;\"MY NAME\"", test);

        //change separator
        config.setSeparator(",");
        test = writer.writeMultiple(testClasses);
        assertEquals("1,\"TEST\"\n2,\"MY NAME\"", test);

        //change quote
        config.setQuote("");
        test = writer.writeMultiple(testClasses);
        assertEquals("1,TEST\n2,MY NAME", test);

        //change escape
        config.setEscape("\\");
        test = writer.writeMultiple(testClasses);
        assertEquals("1,TEST\\2,MY NAME", test);

        //use header
        config.setFindByHeaders(true);
        config.setHeaders(new String[]{"name", "id"});
        test = writer.writeMultiple(testClasses);
        assertEquals("TEST,1\\MY NAME,2", test);

    }
}