package ch.bissbert.bissfx.data.csv;

import ch.bissbert.bissfx.data.mapper.ObjectMapperProvider;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
/**
 * Annotation for fields that should be mapped to a csv value.
 * @author bissbert
 * @since 1.0.0
 * @version 1.0.0
 * @see CsvReader
 * @see CsvWriter
 * @see CsvConfig
 */
public @interface CsvValue {
    String name() default "";

    int index() default -1;
}
