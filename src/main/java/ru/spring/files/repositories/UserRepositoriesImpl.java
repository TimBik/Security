package ru.spring.files.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.spring.files.model.Role;
import ru.spring.files.model.State;
import ru.spring.files.model.User;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class UserRepositoriesImpl implements UserRepositories {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_BY_ID = "select * from users where id = ?;";

    private static final String SQL_SELECT_ALL = "select * from users";

    private static final String SQL_INSERT = "insert into users(name, password, email, state, role, confirmcode) values (?,?,?,?,?,?)";

    private static final String SQL_DELETE_BY_ID = "delete from users where id=?;";

    private static final String SQL_SELECT_BY_EMAIL = "select * from users where email=?;";

    private static final String SQL_SELECT_BY_CONFIRM = "select * from users where confirmcode=?;";

    private static final String SQL_UPDATE_STATE = "update users set state = 'CONFIRMED' where confirmcode = ?;";

    private RowMapper<User> userRowMapper = (row, rowNumber) ->
            User.builder().id(row.getLong("id"))
                    .name(row.getString("name"))
                    .password(row.getString("password"))
                    .email(row.getString("email"))
                    .state(State.valueOf(row.getString("state")))
                    .role(Role.valueOf(row.getString("role")))
                    .confirmCode(row.getString("confirmcode"))
                    .build();

    public UserRepositoriesImpl() {
    }


    @Override
    public Optional<User> find(Long id) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, userRowMapper);
            return Optional.ofNullable(user);
        } catch (
                EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void save(User object) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT);
            statement.setString(1, object.getName());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getEmail());
            statement.setString(4, object.getState().toString());
            statement.setString(5, object.getRole().toString());
            statement.setString(6,object.getConfirmCode());
            return statement;
        }, keyHolder);

        object.setId((Long)keyHolder.getKey());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID,id);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }


    @Override
    public Optional<User> getUserByLogin(String email) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, new Object[]{email}, userRowMapper);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByConfirmCode(String confirmCode) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_SELECT_BY_CONFIRM, new Object[]{confirmCode}, userRowMapper);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(String confirm) {
        jdbcTemplate.update(SQL_UPDATE_STATE, new Object[] {confirm});
    }


}
