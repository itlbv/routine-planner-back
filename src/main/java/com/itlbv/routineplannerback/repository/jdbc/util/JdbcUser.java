package com.itlbv.routineplannerback.repository.jdbc.util;

import com.itlbv.routineplannerback.model.AbstractEntity;

import java.sql.Timestamp;

public class JdbcUser extends AbstractEntity {
    private String email;
    private Timestamp regDateTime;

    public JdbcUser() {
        super();
    }

    public JdbcUser(Integer id, String name, String email, Timestamp regDateTime) {
        super(id, name);
        this.email = email;
        this.regDateTime = regDateTime;
    }

    String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    Timestamp getRegDateTime() {
        return regDateTime;
    }

    public void setRegDateTime(Timestamp regDateTime) {
        this.regDateTime = regDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JdbcUser)) return false;
        if (!super.equals(o)) return false;

        JdbcUser jdbcUser = (JdbcUser) o;

        if (!email.equals(jdbcUser.email)) return false;
        return regDateTime.equals(jdbcUser.regDateTime);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + regDateTime.hashCode();
        return result;
    }
}
