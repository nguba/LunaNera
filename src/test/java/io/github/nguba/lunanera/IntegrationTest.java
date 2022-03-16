package io.github.nguba.lunanera;

import io.github.nguba.lunanera.application.TestContainerApplication;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@WebFluxTest
@ContextConfiguration(classes = {TestContainerApplication.class})
@OverrideAutoConfiguration(enabled = true)
public @interface IntegrationTest {
}
