package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.IntegrationTest;
import io.github.nguba.lunanera.domain.PidControllerID;
import io.github.nguba.lunanera.domain.PidControllerStatus;
import io.github.nguba.lunanera.domain.PidController;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@IntegrationTest
@Disabled("until feature is flagged for implementation")
class PidControllerStatusRepositoryTest {

    @Autowired
    PidControllerStatusRepository repository;

    PidControllerID id = PidControllerID.of(1);

    UUID batchId = UUID.randomUUID();

    @Test
    void persistOffState() {
        PidControllerStatus status = PidControllerStatus.with(PidController.State.OFF, LocalDateTime.now(), id, batchId);

        repository.save(status);

        assertThat(repository.findAll()).asList().singleElement().isEqualTo(status);
    }

    @Test
    void persistOnState() {
        PidControllerStatus status = PidControllerStatus.with(PidController.State.OFF, LocalDateTime.now(), id, batchId);
    }
}