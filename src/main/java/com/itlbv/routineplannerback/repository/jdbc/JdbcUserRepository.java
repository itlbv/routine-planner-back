package com.itlbv.routineplannerback.repository.jdbc;

import com.itlbv.routineplannerback.model.User;
import com.itlbv.routineplannerback.repository.UserRepository;
import com.itlbv.routineplannerback.repository.jdbc.util.JdbcConversionUtil;
import com.itlbv.routineplannerback.repository.jdbc.util.JdbcUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static com.itlbv.routineplannerback.repository.jdbc.util.JdbcConversionUtil.convertToUser;

@Repository
public class JdbcUserRepository implements UserRepository {
    private static final BeanPropertyRowMapper<JdbcUser> ROW_MAPPER = BeanPropertyRowMapper.newInstance(JdbcUser.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert simpleInsert;

    @Autowired
    public JdbcUserRepository(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.simpleInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public User save(User user) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("name", user.getName())
                .addValue("last_name", user.getLastName())
                .addValue("email", user.getEmail())
                .addValue("reg_date_time", formatDate(user.getRegistrationDateTime()));
        if (user.isNew()) {
            Number newId = simpleInsert.executeAndReturnKey(map);
            user.setId(newId.intValue());
        } else if (namedParameterJdbcTemplate.update(
                "UPDATE users" +
                        " SET name = :name," +
                        " last_name =:last_name, " +
                        " email = :email," +
                        " reg_date_time = :reg_date_time" +
                        " WHERE id = :id", map) == 0) {
            return null;
        }
        return user;
    }

    private String formatDate(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    public boolean delete(int id) {
        return (jdbcTemplate.update("DELETE FROM users WHERE id=?", id)) > 0;
    }

    @Override
    public User get(int id) {
        List<JdbcUser> jdbcUserList = jdbcTemplate.query("SELECT * FROM users WHERE id=?", ROW_MAPPER, id);
        JdbcUser jdbcUser = DataAccessUtils.singleResult(jdbcUserList);
        if (jdbcUser == null) {
            return null;
        }
        return convertToUser(jdbcUser);
    }

    @Override
    public User getByEmail(String email) { //TODO get rid of the same code wit get()
        List<JdbcUser> jdbcUserList = jdbcTemplate.query("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
        JdbcUser jdbcUser = DataAccessUtils.singleResult(jdbcUserList);
        if (jdbcUser == null) {
            return null;
        }
        return convertToUser(jdbcUser);
    }

    @Override
    public List<User> getAll() {
        List<JdbcUser> jdbcUserList = jdbcTemplate.query("SELECT * FROM users", ROW_MAPPER);
        return jdbcUserList.stream()
                .map(JdbcConversionUtil::convertToUser)
                .collect(Collectors.toList());
    }
}
