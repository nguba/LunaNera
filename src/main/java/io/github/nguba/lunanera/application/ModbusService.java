package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.domain.command.ModbusCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.StringJoiner;
import java.util.concurrent.*;

/**
 * Processes commands from a queue. This is necessary so that requests to the Modbus master never overlap which
 * would result in unwanted IO errors (serial device).
 */
public class ModbusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModbusService.class);

    @Override
    public String toString() {
        return new StringJoiner(", ", ModbusService.class.getSimpleName() + "[", "]")
                .add("commands=" + commands)
                .toString();
    }

    private final BlockingQueue<ModbusCommand> commands = new LinkedBlockingQueue<>();

    private final ScheduledExecutorService consumer = Executors.newScheduledThreadPool(1);

    public ModbusService() {
    }

    public void start() {
        consumer.scheduleWithFixedDelay(() -> {
            try {
                ModbusCommand command = commands.take();
                command.execute();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, 0, 250, TimeUnit.MILLISECONDS);
        LOGGER.info("Started {}", this);
    }

    public void stop() {
        consumer.shutdownNow();
    }

    public void request(final ModbusCommand command) {
        commands.add(command);
    }

    public ModbusCommand[] queued() {
        return commands.toArray(new ModbusCommand[commands.size()]);
    }
}
