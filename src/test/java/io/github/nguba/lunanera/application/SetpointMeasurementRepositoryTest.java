package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.IntegrationTest;
import io.github.nguba.lunanera.application.measurement.SetpointMeasurementRepository;
import io.github.nguba.lunanera.domain.*;
import io.github.nguba.lunanera.infrastructure.PidControllerTestFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.UUID;

@IntegrationTest
@Disabled
class SetpointMeasurementRepositoryTest {

    @Autowired
    SetpointMeasurementRepository repository;

    BatchId batchId = BatchId.create();

    Vessel controller = PidControllerTestFactory.INSTANCE.makeFermenterPid(BigDecimal.valueOf(50));

    @Test
    void saveMeasurement() throws Exception {
        Setpoint sp=  Setpoint.of(3.00f);
        SetpointMeasurement measurement = new SetpointMeasurement(sp, When.now(), controller.getId(), batchId);

        repository.save(measurement);
    }
}