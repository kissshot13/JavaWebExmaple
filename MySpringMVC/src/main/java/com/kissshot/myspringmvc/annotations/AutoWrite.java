package com.kissshot.myspringmvc.annotations;

import java.lang.annotation.*;

@Target(value = ElementType.FIELD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoWrite {
}
