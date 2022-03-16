package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.domain.AbstractPidController;
import io.github.nguba.lunanera.domain.command.CommandFactory;
import io.github.nguba.lunanera.domain.command.ModbusCommand;
import io.github.nguba.lunanera.infrastructure.EventPublisher;
import io.github.nguba.lunanera.infrastructure.PidControllerTestFactory;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CommandServiceTest {

    CommandService producer = new CommandService();

    ModbusService consumer = new ModbusService();

    AbstractPidController controller = PidControllerTestFactory.INSTANCE.makeHotLiquorPid(BigDecimal.valueOf(895));

    CommandFactory commands = new CommandFactory(new EventPublisher() {
        @Override
        public void publish(final Object event) {

        }
    });

    @Test
    void scheduleOneCommand() throws InterruptedException {
        assertThat(consumer.queued()).isEmpty();

        producer.scheduleInSeconds(commands.readProcessValue(controller), 1, consumer);

        Thread.sleep(5000);

        assertThat(consumer.queued()).hasSizeBetween(4, 6);

        for(ModbusCommand command : consumer.queued()) {
            System.out.println(command);
        }
    }

    @Test
    void scheduleMultipleCommands() throws InterruptedException {
        assertThat(consumer.queued()).isEmpty();

        producer.scheduleInSeconds(commands.readProcessValue(controller), 1, consumer);
        producer.scheduleInSeconds(commands.readSetpoint(controller), 2, consumer);

        Thread.sleep(5000);

        assertThat(consumer.queued()).hasSizeBetween(6, 9);

        for(ModbusCommand command : consumer.queued()) {
            System.out.println(command);
        }
    }
}