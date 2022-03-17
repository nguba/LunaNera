package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.domain.VesselId;
import io.github.nguba.lunanera.domain.ProcessValue;
import io.github.nguba.lunanera.domain.ProcessValueMeasurement;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class ProcessValueMeasurementRepository extends AbstractMeasurementRepository<ProcessValueMeasurement> {

    public ProcessValueMeasurementRepository(final DataSource ds) {
        super("temperatures", ds);
    }

    @Override
    protected void persistMeasurement(final ProcessValueMeasurement measurement, final PreparedStatement stmt) throws SQLException {
        stmt.setFloat(1, measurement.getValue().value());
    }

    @Override
    protected ProcessValueMeasurement rehydrateMeasurement(final UUID batchId, final ResultSet rs, final int pidId, final LocalDateTime when) throws SQLException {
        float pv = rs.getFloat(1);
        return new ProcessValueMeasurement(ProcessValue.of(pv), when, VesselId.of(pidId), batchId);
    }
}
