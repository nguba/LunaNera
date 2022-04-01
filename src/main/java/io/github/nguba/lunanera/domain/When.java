package io.github.nguba.lunanera.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public record When(ZonedDateTime value) {

    private static final ZoneId ZONE_ID = ZoneId.of("UTC");

    public When {
        value = value.truncatedTo(ChronoUnit.MILLIS);
    }

    public When(final LocalDateTime when) {
        this(ZonedDateTime.of(when, ZONE_ID));
    }

    public static When now() {
        return new When(ZonedDateTime.now(ZONE_ID));
    }

    public LocalDateTime toLocalDateTime() {
        return value.toLocalDateTime();
    }
}
