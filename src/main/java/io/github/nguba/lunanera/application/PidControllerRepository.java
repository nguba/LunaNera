package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.domain.PidControllerID;
import io.github.nguba.lunanera.domain.PidController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PidControllerRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PidControllerRepository.class);

    private final ConcurrentMap<PidControllerID, PidController> controllers =
            new ConcurrentHashMap<>();

    public void add(final PidController pidController) {
        LOGGER.info("Registering {}", pidController);
        controllers.putIfAbsent(pidController.getId(), pidController);
    }

    public boolean has(final PidControllerID id) {
        return controllers.containsKey(id);
    }

    public Optional<PidController> find(final PidControllerID id) {
        return Optional.ofNullable(controllers.get(id));
    }

    public void remove(final PidControllerID id) {
        controllers.remove(id);
    }
}
