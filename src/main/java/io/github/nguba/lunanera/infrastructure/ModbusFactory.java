package io.github.nguba.lunanera.infrastructure;

import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.serial.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModbusFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModbusFactory.class);

    public ModbusMaster createMaster(String device) throws Exception {

        LOGGER.info("Modbus devices configured to talk to {}", device);
        SerialParameters sp = new SerialParameters();
        sp.setDevice(device);
        sp.setBaudRate(SerialPort.BaudRate.BAUD_RATE_9600);
        sp.setDataBits(8);
        sp.setParity(SerialPort.Parity.NONE);
        sp.setStopBits(1);

        SerialUtils.setSerialPortFactoryPureJavaComm();

        Modbus.setLogLevel(Modbus.LogLevel.LEVEL_WARNINGS);

        final ModbusMaster master = ModbusMasterFactory.createModbusMasterRTU(sp);
        master.setResponseTimeout(500);
        return master;
    }
}
