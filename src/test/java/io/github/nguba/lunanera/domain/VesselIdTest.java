package io.github.nguba.lunanera.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VesselIdTest {

    @Test
    void verify() {
        EqualsVerifier.forClass(VesselId.class).usingGetClass().verify();
    }

    @Test
    @DisplayName("Value cannot be bigger than 255")
    void cannotConstructOverModbusLimit() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> VesselId.of(256));
    }

    @Test
    @DisplayName("Value cannot be less than zero")
    void cannotBeLessThanZero() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> VesselId.of(-1));
    }
}