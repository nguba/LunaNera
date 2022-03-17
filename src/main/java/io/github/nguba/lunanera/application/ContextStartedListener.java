package io.github.nguba.lunanera.application;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import io.github.nguba.lunanera.domain.VesselId;
import io.github.nguba.lunanera.domain.RedLionPXU;
import io.github.nguba.lunanera.domain.controller.CommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

public class ContextStartedListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContextStartedListener.class);

    private LunaNeraConfig config;

    private final VesselRepository vesselRepository;

    private ControllerService controllerService;

    private ModbusMaster master;

    private CommandService commandService;

    private final CommandFactory factory;

    public ContextStartedListener(final LunaNeraConfig config, final VesselRepository vesselRepository,
                                  ControllerService controllerService, ModbusMaster master, CommandService commandService, CommandFactory factory) {
        this.config = config;
        this.vesselRepository = vesselRepository;
        this.controllerService = controllerService;
        this.master = master;
        this.commandService = commandService;
        this.factory = factory;
    }

    @EventListener
    public void onContextRefreshed(final ContextRefreshedEvent event) {
        try {
            LOGGER.info("Connecting to RS485 devices on {}", config.getSerial().getDevice());
            master.connect();

            if (!master.isConnected()) {
                LOGGER.warn("Unable to connect to RS485 master on {}", config.getSerial().getDevice());
            }
        } catch (ModbusIOException e) {
            LOGGER.error("Unable to connect to RS485 master on {}: {}", config.getSerial().getDevice(), e.getMessage());
        }

        for (final LunaNeraConfig.Pid pidConfig : config.getPid()) {
            RedLionPXU pxu = new RedLionPXU(VesselId.of(pidConfig.getId()), master, pidConfig.getName());
            vesselRepository.add(pxu);
            commandService.scheduleInSeconds(factory.readProcessValue(pxu), pidConfig.getRate(), controllerService);
            commandService.scheduleInSeconds(factory.readSetpoint(pxu), 60, controllerService);
        }
        controllerService.start();
    }
}
