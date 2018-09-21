package com.kissshot.myspringmvc.annotations;

import java.lang.annotation.*;

@Target(value = ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
    String value();
}
