package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.IntegrationTest;
import io.github.nguba.lunanera.application.measurement.ProcessValueMeasurementRepository;
import io.github.nguba.lunanera.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

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
        BatchId batchId = BatchId.create();

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
