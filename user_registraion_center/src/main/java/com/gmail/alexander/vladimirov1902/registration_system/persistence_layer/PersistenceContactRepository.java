package com.gmail.alexander.vladimirov1902.registration_system.persistence_layer;

import com.gmail.alexander.vladimirov1902.registration_system.domain_layer.*;
import vladimirov.alexander.registration_system.domain_layer.*;

import java.util.List;

/**
 * Created by zumba on 30.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *             This is conrete implementation of interface ConcartRepository used for SQL database.
 */
public class PersistenceContactRepository implements ContactRepository {
    private final DataStore dataStore;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public PersistenceContactRepository(DataStore dataStore, UserRepository userRepository, AddressRepository addressRepository) {
        this.dataStore = dataStore;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    /**
     * Gets all Contacts from database.
     * @return List of contacts from database.
     */
    @Override
    public List<Contact> getAll() {
        return dataStore.fetchRows("SELECT * FROM Contact", resultSet -> {
            User user = userRepository.byId(resultSet.getInt(2));
            Address address = addressRepository.byId(resultSet.getInt(5));
            return new Contact(resultSet.getInt(1), user, resultSet.getString(3), resultSet.getString(4), address);
        });
    }

    /**
     * Register new Contact to database.
     * @param contact that is going to be recorded to database.
     */
    @Override
    public void register(Contact contact) {
        User user = contact.user;
        Address address = contact.address;
        dataStore.update("INSERT INTO Contact VALUES (?,?,?,?,?)",contact.id,user.id,contact.cellPhone,contact.email,address.id);
    }

    /**
     * Updates old Contact with new data.
     * @param contact with new changes that are going to be applied.
     */
    @Override
    public void update(Contact contact) {
        dataStore.update("UPDATE Contact SET CellPhone = ?, Email = ? WHERE Id = ?",contact.cellPhone,contact.email,contact.id);
    }

    /**
     * Deletes Contact from database.
     * @param contact is the target of deletions.
     */
    @Override
    public void delete(Contact contact) {
        dataStore.update("DELETE Contact WHERE Id = ?", contact.id);
    }
}