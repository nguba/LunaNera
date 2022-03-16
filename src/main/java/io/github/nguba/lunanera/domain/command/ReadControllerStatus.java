package io.github.nguba.lunanera.domain.command;

// Read register 18
// 0: Stop, 1: Run, 2 = End (Profile mode), 3 = Pause
//(Profile mode), 4 = Advance Profile (Profile mode)
public class ReadControllerStatus implements ModbusCommand {

    @Override
    public void execute(){
    }
}
