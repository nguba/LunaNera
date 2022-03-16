package io.github.nguba.lunanera.application;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import io.github.nguba.lunanera.domain.PidControllerID;
import io.github.nguba.lunanera.domain.RedLionPXU;
import io.github.nguba.lunanera.domain.command.CommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

public class ContextStartedListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContextStartedListener.class);

    private LunaNeraConfig config;

    private final PidControllerRepository pidControllerRepository;

    private ModbusService modbusService;

    private ModbusMaster master;

    private CommandService commandService;

    private final CommandFactory factory;

    public ContextStartedListener(final LunaNeraConfig config, final PidControllerRepository pidControllerRepository,
                                  ModbusService modbusService, ModbusMaster master, CommandService commandService, CommandFactory factory) {
        this.config = config;
        this.pidControllerRepository = pidControllerRepository;
        this.modbusService = modbusService;
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
            RedLionPXU pxu = new RedLionPXU(PidControllerID.of(pidConfig.getId()), master, pidConfig.getName());
            pidControllerRepository.add(pxu);
            commandService.scheduleInSeconds(factory.readProcessValue(pxu), pidConfig.getRate(), modbusService);
            commandService.scheduleInSeconds(factory.readSetpoint(pxu), 60, modbusService);
        }
        modbusService.start();
    }
}
