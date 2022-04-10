package io.github.nguba.lunanera.domain.instrumentation;

// Read register 18
// 0: Stop, 1: Run, 2 = End (SetpointProfile mode), 3 = Pause
//(SetpointProfile mode), 4 = Advance SetpointProfile (SetpointProfile mode)
public enum ControllerStatus {
    STOP(0), RUN(1), END(2), PAUSE(3), ADVANCE(4), OFF(5);

    int value;

    ControllerStatus(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static ControllerStatus of(int value) {
        switch (value) {
            case 0: return STOP;
            case 1: return RUN;
            case 2: return END;
            case 3: return PAUSE;
            case 4: return ADVANCE;
            case 5: return OFF;
            default:
                throw new IllegalArgumentException("No defined status for value=" + value);
        }
    }
}
