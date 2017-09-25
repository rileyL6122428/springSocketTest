package l2k.demo.multiple.chats.customannotations;

import java.lang.annotation.Target;

import org.junit.jupiter.api.Disabled;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Target({ ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Disabled("Implementation Pending For This Test")
public @interface ImplementationPending {

}
