package io.github.nguba.lunanera.infrastructure;

import org.springframework.context.ApplicationEventPublisher;

public class DomainEventPublisher implements EventPublisher {

    private final ApplicationEventPublisher publisher;

    public DomainEventPublisher(final ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(final Object event) {
        publisher.publishEvent(event);
    }
}
