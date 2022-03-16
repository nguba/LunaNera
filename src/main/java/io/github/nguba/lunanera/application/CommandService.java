package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.domain.PidControllerID;
import io.github.nguba.lunanera.domain.command.ModbusCommand;
import io.github.nguba.lunanera.domain.command.ReadProcessValue;

import java.util.concurrent.*;

public class CommandService {

    public void scheduleInSeconds(final ModbusCommand command, final int period, ModbusService consumer) {
        Executors.newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(() -> consumer.request(command), 0, period, TimeUnit.SECONDS);
    }
}
