package com.gmail.alexander.vladimirov1902.registration_system;

import com.gmail.alexander.vladimirov1902.registration_system.domain_layer.User;
import com.gmail.alexander.vladimirov1902.registration_system.domain_layer.UserRepository;
import org.junit.Rule;
import org.junit.Test;
import com.gmail.alexander.vladimirov1902.registration_system.persistence_layer.DataStore;
import com.gmail.alexander.vladimirov1902.registration_system.persistence_layer.PersistenceUserRepository;

import java.sql.SQLException;
import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by zumba on 30.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class GetUserTest {
    @Rule
    public DataBaseConnectionRule dataBaseConnectionRule = new DataBaseConnectionRule();
    @Rule
    public DatabaseTableRule databaseTableRule = new DatabaseTableRule(new DataStore(dataBaseConnectionRule.connection), new LinkedList<String>() {{
        add("Users");
    }});
    private UserRepository userRepository = new PersistenceUserRepository(new DataStore(dataBaseConnectionRule.connection));

    public GetUserTest() throws SQLException {
    }


    @Test
    public void getUserById() {
        userRepository.register(new User(1, "Ok"));

        User expected = new User(1, "Ok");
        User actual = userRepository.byId(1);
        assertThat(actual, is(equalTo(expected)));
    }
}
