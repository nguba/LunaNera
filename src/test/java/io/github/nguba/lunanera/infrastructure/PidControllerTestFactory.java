package io.github.nguba.lunanera.infrastructure;

import io.github.nguba.lunanera.domain.PidController;
import io.github.nguba.lunanera.domain.VesselId;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.StringJoiner;

public enum PidControllerTestFactory {
    INSTANCE;

    public static final BigDecimal ERROR_SIGNAL = BigDecimal.valueOf(Integer.MIN_VALUE);

    public PidController makeMashTunPid(BigDecimal value) {
        return makePid(value, 1);
    }

    public PidController makeBoilKettlePid(BigDecimal value) {
        return makePid(value, 2);
    }

    public PidController makeHotLiquorPid(BigDecimal value) {
        return makePid(value, 3);
    }

    public PidController makeFermenterPid(BigDecimal value) {
        return makePid(value, 4);
    }

    public PidController makeCellarPid(BigDecimal value) {
        return makePid(value, 5);
    }

    @NotNull
    public PidController makePid(final BigDecimal value, final Integer id) {
        return new PidController(VesselId.of(id)) {

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
                        .add("vesselId=" + getId())
                        .add("name=" + getName())
                        .toString();
            }
        };
    }

    @NotNull
    public PidController makeFailing(final Exception exception, final Integer id) {
        return new PidController(VesselId.of(id)) {

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
                        .add("vesselId=" + getId())
                        .add("name=" + getName())
                        .toString();
            }
        };
    }
}
