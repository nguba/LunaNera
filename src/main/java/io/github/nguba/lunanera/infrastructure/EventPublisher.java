package io.github.nguba.lunanera.infrastructure;

public interface EventPublisher {
    void publish(Object event);
}
