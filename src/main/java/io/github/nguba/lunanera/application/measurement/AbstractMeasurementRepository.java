package io.github.nguba.lunanera.application.measurement;

import io.github.nguba.lunanera.domain.Measurement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public abstract class AbstractMeasurementRepository<T extends Measurement> {
    protected final String tableName;
    protected DataSource ds;
    protected Map<String, String> queries = new HashMap<>();

    public AbstractMeasurementRepository(String tableName, final DataSource ds) {
        this.tableName = tableName;
        this.ds = ds;
        queries.put("add",
                String.format("INSERT INTO %s (process_value, batch_id, pid_id, date) VALUES (?,?,?,?)", tableName));
        queries.put("read",
                String.format("SELECT process_value, batch_id, pid_id, date FROM %s WHERE batch_id=? ORDER BY date", tableName));
        queries.put("delete_batch",
                String.format("DELETE FROM %s WHERE batch_id=?", tableName));
    }

    public void save(T measurement) throws SQLException {
        try (Connection c = ds.getConnection(); PreparedStatement stmt = c.prepareStatement(queries.get("add"))) {
            configureSaveStatement(measurement, stmt);
            stmt.setObject(2, measurement.getBatchId());
            stmt.setInt(3, measurement.getDeviceId().value());
            stmt.setObject(4, measurement.getWhen().value());
            stmt.executeUpdate();
        }
    }

    protected abstract void configureSaveStatement(final T measurement, final PreparedStatement stmt) throws SQLException;

    public List<T> read(final UUID batchId) throws SQLException {
        final List<T> results = new LinkedList<>();
        try (Connection c = ds.getConnection(); PreparedStatement stmt = c.prepareStatement(queries.get("read"))) {
            stmt.setObject(1, batchId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int pidId = rs.getInt(3);
                    LocalDateTime when = rs.getObject(4, LocalDateTime.class);
                    T measurement = rehydrateMeasurement(batchId, rs, pidId, when);
                    results.add(measurement);
                }
            }
        }
        return Collections.unmodifiableList(results);
    }

    protected abstract T rehydrateMeasurement(final UUID batchId, final ResultSet rs, final int pidId, final LocalDateTime when) throws SQLException;

    public void delete(final UUID batchId) throws SQLException {
        try (Connection c = ds.getConnection();
             PreparedStatement stmt = c.prepareStatement(queries.get("delete_batch"))) {
            stmt.setObject(1, batchId);
            stmt.executeUpdate();
        }
    }

    public String getTableName() {
        return tableName;
    }
}
