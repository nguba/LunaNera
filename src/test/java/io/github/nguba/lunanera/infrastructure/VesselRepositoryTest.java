package io.github.nguba.lunanera.infrastructure;

import io.github.nguba.lunanera.application.VesselRepository;
import io.github.nguba.lunanera.domain.PidController;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class VesselRepositoryTest {

    VesselRepository repository = new VesselRepository();

    private final PidController controller = PidControllerTestFactory.INSTANCE.makeMashTunPid(BigDecimal.ZERO);

    @Test
    void addController() {
        repository.add(controller);
        assertThat(repository.has(controller.getId())).isTrue();
    }

    @Test
    void controllerNotFound() {
        assertThat(repository.has(controller.getId())).isFalse();
    }

    @Test
    void removeController() {
        repository.add(controller);
        repository.remove(controller.getId());
        assertThat(repository.has(controller.getId())).isFalse();
    }

    @Test
    void findController() {
        repository.add(controller);
        assertThat(repository.find(controller.getId())).isPresent();
    }
}