package io.github.nguba.lunanera.domain.instrumentation;

import io.github.nguba.lunanera.domain.Setpoint;

public record SetpointProfileSegment(SetpointProfileSegmentId id, Setpoint setpoint, int minutes) {
}
