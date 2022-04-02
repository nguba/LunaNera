package io.github.nguba.lunanera.domain.instrumentation;

import java.util.Collections;
import java.util.List;

public record Profile(ProfileId id) {

    public List<ProfileSegment> segments() {
        return Collections.emptyList();
    }
}
