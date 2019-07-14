package com.fanhehe.message.util;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface ResultAnnotation {
    Class<? extends IResult> value() default IResult.class;
}
