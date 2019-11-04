package com.itlbv.routineplannerback.repository.jdbc.util;

import com.itlbv.routineplannerback.model.AbstractEntity;
import org.postgresql.util.PGInterval;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class JdbcRoutine extends AbstractEntity {

    private String description;
    private LocalDate startDate, endDate;
    private LocalTime timeOfDay;
    private PGInterval pgInterval;

    public JdbcRoutine() {
        super();
    }

    public JdbcRoutine(Integer id, String name, String description, LocalDate startDate, LocalDate endDate, LocalTime timeOfDay, PGInterval pgInterval) {
        super(id, name);
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timeOfDay = timeOfDay;
        this.pgInterval = pgInterval;
    }

    String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    LocalTime getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(LocalTime timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    PGInterval getPgInterval() {
        return pgInterval;
    }

    public void setPgInterval(PGInterval pgInterval) {
        this.pgInterval = pgInterval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JdbcRoutine)) return false;
        if (!super.equals(o)) return false;

        JdbcRoutine that = (JdbcRoutine) o;

        if (!Objects.equals(description, that.description)) return false;
        if (!startDate.equals(that.startDate)) return false;
        if (!endDate.equals(that.endDate)) return false;
        if (!timeOfDay.equals(that.timeOfDay)) return false;
        return Objects.equals(pgInterval, that.pgInterval);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + timeOfDay.hashCode();
        result = 31 * result + (pgInterval != null ? pgInterval.hashCode() : 0);
        return result;
    }
}
