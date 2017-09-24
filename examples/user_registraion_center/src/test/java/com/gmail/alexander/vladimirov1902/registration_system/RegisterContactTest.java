package com.gmail.alexander.vladimirov1902.registration_system;

import com.gmail.alexander.vladimirov1902.registration_system.domain_layer.*;
import com.google.common.collect.Lists;
import org.junit.Rule;
import org.junit.Test;
import vladimirov.alexander.registration_system.domain_layer.*;
import com.gmail.alexander.vladimirov1902.registration_system.persistence_layer.DataStore;
import com.gmail.alexander.vladimirov1902.registration_system.persistence_layer.PersistenceAddressRepository;
import com.gmail.alexander.vladimirov1902.registration_system.persistence_layer.PersistenceContactRepository;
import com.gmail.alexander.vladimirov1902.registration_system.persistence_layer.PersistenceUserRepository;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by zumba on 29.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class RegisterContactTest {
    @Rule
    public DataBaseConnectionRule dataBaseConnectionRule = new DataBaseConnectionRule();
   @Rule
    public DatabaseTableRule databaseTableRule = new DatabaseTableRule(new DataStore(dataBaseConnectionRule.connection), new LinkedList<String>() {{
        add("Contact");
        add("Address");
        add("Users");
    }});
    private UserRepository userRepository = new PersistenceUserRepository(new DataStore(dataBaseConnectionRule.connection));
    private AddressRepository addressRepository = new PersistenceAddressRepository(new DataStore(dataBaseConnectionRule.connection));
    private ContactRepository contactRepository = new PersistenceContactRepository(new DataStore(dataBaseConnectionRule.connection), userRepository, addressRepository);
    public RegisterContactTest() throws SQLException {
    }

    @Test
    public void registerContact(){
        Contact contact = new Contact(1,new User(null,"User"),"1231231233","email@gmail.com",new Address(1,"a","b","c"));
        List<Contact> expected = Lists.newArrayList();
        expected.add(contact);
        userRepository.register(new User(null,"User"));
        addressRepository.register(new Address(null,"a","b","c"));
        contactRepository.register(new Contact(null ,new User(1,"User"),"1231231233","email@gmail.com",new Address(1,"a","b","c")));
        List<Contact> actual = contactRepository.getAll();
        assertThat(actual,is(equalTo(expected)));
    }
}
