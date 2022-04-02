package io.github.nguba.lunanera.application.measurement;

import io.github.nguba.lunanera.domain.*;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ProcessValueMeasurementRepository extends AbstractMeasurementRepository<ProcessValueMeasurement> {

    public ProcessValueMeasurementRepository(final DataSource ds) {
        super("temperatures", ds);
    }

    @Override
    protected void configureSaveStatement(final ProcessValueMeasurement measurement, final PreparedStatement stmt) throws SQLException {
        stmt.setFloat(1, measurement.getValue().value());
    }

    @Override
    protected ProcessValueMeasurement rehydrateMeasurement(final BatchId batchId, final ResultSet rs, final int pidId, final LocalDateTime localDateTime) throws SQLException {
        float pv = rs.getFloat(1);
        return new ProcessValueMeasurement(ProcessValue.of(pv), new When(localDateTime), VesselId.of(pidId), batchId);
    }
}
