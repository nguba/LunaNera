package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.IntegrationTest;
import io.github.nguba.lunanera.domain.ControllerStatus;
import io.github.nguba.lunanera.domain.ControllerStatusReceived;
import org.junit.jupiter.api.Test;

@IntegrationTest
class ControllerStatusReceivedListenerTest extends DomainEventListenerFixture {

    @Test
    void persistsEvent() {
        publisher.publish(ControllerStatusReceived.with(ControllerStatus.PAUSE, vesselId, batchId));
    }
}