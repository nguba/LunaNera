package io.github.nguba.lunanera.domain;

import java.util.Objects;
import java.util.UUID;

public abstract class Measurement<T> {
    protected final When when;
    protected final VesselId pidId;
    protected final UUID batchId;
    protected final T value;

    public Measurement(T value, When when, final VesselId pidId, final UUID batchId) {
        this.value = value;
        // we are truncating to second so that postgresql can handle the timestamps with the correct precision
        this.when = when;
        this.pidId = pidId;
        this.batchId = batchId;
    }

    public When getWhen() {
        return when;
    }

    public T getValue() {
        return value;
    }

    public VesselId getDeviceId() {
        return pidId;
    }

    public UUID getBatchId() {
        return batchId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Measurement<?> that = (Measurement<?>) o;
        return Objects.equals(when, that.when) && Objects.equals(pidId, that.pidId) && Objects.equals(batchId, that.batchId) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(when, pidId, batchId, value);
    }
}
