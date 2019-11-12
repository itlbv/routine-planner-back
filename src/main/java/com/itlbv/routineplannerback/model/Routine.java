package com.itlbv.routineplannerback.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.Objects;

public class Routine extends AbstractEntity {

    private String description;
    private LocalDate startDate, endDate;
    private LocalTime timeOfDay;
    private Period period;

    public Routine() {
    }

    public Routine(Integer id, String name, String description,
                   LocalDate startDate, LocalDate endDate, LocalTime timeOfDay, Period period) {
        super(id, name);
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timeOfDay = timeOfDay;
        this.period = period;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(LocalTime timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "Routine{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", timeOfDay=" + timeOfDay +
                ", period=" + period +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Routine)) return false;
        if (!super.equals(o)) return false;

        Routine routine = (Routine) o;

        if (!Objects.equals(description, routine.description)) return false;
        if (!startDate.equals(routine.startDate)) return false;
        if (!endDate.equals(routine.endDate)) return false;
        if (!timeOfDay.equals(routine.timeOfDay)) return false;
        return Objects.equals(period, routine.period);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + timeOfDay.hashCode();
        result = 31 * result + (period != null ? period.hashCode() : 0);
        return result;
    }
}