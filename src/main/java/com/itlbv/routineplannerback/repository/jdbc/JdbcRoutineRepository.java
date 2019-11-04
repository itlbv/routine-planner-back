package com.itlbv.routineplannerback.repository.jdbc;

import com.itlbv.routineplannerback.model.Routine;
import com.itlbv.routineplannerback.repository.RoutineRepository;
import com.itlbv.routineplannerback.repository.jdbc.util.JdbcConversionUtil;
import com.itlbv.routineplannerback.repository.jdbc.util.JdbcRoutine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

import static com.itlbv.routineplannerback.repository.jdbc.util.JdbcConversionUtil.convertPeriodToInterval;
import static com.itlbv.routineplannerback.repository.jdbc.util.JdbcConversionUtil.convertToRoutine;

@Repository
public class JdbcRoutineRepository implements RoutineRepository {

    private static final BeanPropertyRowMapper<JdbcRoutine> ROW_MAPPER = BeanPropertyRowMapper.newInstance(JdbcRoutine.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert simpleInsert;

    @Autowired
    public JdbcRoutineRepository(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.simpleInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("routines")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Routine save(Routine routine, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", routine.getId())
                .addValue("user_id", userId)
                .addValue("name", routine.getName())
                .addValue("description", routine.getDescription())
                .addValue("start_date", routine.getStartDate())
                .addValue("end_date", routine.getEndDate())
                .addValue("time_of_day", routine.getTimeOfDay())
                .addValue("period", convertPeriodToInterval(routine.getPeriod()));
        if (routine.isNew()) {
            Number newId = simpleInsert.executeAndReturnKey(map);
            routine.setId(newId.intValue());
        } else if (namedParameterJdbcTemplate.update(
                "UPDATE routines " +
                        " SET name = :name, " +
                        " description = :description, " +
                        " start_date = :start_date, " +
                        " end_date = :end_date, " +
                        " time_of_day = :time_of_day, " +
                        " period = :period " +
                        "WHERE id = :id AND user_id = :user_id", map) == 0) {
            return null;
        }
        return routine;
    }

    @Override
    public boolean delete(int id, int userId) {
        return (jdbcTemplate.update("DELETE FROM routines WHERE id=? AND user_id=?", id, userId)) > 0;
    }

    @Override
    public Routine get(int id, int userId) {
        List<JdbcRoutine> jdbcRoutineList = jdbcTemplate.query("SELECT * FROM routines WHERE id=? AND user_id=?", ROW_MAPPER, id, userId);
        JdbcRoutine jdbcRoutine = DataAccessUtils.singleResult(jdbcRoutineList);
        if (jdbcRoutine == null) {
            return null;
        }
        return convertToRoutine(jdbcRoutine);
    }

    @Override
    public List<Routine> getAll(int userId) {
        List<JdbcRoutine> jdbcRoutineList = jdbcTemplate.query("SELECT * FROM routines WHERE user_id=?", ROW_MAPPER, userId);
        return jdbcRoutineList.stream()
                .map(JdbcConversionUtil::convertToRoutine)
                .collect(Collectors.toList());
    }
}
