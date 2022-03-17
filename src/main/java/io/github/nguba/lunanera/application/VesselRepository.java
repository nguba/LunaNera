package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.domain.VesselId;
import io.github.nguba.lunanera.domain.Vessel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Stores all configured Vessels, so we can locate them via their identity.
 */
public class VesselRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(VesselRepository.class);

    private final ConcurrentMap<VesselId, Vessel> controllers = new ConcurrentHashMap<>();

    public void add(final Vessel vessel) {
        LOGGER.info("Registering {}", vessel);
        controllers.putIfAbsent(vessel.getId(), vessel);
    }

    public boolean has(final VesselId id) {
        return controllers.containsKey(id);
    }

    public Optional<Vessel> find(final VesselId id) {
        return Optional.ofNullable(controllers.get(id));
    }

    public void remove(final VesselId id) {
        controllers.remove(id);
    }
}
