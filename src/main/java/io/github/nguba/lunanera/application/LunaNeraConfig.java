package io.github.nguba.lunanera.application;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@ConfigurationProperties("lunanera")
public class LunaNeraConfig {

    private SerialConfig serial = new SerialConfig();

    private List<Pid> pid = new ArrayList<>();

    @Override
    public String toString() {
        return new StringJoiner(", ", LunaNeraConfig.class.getSimpleName() + "[", "]")
                .add("serial=" + serial)
                .add("pid=" + pid)
                .toString();
    }

    public SerialConfig getSerial() {
        return serial;
    }

    public void setSerial(final SerialConfig serial) {
        this.serial = serial;
    }

    @ConfigurationProperties("serial")
    public static final class SerialConfig {

        private int baudRate;

        private int dataBits;

        private String device;

        public int getDataBits() {
            return dataBits;
        }

        public void setDataBits(final int dataBits) {
            this.dataBits = dataBits;
        }

        public int getBaudRate() {
            return baudRate;
        }

        public void setBaudRate(final int baudRate) {
            this.baudRate = baudRate;
        }

        public String getDevice() {
            return device;
        }

        public void setDevice(final String device) {
            this.device = device;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", SerialConfig.class.getSimpleName() + "[", "]")
                    .add("baudRate=" + baudRate)
                    .add("dataBits=" + dataBits)
                    .add("device='" + device + "'")
                    .toString();
        }
    }

    public List<Pid> getPid() {
        return pid;
    }

    public void setPid(final List<Pid> pid) {
        this.pid = pid;
    }

    @ConfigurationProperties("pid")
    public static class Pid {
        private String name;

        public int rate = 5;

        private Integer id;

        private boolean persisted;

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(final Integer id) {
            this.id = id;
        }

        public int getRate() {
            return rate;
        }

        public void setRate(final int rate) {
            this.rate = rate;
        }

        public boolean isPersisted() {
            return persisted;
        }

        public void setPersisted(final boolean persisted) {
            this.persisted = persisted;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Pid.class.getSimpleName() + "[", "]")
                    .add("name='" + name + "'")
                    .add("rate=" + rate)
                    .add("vesselId=" + id)
                    .add("persisted=" + persisted)
                    .toString();
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Pid pid = (Pid) o;
            return rate == pid.rate && persisted == pid.persisted && Objects.equals(name, pid.name) && Objects.equals(id, pid.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, rate, id, persisted);
        }
    }
}
