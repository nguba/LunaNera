package io.github.nguba.lunanera.domain;

import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;

import java.math.BigDecimal;
import java.util.StringJoiner;

/*
 * Note that reads from the PDU are offset by one.  that means that a register stored in offset 1, is actually read as offset 0.
 */
// TODO refactor into Vessel concept
public class RedLionPXU extends PidController {

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
        int[] registerValues = modbusMaster.readHoldingRegisters(getId().value(), 0, 1);
        return BigDecimal.valueOf(registerValues[0]);
    }

    @Override
    public BigDecimal readSetpoint() throws Exception {
        int[] registerValues = modbusMaster.readHoldingRegisters(getId().value(), 1, 1);
        return BigDecimal.valueOf(registerValues[0]);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RedLionPXU.class.getSimpleName() + "[", "]")
                .add("name=" + name)
                .toString();
    }
}
