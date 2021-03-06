package io.github.nguba.lunanera.domain;

import io.github.nguba.lunanera.infrastructure.PidControllerTestFactory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class VesselTest {

    Vessel controller = PidControllerTestFactory.INSTANCE.makeMashTunPid(BigDecimal.ZERO);

    @Test
    void isInitiallyOn() {
        assertThat(controller.getState()).isEqualTo(Vessel.State.ON);
    }

    @Test
    void toggleOn() {
        controller.on();
        assertThat(controller.getState()).isEqualTo(Vessel.State.ON);
    }

    @Test
    void toggleOff() {
        controller.off();
        assertThat(controller.getState()).isEqualTo(Vessel.State.OFF);
    }

    @Test
    void returnEventOnToggleOn() {
        controller.off();

        assertThat(controller.on()).isPresent();
    }

    @Test
    void returnEventOnToggleOff() {
        controller.on();

        assertThat(controller.off()).isPresent();
    }

    @Test
    void doNotToggleWhenAlreadyOn() {
        controller.on();
        assertThat(controller.on()).isNotPresent();
    }

    @Test
    void doNotToggleWhenAlreadyOff() {
        controller.off();
        assertThat(controller.off()).isNotPresent();
    }
}