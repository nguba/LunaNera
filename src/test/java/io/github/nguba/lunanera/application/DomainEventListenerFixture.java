package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.domain.BatchId;
import io.github.nguba.lunanera.domain.VesselId;
import io.github.nguba.lunanera.infrastructure.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class DomainEventListenerFixture {
    @Autowired
    DomainEventPublisher publisher;
    BatchId batchId = BatchId.create();
    VesselId vesselId = VesselId.of(1);
}
