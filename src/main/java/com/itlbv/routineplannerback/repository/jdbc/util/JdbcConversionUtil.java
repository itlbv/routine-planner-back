package com.itlbv.routineplannerback.repository.jdbc.util;

import com.itlbv.routineplannerback.model.Routine;
import com.itlbv.routineplannerback.model.User;
import org.postgresql.util.PGInterval;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Period;

public class JdbcConversionUtil {

    public static Routine convertToRoutine(JdbcRoutine jdbcRoutine) {
        return new Routine(jdbcRoutine.getId(),
                jdbcRoutine.getName(),
                jdbcRoutine.getDescription(),
                jdbcRoutine.getStartDate(),
                jdbcRoutine.getEndDate(),
                jdbcRoutine.getTimeOfDay(),
                convertIntervalToPeriod(jdbcRoutine.getPgInterval()));
    }

    private static Period convertIntervalToPeriod(PGInterval pgInterval) {
        return Period.ofDays(2);
    }

    public static PGInterval convertPeriodToInterval(Period period) {
        return new PGInterval(period.getYears(),
                period.getMonths(),
                period.getDays(),
                0, 0, 0);
    }

    public static User convertToUser(JdbcUser jdbcUser) {
        return new User(jdbcUser.getId(),
                jdbcUser.getName(),
                jdbcUser.getLastName(),
                jdbcUser.getEmail(),
                convertTimestampToLocalDateTime(jdbcUser.getRegDateTime()));
    }

    private static LocalDateTime convertTimestampToLocalDateTime(Timestamp timestamp) {
        return timestamp.toLocalDateTime();
    }
}
