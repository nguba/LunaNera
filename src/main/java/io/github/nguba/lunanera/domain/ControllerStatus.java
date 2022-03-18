package io.github.nguba.lunanera.domain;

// Read register 18
// 0: Stop, 1: Run, 2 = End (Profile mode), 3 = Pause
//(Profile mode), 4 = Advance Profile (Profile mode)
public enum ControllerStatus {
    STOP, RUN, END, PAUSE, ADVANCE
}
