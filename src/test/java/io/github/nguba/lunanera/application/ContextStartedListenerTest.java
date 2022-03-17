package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.IntegrationTest;
import io.github.nguba.lunanera.infrastructure.PidControllerTestFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@IntegrationTest
class ContextStartedListenerTest {

    @Autowired
    private VesselRepository repo;

    @Test
    void hasMashTunController() {
        assertThat(repo.has(PidControllerTestFactory.INSTANCE.makeMashTunPid(BigDecimal.ZERO).getId())).isTrue();
    }

    @Test
    void hasBoilKettleController() {
        assertThat(repo.has(PidControllerTestFactory.INSTANCE.makeBoilKettlePid(BigDecimal.ZERO).getId())).isTrue();
    }

    @Test
    void hasHotLiquorTunController() {
        assertThat(repo.has(PidControllerTestFactory.INSTANCE.makeHotLiquorPid(BigDecimal.ZERO).getId())).isTrue();
    }

    @Test
    void hasFermenterController() {
        assertThat(repo.has(PidControllerTestFactory.INSTANCE.makeFermenterPid(BigDecimal.ZERO).getId())).isTrue();
    }

    @Test
    void hasCellarController() {
        assertThat(repo.has(PidControllerTestFactory.INSTANCE.makeHotLiquorPid(BigDecimal.ZERO).getId())).isTrue();
    }
}