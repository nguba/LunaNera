package io.github.nguba.lunanera.application.measurement;

import io.github.nguba.lunanera.domain.*;
import io.github.nguba.lunanera.domain.When;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class SetpointMeasurementRepository extends AbstractMeasurementRepository<SetpointMeasurement> {

    public SetpointMeasurementRepository(final DataSource dataSource) {
        super("setpoint", dataSource);
        queries.put("add",
                String.format("INSERT INTO %s (value, batch_id, pid_id, date) VALUES (?,?,?,?)", tableName));
        queries.put("read",
                String.format("SELECT value, batch_id, pid_id, date FROM %s WHERE batch_id=? ORDER BY date", tableName));
    }

    @Override
    protected void configureSaveStatement(final SetpointMeasurement measurement, final PreparedStatement stmt) throws SQLException {
        stmt.setFloat(1, measurement.getValue().value());
    }

    @Override
    protected SetpointMeasurement rehydrateMeasurement(final UUID batchId, final ResultSet rs, final int pidId, final LocalDateTime localDateTime) throws SQLException {
        float pv = rs.getFloat(1);
        return new SetpointMeasurement(Setpoint.of(pv), new When(localDateTime), VesselId.of(pidId), batchId);
    }

    public int delete(int pidId) throws SQLException {
        try (Connection c = ds.getConnection();
             PreparedStatement stmt = c.prepareStatement("DELETE FROM setpoint WHERE pid_id=?")) {
            stmt.setInt(1, pidId);
            return stmt.executeUpdate();
        }
    }
}
