package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.IntegrationTest;
import io.github.nguba.lunanera.domain.ControllerStatus;
import io.github.nguba.lunanera.domain.ControllerStatusReceived;
import io.github.nguba.lunanera.domain.Vessel;
import io.github.nguba.lunanera.domain.VesselId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@IntegrationTest
@ActiveProfiles({"test"})
class ControllerStatusRepositoryTest {

    @Autowired
    ControllerStatusRepository repository;

    @Test
    void saveEvent() throws Exception {
        ControllerStatusReceived event =
                ControllerStatusReceived.with(ControllerStatus.ADVANCE, VesselId.of(1), UUID.randomUUID());

       repository.save(event);

        assertThat(repository.find(VesselId.of(1))).isPresent().get().isEqualTo(event);
    }
}