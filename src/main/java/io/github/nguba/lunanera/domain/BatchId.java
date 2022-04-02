package io.github.nguba.lunanera.domain;

import java.util.UUID;

public record BatchId(UUID value) {

    public static BatchId create() {
        return new BatchId(UUID.randomUUID());
    }
}
