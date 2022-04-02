package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.IntegrationTest;
import io.github.nguba.lunanera.application.status.StatusRepository;
import io.github.nguba.lunanera.domain.BatchId;
import io.github.nguba.lunanera.domain.ControllerStatus;
import io.github.nguba.lunanera.domain.ControllerStatusReceived;
import io.github.nguba.lunanera.domain.VesselId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@IntegrationTest
@ActiveProfiles({"test"})
class StatusRepositoryTest {

    @Autowired
    StatusRepository repository;

    BatchId batchId = BatchId.create();

    @Test
    void saveEvent() throws Exception {
        ControllerStatusReceived event =
                ControllerStatusReceived.with(ControllerStatus.ADVANCE, VesselId.of(1), batchId);

       repository.save(event);

        assertThat(repository.find(VesselId.of(1))).isPresent().get().isEqualTo(event);
    }

    @Test
    void update() throws Exception {
        ControllerStatusReceived first =
                ControllerStatusReceived.with(ControllerStatus.RUN, VesselId.of(1), batchId);

        repository.save(first);

        ControllerStatusReceived second =
                ControllerStatusReceived.with(ControllerStatus.STOP, VesselId.of(1), batchId);

        repository.save(second);

        assertThat(repository.find(VesselId.of(1))).isPresent().get().isEqualTo(second);
    }
}