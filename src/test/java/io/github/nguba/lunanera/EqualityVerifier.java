package io.github.nguba.lunanera;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public interface EqualityVerifier<T> {

    @Test
    @DisplayName("Verify the equality contract")
    default void equalityVerification()
    {
        EqualsVerifier.forClass(typeClass()).usingGetClass().verify();
    }

    Class<? extends T> typeClass();

}
