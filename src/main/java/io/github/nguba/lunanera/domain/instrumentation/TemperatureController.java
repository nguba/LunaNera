package io.github.nguba.lunanera.domain.instrumentation;

import io.github.nguba.lunanera.application.LunaNeraConfig;
import io.github.nguba.lunanera.domain.ProcessValue;
import io.github.nguba.lunanera.domain.Setpoint;
import io.github.nguba.lunanera.domain.VesselId;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *     These are usually PID controller from various vendors, talking over RS485 to set and retrieve temperature
 *     readings.  Some also support temperature profiles with ramp and soak capabilities.
 * </p>
 * <p>
 *     Temperature controllers have state.  They are either switched on or off.  When switched off it makes no sense
 *     processing any requests for reading or writing.
 * </p>
 * <p>
 *     Temperature controllers have setpoints -- which are the target temperature.  They return the processValue,
 *     which is the current temperature.
 * </p>
 * <p>
 *     Profiles are a collection of segments of temperature over time.  If the setpoint changes over the span of a
 *     segment, it is said to be <sl>ramping</sl>.  If the setpoint remains constant during the a segment, it is said
 *     to be <sl>soaking</sl>
 * </p>
 * <p>
 *     For use in our system, all equipments needs to implement a control interface via RS485 (Modbus).  For
 *     the full specification of the communications interface, consult the
 *     <a href="https://www.redlion.net/sites/default/files/213/6305/PXU%20Manual_0.pdf">PXU Series manual</a>.
 * </p>
 * @see <a href="http://www.redlion.net/products/industrial-automation/controllers-and-data-acquisition/pid-controllers/pxu-pid-controllers">RedLion PXU PID Controllers</a>
 */
public class TemperatureController {

    TemperatureControllerId id;

    public ProcessValue readProcessValue() {
        return null;
    }

    public Setpoint readSetpoint() {
        return null;
    }

    public int readProportionalBand() {
        return 0;
    }

    public int readDerivativeTime() {
        return 0;
    }

    public int readIntegralDefault() {
        return 0;
    }

    public PidGroup readPidGroup() {
        return PidGroup.AUTO;
    }

    public void writePidGroup(PidGroup group) {

    }

    public Boolean isAutoTune() {
        return Boolean.FALSE;
    }

    public void setAutoTune(Boolean value) {

    }

    public ControllerStatus readStatus() {
        return ControllerStatus.OFF;
    }

    public int readCurrentProfile() {
        return 0;
    }

    public int readCurrentProfileSegment() {
        return 0;
    }

    public int readProfileSegmentRemainingTime() {
        return 0;
    }

    public int readStartingSegmentId() {
        return 0;
    }
}
