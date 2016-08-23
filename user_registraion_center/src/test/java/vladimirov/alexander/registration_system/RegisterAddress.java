package vladimirov.alexander.registration_system;

import com.google.common.collect.Lists;
import org.junit.Rule;
import org.junit.Test;
import vladimirov.alexander.registration_system.domain_layer.Address;
import vladimirov.alexander.registration_system.domain_layer.AddressRepository;
import vladimirov.alexander.registration_system.persistence_layer.DataStore;
import vladimirov.alexander.registration_system.persistence_layer.PersistenceAddressRepository;

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
public class RegisterAddress {
    @Rule
    public DataBaseConnectionRule dataBaseConnectionRule = new DataBaseConnectionRule();
    @Rule
    public DatabaseTableRule databaseTableRule = new DatabaseTableRule(new DataStore(dataBaseConnectionRule.connection), new LinkedList<String>() {{
        add("Address");
    }});
    private AddressRepository addressRepository= new PersistenceAddressRepository(new DataStore(dataBaseConnectionRule.connection));


    public RegisterAddress() throws SQLException {
    }
    @Test
    public void registerAddress(){
        addressRepository.register(new Address(null,"Bulgaria","City","Street"));
        List<Address> expected = Lists.newArrayList();
        expected.add(new Address(1,"Bulgaria","City","Street"));
        List<Address> actual = addressRepository.getAll();
        assertThat(actual,is(equalTo(expected)));
    }
}