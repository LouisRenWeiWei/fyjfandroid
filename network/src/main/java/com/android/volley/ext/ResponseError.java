package com.android.volley.ext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by 任伟伟
 * Datetime: 2016/11/24-15:48
 * Email: renweiwei@ufashion.com
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResponseError {
    String name() default "error";
}
