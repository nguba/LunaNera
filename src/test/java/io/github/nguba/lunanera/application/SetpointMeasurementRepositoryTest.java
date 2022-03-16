package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.IntegrationTest;
import io.github.nguba.lunanera.domain.AbstractPidController;
import io.github.nguba.lunanera.domain.Setpoint;
import io.github.nguba.lunanera.domain.SetpointMeasurement;
import io.github.nguba.lunanera.infrastructure.PidControllerTestFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@IntegrationTest
@Disabled
class SetpointMeasurementRepositoryTest {

    @Autowired
    SetpointMeasurementRepository repository;

    UUID batchId = UUID.randomUUID();

    AbstractPidController controller = PidControllerTestFactory.INSTANCE.makeFermenterPid(BigDecimal.valueOf(50));

    @Test
    void saveMeasurement() throws Exception {
        Setpoint sp=  Setpoint.of(3.00f);
        SetpointMeasurement measurement = new SetpointMeasurement(sp, LocalDateTime.now(), controller.getId(), batchId);

        repository.save(measurement);
    }
}