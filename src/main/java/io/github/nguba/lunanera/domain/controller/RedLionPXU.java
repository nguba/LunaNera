package io.github.nguba.lunanera.domain.controller;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import io.github.nguba.lunanera.domain.Vessel;
import io.github.nguba.lunanera.domain.VesselId;

import java.math.BigDecimal;
import java.util.StringJoiner;

/*
 * Note that reads from the PDU are offset by one.  that means that a register stored in offset 1, is actually read as offset 0.
 */
public class RedLionPXU extends Vessel {

    private final ModbusMaster modbusMaster;

    private final String name;

    public RedLionPXU(final VesselId id, final ModbusMaster modbusMaster, String name) {
        super(id);
        this.modbusMaster = modbusMaster;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }


    @Override
    public BigDecimal readProcessValue() throws Exception {
        return readRegister(1);
    }

    @Override
    public BigDecimal readSetpoint() throws Exception {
        return readRegister(2);
    }

    @Override
    public BigDecimal readStatus() throws Exception {
        return readRegister(18);
    }

    private BigDecimal readRegister(final int register) throws ModbusProtocolException, ModbusNumberException, ModbusIOException {
        int startAddress = register - 1;
        int[] registerValues = modbusMaster.readHoldingRegisters(getId().value(), startAddress, 1);
        return BigDecimal.valueOf(registerValues[0]);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RedLionPXU.class.getSimpleName() + "[", "]")
                .add("name=" + name)
                .toString();
    }
}
