package vladimirov.alexander.registration_system;

import com.google.common.collect.Lists;
import org.junit.Rule;
import org.junit.Test;
import vladimirov.alexander.registration_system.domain_layer.User;
import vladimirov.alexander.registration_system.domain_layer.UserRepository;
import vladimirov.alexander.registration_system.persistence_layer.DataStore;
import vladimirov.alexander.registration_system.persistence_layer.PersistenceUserRepository;

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
public class RegisterUserTest {
    @Rule
    public DataBaseConnectionRule dataBaseConnectionRule = new DataBaseConnectionRule();
    @Rule
    public DatabaseTableRule databaseTableRule = new DatabaseTableRule(new DataStore(dataBaseConnectionRule.connection), new LinkedList<String>() {{
        add("Users");
    }});
    private UserRepository userRepository = new PersistenceUserRepository(new DataStore(dataBaseConnectionRule.connection));

    public RegisterUserTest() throws SQLException {
    }


    @Test
    public void registerUser() {
        userRepository.register(new User(1, "Ok"));
        List<User> expected = Lists.newArrayList();
        expected.add(new User(1, "Ok"));
        List<User> actual = userRepository.getAll();
        assertThat(actual, is(equalTo(expected)));
    }
}