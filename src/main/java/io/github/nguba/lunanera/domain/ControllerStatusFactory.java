package io.github.nguba.lunanera.domain;

import java.math.BigDecimal;

// 0: Stop, 1: Run, 2 = End (Profile mode), 3 = Pause
//(Profile mode), 4 = Advance Profile (Profile mode)
public enum ControllerStatusFactory {
    INSTANCE;

    public ControllerStatus makeFrom(BigDecimal value) {
        switch(value.intValue()) {
            case 0:
                return ControllerStatus.STOP;
            case 1:
                return ControllerStatus.RUN;
            case 2:
                return ControllerStatus.END;
            case 3:
                return ControllerStatus.PAUSE;
            case 4:
                return ControllerStatus.ADVANCE;
            default:
                throw new IllegalStateException("Unknown opcode for Controller Status" + value);
        }
    }
}
