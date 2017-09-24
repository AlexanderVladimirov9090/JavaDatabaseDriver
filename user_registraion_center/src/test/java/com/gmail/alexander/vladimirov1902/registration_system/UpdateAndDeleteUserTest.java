package com.gmail.alexander.vladimirov1902.registration_system;

import com.gmail.alexander.vladimirov1902.registration_system.domain_layer.User;
import com.gmail.alexander.vladimirov1902.registration_system.domain_layer.UserRepository;
import com.gmail.alexander.vladimirov1902.registration_system.persistence_layer.DataStore;
import com.gmail.alexander.vladimirov1902.registration_system.persistence_layer.PersistenceUserRepository;
import org.junit.Rule;
import org.junit.Test;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by zumba on 30.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class UpdateAndDeleteUserTest {
    @Rule
    public DataBaseConnectionRule dataBaseConnectionRule = new DataBaseConnectionRule();
    @Rule
    public DatabaseTableRule databaseTableRule = new DatabaseTableRule(new DataStore(dataBaseConnectionRule.connection), new LinkedList<String>() {{
        add("Users");
    }});
    private UserRepository userRepository = new PersistenceUserRepository(new DataStore(dataBaseConnectionRule.connection));

    public UpdateAndDeleteUserTest() throws SQLException {
    }

    @Test
    public void delete() {
        userRepository.register(new User(null, "Hello"));
        userRepository.delete(new User(1, "Hello"));
        List<User> users = userRepository.getAll();
        assertThat(users.size(), is(equalTo(0)));
    }

    @Test
    public void update() {
        User expected = new User(1,"New");
        userRepository.register(new User(null,"Old"));
        userRepository.update(new User(1,"New"));
        List<User> users = userRepository.getAll();
        User actual = users.get(0);
        assertThat(actual.equals(expected),is(true));
    }
}