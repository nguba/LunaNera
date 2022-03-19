package io.github.nguba.lunanera.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.PostgreSQLContainer;

@SpringBootApplication
@Import({LunaNeraApplication.class})
public class TestContainerApplication {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestContainerApplication.class);

    @Bean
    PostgreSQLContainer db() {
        PostgreSQLContainer container = (PostgreSQLContainer) new PostgreSQLContainer("postgres:13.5")
                .withInitScript("schema.sql")
                .withDatabaseName("LunaNera")
                .withUsername("postgres");
       // container.start();

       //LOGGER.info("jdbc.url={}, driver={}", container.getJdbcUrl(), container.getDriverClassName());

        return container;
    }
}
