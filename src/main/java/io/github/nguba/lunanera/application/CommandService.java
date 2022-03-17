package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.domain.controller.Command;

import java.util.concurrent.*;

public class CommandService {

    public void scheduleInSeconds(final Command command, final int period, ControllerService consumer) {
        Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(() -> consumer.request(command), 0, period, TimeUnit.SECONDS);
    }
}
