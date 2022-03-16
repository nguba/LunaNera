package io.github.nguba.lunanera.infrastructure;

import io.github.nguba.lunanera.domain.AbstractPidController;
import io.github.nguba.lunanera.domain.PidController;
import io.github.nguba.lunanera.domain.PidControllerID;
import io.github.nguba.lunanera.domain.ProcessValue;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.StringJoiner;

public enum PidControllerTestFactory {
    INSTANCE;

    public static final BigDecimal ERROR_SIGNAL = BigDecimal.valueOf(Integer.MIN_VALUE);

    public AbstractPidController makeMashTunPid(BigDecimal value) {
        Integer id = Integer.valueOf(1);
        return makePid(value, id);
    }

    public AbstractPidController makeBoilKettlePid(BigDecimal value) {
        Integer id = Integer.valueOf(2);
        return makePid(value, id);
    }

    public AbstractPidController makeHotLiquorPid(BigDecimal value) {
        Integer id = Integer.valueOf(3);
        return makePid(value, id);
    }

    public AbstractPidController makeFermenterPid(BigDecimal value) {
        Integer id = Integer.valueOf(4);
        return makePid(value, id);
    }

    public AbstractPidController makeCellarPid(BigDecimal value) {
        Integer id = Integer.valueOf(5);
        return makePid(value, id);
    }

    @NotNull
    public AbstractPidController makePid(final BigDecimal value, final Integer id) {
        AbstractPidController controller = new AbstractPidController(PidControllerID.of(id)) {

            @Override
            public String getName() {
                return "Test PID";
            }

            @Override
            public BigDecimal readProcessValue() throws Exception {
                if(value.equals(ERROR_SIGNAL)) {
                    throw new Exception("Error reading process value");
                }
                return value;
            }

            @Override
            public BigDecimal readSetpoint() throws Exception {
                return value;
            }

            @Override
            public String toString() {
                return new StringJoiner(", ", PidController.class.getSimpleName() + "[", "]")
                        .add("id=" + getId())
                        .add("name=" + getName())
                        .toString();
            }
        };
        return controller;
    }

    @NotNull
    public AbstractPidController makeFailing(final Exception exception, final Integer id) {
        AbstractPidController controller = new AbstractPidController(PidControllerID.of(id)) {

            @Override
            public String getName() {
                return "Test PID";
            }

            @Override
            public BigDecimal readProcessValue() throws Exception {
                throw exception;
            }

            @Override
            public BigDecimal readSetpoint() throws Exception {
                throw exception;
            }

            @Override
            public String toString() {
                return new StringJoiner(", ", PidController.class.getSimpleName() + "[", "]")
                        .add("id=" + getId())
                        .add("name=" + getName())
                        .toString();
            }
        };
        return controller;
    }
}
