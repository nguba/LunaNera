package io.github.nguba.lunanera.infrastructure.brewersfriend;

public record FermentationEntry(String device_source, String name, float temp,
                                String temp_unit, float gravity, String gravity_unit) {

    public FermentationEntry(float temp, float gravity) {
        this("RedLion PXU", "LunaNera V", temp, "C", gravity, "P");
    }
}
