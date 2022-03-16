package io.github.nguba.lunanera.domain;

import java.time.LocalDateTime;

public interface DomainEvent {
    LocalDateTime getWhen();
}
