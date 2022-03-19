package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.domain.ControllerStatus;
import io.github.nguba.lunanera.domain.ControllerStatusReceived;
import io.github.nguba.lunanera.domain.VesselId;
import io.github.nguba.lunanera.domain.controller.When;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class ControllerStatusRepository {

    private final DataSource dataSource;

    private static final String INSERT = "INSERT INTO status (value, batch_id, pid_id, date) VALUES (?,?,?,?)";

    public ControllerStatusRepository(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(ControllerStatusReceived event) throws SQLException {

        try (Connection c = dataSource.getConnection(); PreparedStatement stmt = c.prepareStatement(INSERT)) {
            stmt.setString(1, event.value().toString());
            stmt.setObject(2, event.batchId());
            stmt.setInt(3, event.vesselId().value());
            stmt.setObject(4, event.when().value());
            stmt.executeUpdate();
        }
    }

    public Optional<ControllerStatusReceived> find(final VesselId vesselId) throws SQLException {
        try (Connection c = dataSource.getConnection();
             PreparedStatement stmt = c.prepareStatement("SELECT value, batch_id, date FROM status WHERE pid_id=?")) {
            stmt.setInt(1, vesselId.value());

            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()) {
                    String value = rs.getString("value");
                    UUID batchId = rs.getObject("batch_id", UUID.class);
                    LocalDateTime when = rs.getObject("date", LocalDateTime.class);
                    return Optional.of(
                            ControllerStatusReceived.with(ControllerStatus.valueOf(value), vesselId, batchId, new When(when)));
                }
            }
        }
        return Optional.empty();
    }
}
