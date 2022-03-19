package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.domain.*;
import io.github.nguba.lunanera.domain.controller.Command;
import io.github.nguba.lunanera.domain.controller.CommandFactory;
import io.github.nguba.lunanera.domain.controller.ControllerSwitchedOff;
import io.github.nguba.lunanera.domain.controller.ControllerSwitchedOn;
import io.github.nguba.lunanera.infrastructure.EventPublisher;

import io.github.nguba.lunanera.infrastructure.PidControllerTestFactory;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class ControllerServiceTest {

    public static final BigDecimal EXPECTED_VALUE = BigDecimal.valueOf(554);

    ControllerService controllerService = new ControllerService();

    BlockingQueue<Object> events = new LinkedBlockingQueue<>();

    private final Vessel controller = PidControllerTestFactory.INSTANCE.makeHotLiquorPid(EXPECTED_VALUE);

    CommandFactory commands = new CommandFactory(new EventPublisher() {
        @Override
        public void publish(final Object event) {
            //System.out.println(event);
            events.add(event);
        }
    });

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void scheduleOneCommand() throws InterruptedException {
        AssertionsForClassTypes.assertThat(controllerService.queued()).isEmpty();

        controllerService.scheduleInSeconds(commands.readProcessValue(controller), 1);

        assertThat(events.take()).isInstanceOf(ProcessValueReceived.class);
        assertThat(events).isEmpty();
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void scheduleMultipleCommands() throws InterruptedException {
        AssertionsForClassTypes.assertThat(controllerService.queued()).isEmpty();

        controllerService.scheduleInSeconds(commands.readProcessValue(controller), 5);
        controllerService.scheduleInSeconds(commands.readSetpoint(controller), 4);

        assertThat(events.take()).isInstanceOf(ProcessValueReceived.class);
        assertThat(events.take()).isInstanceOf(SetpointReceived.class);

        assertThat(events).isEmpty();
    }

    @BeforeEach
    void setUp() {
        controllerService.start();
    }

    @AfterEach
    void tearDown() {
        controllerService.stop();
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void readProcessValue() throws InterruptedException {
        controllerService.request(commands.readProcessValue(controller));

        Object event = events.take();

        assertThat(event).isInstanceOf(ProcessValueReceived.class);
        Float value = ((ProcessValueReceived) event).processValue().value();
        assertThat(value).isEqualTo(ProcessValue.withSingleDecimalPrecision(EXPECTED_VALUE).value());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void readSetpoint() throws InterruptedException {
        controllerService.request(commands.readSetpoint(controller));

        Object event = events.take();

        assertThat(event).isInstanceOf(SetpointReceived.class);
        Float value = ((SetpointReceived) event).getValue().value();
        assertThat(value).isEqualTo(Setpoint.withSingleDecimalPrecision(EXPECTED_VALUE).value());
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void switchControllerOffOnFailure() throws InterruptedException {
        Vessel controller =
                PidControllerTestFactory.INSTANCE.makeFailing(new IOException("switched off"), Integer.valueOf(3));

        controllerService.request(commands.readProcessValue(controller));

        Object event = events.take();
        assertThat(event).isExactlyInstanceOf(ControllerSwitchedOn.class);
    }

    @Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void switchBackOnAfterReadSuccess() throws InterruptedException {
        controller.off();
        controllerService.request(commands.readProcessValue(controller));

        assertThat(events.take()).isInstanceOf(ProcessValueReceived.class);
        assertThat(events.take()).isExactlyInstanceOf(ControllerSwitchedOff.class);
    }

}