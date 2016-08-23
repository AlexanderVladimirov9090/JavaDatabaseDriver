package vladimirov.alexander.registration_system;

import org.junit.Rule;
import org.junit.Test;
import vladimirov.alexander.registration_system.domain_layer.Address;
import vladimirov.alexander.registration_system.domain_layer.AddressRepository;
import vladimirov.alexander.registration_system.persistence_layer.DataStore;
import vladimirov.alexander.registration_system.persistence_layer.PersistenceAddressRepository;

import java.sql.SQLException;
import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by zumba on 30.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class GetAddress {
    @Rule
    public DataBaseConnectionRule dataBaseConnectionRule = new DataBaseConnectionRule();
    @Rule
    public DatabaseTableRule databaseTableRule = new DatabaseTableRule(new DataStore(dataBaseConnectionRule.connection), new LinkedList<String>() {{
        add("Address");
    }});
    private AddressRepository addressRepository= new PersistenceAddressRepository(new DataStore(dataBaseConnectionRule.connection));


    public GetAddress() throws SQLException {
    }
    @Test
    public void getAddressById(){
     addressRepository.register(new Address(null,"SomeCountry","SomeCity","SomeStreet"));
    Address expected = new Address(null,"SomeCountry","SomeCity","SomeStreet");
     Address actual = addressRepository.byId(1);
        assertThat(actual.equals(expected),is(true));
    }
}
