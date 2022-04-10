package io.github.nguba.lunanera.domain;

import io.github.nguba.lunanera.EqualityVerifier;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HexFormat;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProcessValueTest implements EqualityVerifier<ProcessValue> {

    Instant timestamp = Instant.ofEpochSecond(1);

    @Override
    public Class<? extends ProcessValue> typeClass() {
        return ProcessValue.class;
    }

    @Test
    void hasExpectedValue() {
        assertThat(makeValueObject().value()).isEqualTo(Float.valueOf(1234));
    }


    private ProcessValue makeValueObject() {
        return ProcessValue.of(1234);
    }

    @Test
    void toStringRepresentation() {
        assertThat(makeValueObject().toString()).isEqualTo("ProcessValue[value=1234.0]");
    }

    @Test
    void hasComparatorOrderingByTimestamp() {
//        Set<ProcessValue> items = new TreeSet<>();
//        items.add(ProcessValue.with(1f, Instant.ofEpochSecond(10)));
//        items.add(ProcessValue.with(1f, Instant.ofEpochSecond(1)));
//        items.add(ProcessValue.with( 1f, Instant.ofEpochSecond(5)));
//
//        assertThat(items.toString()).isEqualTo("[1.0:1000, 1.0:5000, 1.0:10000]");

        byte[] bytes = HexFormat.of().parseHex("5058");
        System.out.println(new String(bytes, StandardCharsets.UTF_8));
    }
}