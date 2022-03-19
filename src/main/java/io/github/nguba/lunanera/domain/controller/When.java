package io.github.nguba.lunanera.domain.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public record When(LocalDateTime value) {

    public When {
        value = value.truncatedTo(ChronoUnit.MILLIS);
    }

    public static When now() {
        return new When(LocalDateTime.now());
    }
}
