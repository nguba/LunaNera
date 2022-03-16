package io.github.nguba.lunanera.domain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

public class TimestampedMeasurement<T> {
    protected final LocalDateTime when;
    protected final PidControllerID pidId;
    protected final UUID batchId;
    protected final T value;


    public TimestampedMeasurement(T value, LocalDateTime when, final PidControllerID pidId, final UUID batchId) {
        this.value = value;
        // we are truncating to second so that postgresql can handle the timestamps with the correct precision
        this.when = when.truncatedTo(ChronoUnit.SECONDS);
        this.pidId = pidId;
        this.batchId = batchId;
    }

    public LocalDateTime getWhen() {
        return when;
    }

    public T getValue() {
        return value;
    }

    public PidControllerID getDeviceId() {
        return pidId;
    }

    public UUID getBatchId() {
        return batchId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final TimestampedMeasurement<?> that = (TimestampedMeasurement<?>) o;
        return Objects.equals(when, that.when) && Objects.equals(pidId, that.pidId) && Objects.equals(batchId, that.batchId) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(when, pidId, batchId, value);
    }
}
