package io.github.nguba.lunanera.infrastructure;

import io.github.nguba.lunanera.domain.ProcessValue;

public interface TemperatureReader {

    ProcessValue readTemperature();
}
