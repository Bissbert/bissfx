package ch.bissbert.bissfx.data.csv;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for fields that should be mapped to a csv value.
 *
 * @author bissbert
 * @version 1.0.0
 * @see CsvReader
 * @see CsvWriter
 * @see CsvConfig
 * @since 1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)

public @interface CsvValue {
    String name() default "";

    int index() default -1;
}
