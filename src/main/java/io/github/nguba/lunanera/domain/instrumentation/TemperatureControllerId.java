package io.github.nguba.lunanera.domain.instrumentation;

/**
 * Each temperature controller has to have a unique id less than 247 (constraint of the Modbus implementation).
 *
 * @param value
 */
public record TemperatureControllerId(int value) {
}
