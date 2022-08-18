package ch.bissbert.bissfx.data.csv;

import ch.bissbert.bissfx.data.mapper.ObjectMapperProvider;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CsvValue {
    String name() default "";
    int index() default -1;
}
