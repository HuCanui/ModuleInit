package annotation.hucanhui.com.libannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hucanhui on 2018/6/5.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface ApplicationModule {
    String value() default "";
}
