package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.IntegrationTest;
import io.github.nguba.lunanera.domain.VesselId;
import io.github.nguba.lunanera.domain.ProcessValue;
import io.github.nguba.lunanera.domain.ProcessValueMeasurement;
import io.github.nguba.lunanera.domain.ProcessValueReceived;
import io.github.nguba.lunanera.infrastructure.DomainEventPublisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@IntegrationTest
class ProcessValueMeasuredListenerTest {

    @Autowired
    DomainEventPublisher publisher;

    @Autowired
    ProcessValueMeasurementRepository repository;

    @Test
    void isPersistingProcessValueMeasurements() throws Exception {

        final UUID batchId = UUID.randomUUID();
        final ProcessValue processValue = ProcessValue.of(62.0f);
        final VesselId id = VesselId.of(1);
        final ProcessValueReceived event = ProcessValueReceived.with(processValue, id, batchId);
        final ProcessValueMeasurement expected = new ProcessValueMeasurement(processValue, event.when(), id, batchId);

        assertThat(repository.read(batchId)).asList().isEmpty();

        publisher.publish(event);

        assertThat(repository.read(batchId)).asList().singleElement().isEqualTo(expected);
    }
}