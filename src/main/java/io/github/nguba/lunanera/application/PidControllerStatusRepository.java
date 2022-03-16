package io.github.nguba.lunanera.application;

import io.github.nguba.lunanera.domain.PidControllerStatus;

import javax.sql.DataSource;
import java.util.LinkedList;
import java.util.List;

public class PidControllerStatusRepository {
    private final DataSource dataSource;

    public PidControllerStatusRepository(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(final PidControllerStatus status) {
    }

    public List<PidControllerStatus> findAll() {
        return new LinkedList<>();
    }
}
