package com.cacaofriendsshop.etc.config등등;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.METHOD;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(METHOD)
public @interface LoginCheck {

    MemberLevel authority() default MemberLevel.DEFAULT;
}
