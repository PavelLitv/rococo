package qa.guru.rococo.jupiter.annotation;


import org.junit.jupiter.api.extension.ExtendWith;
import qa.guru.rococo.jupiter.extension.BrowserExtension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(BrowserExtension.class)
public @interface WebTest {
}
