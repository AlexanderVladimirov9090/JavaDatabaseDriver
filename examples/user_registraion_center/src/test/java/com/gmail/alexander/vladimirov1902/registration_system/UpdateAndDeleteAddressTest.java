package com.gmail.alexander.vladimirov1902.registration_system;

import com.gmail.alexander.vladimirov1902.registration_system.domain_layer.Address;
import com.gmail.alexander.vladimirov1902.registration_system.domain_layer.AddressRepository;
import com.gmail.alexander.vladimirov1902.registration_system.persistence_layer.DataStore;
import com.gmail.alexander.vladimirov1902.registration_system.persistence_layer.PersistenceAddressRepository;
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
public class UpdateAndDeleteAddressTest {
    @Rule
    public DataBaseConnectionRule dataBaseConnectionRule = new DataBaseConnectionRule();
    @Rule
    public DatabaseTableRule databaseTableRule = new DatabaseTableRule(new DataStore(dataBaseConnectionRule.connection), new LinkedList<String>() {{
        add("Address");
    }});
    private AddressRepository addressRepository = new PersistenceAddressRepository(new DataStore(dataBaseConnectionRule.connection));

    public UpdateAndDeleteAddressTest() throws SQLException {
    }

    @Test
    public void deleteAddress() {
        addressRepository.register(new Address(null, "SomeCountry", "SomeCity", "SomeStreet"));
        addressRepository.delete(new Address(1, "SomeCountry", "SomeCity", "SomeStreet"));
        List<Address> actualEmpty = addressRepository.getAll();
        assertThat(actualEmpty.isEmpty(), is(true));
    }

    @Test
    public void updateAddress() {
        Address expected = new Address(1, "NewCountry", "NewCity", "NewStreet");
        addressRepository.register(new Address(null, "OldCountry", "OldCity", "OldStreet"));
        addressRepository.update(new Address(1, "NewCountry", "NewCity", "NewStreet"));
        List<Address> addresses = addressRepository.getAll();
        Address actual = addresses.get(0);
        assertThat(actual, is(equalTo(expected)));
    }
}