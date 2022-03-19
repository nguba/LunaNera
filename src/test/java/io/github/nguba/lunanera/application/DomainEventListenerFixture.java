package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.domain.VesselId;
import io.github.nguba.lunanera.infrastructure.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class DomainEventListenerFixture {
    @Autowired
    DomainEventPublisher publisher;
    UUID batchId = UUID.randomUUID();
    VesselId vesselId = VesselId.of(1);
}
