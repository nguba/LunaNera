package io.github.nguba.lunanera.domain;

import java.math.BigDecimal;

public interface PidController {
    AbstractPidController.State getState();

    public PidControllerID getId();

    public String getName();

    public enum State {
        ON, OFF;
    }

    /*
     * Note that reads from the PDU are offset by one.  that means that a register stored in offset 1, is actually read as offset 0.
     */
    BigDecimal readProcessValue() throws Exception;

    BigDecimal readSetpoint() throws Exception;
}
