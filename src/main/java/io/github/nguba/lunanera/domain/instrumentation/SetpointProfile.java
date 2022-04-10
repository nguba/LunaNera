package io.github.nguba.lunanera.domain.instrumentation;

import java.util.Collections;
import java.util.List;

/**
 * The PXU can be configured for ramp/soak profile operation, where the
 * unit can control a process to conform to a time based process/
 * temperature profile. A profile is a series of 1 to 16 programmable ramp or
 * hold (soak) segments. Each segment has a setpoint value and segment
 * duration time value associated with it. The segment type, i.e., ramp or
 * hold (soak) segment, is determined by whether the previous
 * segment’s setpoint is the same as the preceding setpoint. If they differ,
 * the segment setpoint value will ramp from the previous setpoint value to
 * the preceding segment’s setpoint value within the programmed segment
 * time. The segment time effectively controls the ramp rate. When a profile
 * is started, each time based segment will execute in order until the
 * completion of the last segment, at which point the profile will cycle, end
 * or link to another profile. There are 16 profiles, which may be linked to
 * increase the number of segments used for a process. Each profile can
 * be started, stopped, paused or automatically delayed insuring profile
 * conformity (guarantied soak).
 *
 * @param id
 */
public record SetpointProfile(SetpointProfileId id) {

    public List<ProfileSegment> segments() {
        return Collections.emptyList();
    }
}
