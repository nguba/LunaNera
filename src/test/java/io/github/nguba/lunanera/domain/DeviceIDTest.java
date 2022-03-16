package io.github.nguba.lunanera.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeviceIDTest {

    @Test
    void verify() {
        EqualsVerifier.forClass(PidControllerID.class).usingGetClass().verify();
    }

    @Test
    @DisplayName("Value cannot be bigger than 255")
    void cannotConstructOverModbusLimit() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> {
            PidControllerID.of(Integer.valueOf(256));
        });
    }

    @Test
    @DisplayName("Value cannot be less than zero")
    void cannotBeLessThanZero() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> {
            PidControllerID.of(Integer.valueOf(-1));
        });
    }
}