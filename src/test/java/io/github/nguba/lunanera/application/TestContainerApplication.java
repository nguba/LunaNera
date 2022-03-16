package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.application.LunaNeraApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootApplication
@Import({LunaNeraApplication.class})
@ActiveProfiles({"test"})
public class TestContainerApplication {

    @Bean
    PostgreSQLContainer db() {
        PostgreSQLContainer container = (PostgreSQLContainer) new PostgreSQLContainer("postgres:13.5")
                .withInitScript("schema.sql")
                .withDatabaseName("LunaNera")
                .withUsername("postgres");
        container.start();
        return container;
    }
}
