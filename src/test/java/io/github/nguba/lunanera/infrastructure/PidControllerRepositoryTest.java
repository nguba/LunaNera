package io.github.nguba.lunanera.infrastructure;

import io.github.nguba.lunanera.application.PidControllerRepository;
import io.github.nguba.lunanera.domain.PidController;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PidControllerRepositoryTest {

    PidControllerRepository respository = new PidControllerRepository();

    private PidController controller = PidControllerTestFactory.INSTANCE.makeMashTunPid(BigDecimal.ZERO);

    @Test
    void addController() {
        respository.add(controller);
        assertThat(respository.has(controller.getId())).isTrue();
    }

    @Test
    void controllerNotFound() {
        assertThat(respository.has(controller.getId())).isFalse();
    }

    @Test
    void removeController() {
        respository.add(controller);
        respository.remove(controller.getId());
        assertThat(respository.has(controller.getId())).isFalse();
    }

    @Test
    void findController() {
        respository.add(controller);
        assertThat(respository.find(controller.getId())).isPresent();
    }
}