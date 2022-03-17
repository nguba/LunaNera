package io.github.nguba.lunanera.infrastructure;

import io.github.nguba.lunanera.domain.Vessel;
import io.github.nguba.lunanera.domain.VesselId;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.StringJoiner;

public enum PidControllerTestFactory {
    INSTANCE;

    public static final BigDecimal ERROR_SIGNAL = BigDecimal.valueOf(Integer.MIN_VALUE);

    public Vessel makeMashTunPid(BigDecimal value) {
        return makePid(value, 1);
    }

    public Vessel makeBoilKettlePid(BigDecimal value) {
        return makePid(value, 2);
    }

    public Vessel makeHotLiquorPid(BigDecimal value) {
        return makePid(value, 3);
    }

    public Vessel makeFermenterPid(BigDecimal value) {
        return makePid(value, 4);
    }

    public Vessel makeCellarPid(BigDecimal value) {
        return makePid(value, 5);
    }

    @NotNull
    public Vessel makePid(final BigDecimal value, final Integer id) {
        return new Vessel(VesselId.of(id)) {

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
                return new StringJoiner(", ", Vessel.class.getSimpleName() + "[", "]")
                        .add("vesselId=" + getId())
                        .add("name=" + getName())
                        .toString();
            }
        };
    }

    @NotNull
    public Vessel makeFailing(final Exception exception, final Integer id) {
        return new Vessel(VesselId.of(id)) {

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
                return new StringJoiner(", ", Vessel.class.getSimpleName() + "[", "]")
                        .add("vesselId=" + getId())
                        .add("name=" + getName())
                        .toString();
            }
        };
    }
}
