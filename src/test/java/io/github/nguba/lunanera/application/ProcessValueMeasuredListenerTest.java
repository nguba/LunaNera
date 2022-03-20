package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.IntegrationTest;
import io.github.nguba.lunanera.application.measurement.ProcessValueMeasurementRepository;
import io.github.nguba.lunanera.domain.ProcessValue;
import io.github.nguba.lunanera.domain.ProcessValueMeasurement;
import io.github.nguba.lunanera.domain.ProcessValueReceived;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@IntegrationTest
class ProcessValueMeasuredListenerTest extends DomainEventListenerFixture {

    @Autowired
    ProcessValueMeasurementRepository repository;

    @Test
    void isPersistingProcessValueMeasurements() throws Exception {
        final ProcessValue processValue = ProcessValue.of(62.0f);
        final ProcessValueReceived event = ProcessValueReceived.with(processValue, vesselId, batchId);
        final ProcessValueMeasurement expected = new ProcessValueMeasurement(processValue, event.when(), vesselId, batchId);

        assertThat(repository.read(batchId)).asList().isEmpty();

        publisher.publish(event);

        assertThat(repository.read(batchId)).asList().singleElement().isEqualTo(expected);
    }
}