package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.IntegrationTest;
import io.github.nguba.lunanera.domain.VesselId;
import io.github.nguba.lunanera.domain.ProcessValue;
import io.github.nguba.lunanera.domain.ProcessValueMeasurement;
import io.github.nguba.lunanera.domain.controller.When;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class ProcessValueMeasurementRepositoryTest {

    @Autowired
    ProcessValueMeasurementRepository repository;

    @Autowired
    Environment env;

    @Test
    void printEnv() {
        System.out.println(env.getProperty("spring.datasource.url"));
    }

    @Test
    void addMeasurement() throws Exception {
        UUID batchId = UUID.randomUUID();

        ProcessValueMeasurement expected = new ProcessValueMeasurement(ProcessValue.of(23.1f), When.now(), VesselId.of(245), batchId);
        repository.save(expected);

        try {
            assertThat(repository.read(batchId)).contains(expected);
        }
        finally {
            repository.delete(batchId);
        }
    }
}
