package io.github.nguba.lunanera.domain.instrumentation;

import io.github.nguba.lunanera.domain.Setpoint;

public record ProfileSegment(ProfileSegmentId id, Setpoint setpoint, int minutes) {
}
