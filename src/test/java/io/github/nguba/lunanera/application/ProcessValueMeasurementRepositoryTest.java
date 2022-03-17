package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.IntegrationTest;
import io.github.nguba.lunanera.domain.VesselId;
import io.github.nguba.lunanera.domain.ProcessValue;
import io.github.nguba.lunanera.domain.ProcessValueMeasurement;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
public class ProcessValueMeasurementRepositoryTest {

    @Autowired
    ProcessValueMeasurementRepository repository;

    @Test
    void addMeasurement() throws Exception {
        UUID batchId = UUID.randomUUID();

        ProcessValueMeasurement expected = new ProcessValueMeasurement(ProcessValue.of(23.1f), LocalDateTime.now(), VesselId.of(245), batchId);
        repository.save(expected);

        try {
            assertThat(repository.read(batchId)).contains(expected);
        }
        finally {
            repository.delete(batchId);
        }
    }
}
