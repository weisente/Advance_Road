package com.example;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.CLASS;
//编译时期
@Retention(CLASS) @Target(FIELD)
public @interface BindView  {
    int value();
}
