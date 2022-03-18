package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.domain.ProcessValueMeasurement;
import io.github.nguba.lunanera.domain.ProcessValueReceived;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

import java.sql.SQLException;

public class ProcessValueMeasuredListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessValueMeasuredListener.class);

    private final ProcessValueMeasurementRepository repository;

    public ProcessValueMeasuredListener(final ProcessValueMeasurementRepository repository) {
        this.repository = repository;
    }

    @EventListener
    public void onProcessValueMeasured(final ProcessValueReceived event) {
        LOGGER.debug("{}", event);
        try {
            ProcessValueMeasurement temp =
                    new ProcessValueMeasurement(event.processValue(), event.when(), event.vesselId(), event.batchId());
            repository.save(temp);
        } catch (SQLException e) {
            LOGGER.warn("Error persisting {}", event.processValue(), e);
        }
    }
}


