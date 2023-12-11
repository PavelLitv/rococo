package qa.guru.rococo.jupiter.annotation;

import org.junit.jupiter.api.extension.ExtendWith;
import qa.guru.rococo.jupiter.extension.ApiLoginExtension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(ApiLoginExtension.class)
public @interface ApiLogin {
    String username() default "";
    String password() default "";
    CreateUser user() default @CreateUser(handleAnnotation = false);
}
