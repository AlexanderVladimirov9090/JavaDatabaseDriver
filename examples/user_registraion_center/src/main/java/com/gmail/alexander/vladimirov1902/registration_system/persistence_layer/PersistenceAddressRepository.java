package com.gmail.alexander.vladimirov1902.registration_system.persistence_layer;

import com.gmail.alexander.vladimirov1902.registration_system.domain_layer.Address;
import com.gmail.alexander.vladimirov1902.registration_system.domain_layer.AddressRepository;

import java.util.List;

/**
 * Created by zumba on 30.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *             This is conrete implementiation of AddressRepository used for SQL database.
 */
public class PersistenceAddressRepository implements AddressRepository {
    private final DataStore dataStore;

    public PersistenceAddressRepository(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    /**
     * Gets all Addresses from database.
     * @return list of addresses from database.
     */
    @Override

    public List<Address> getAll() {
        return dataStore.fetchRows("SELECT * FROM Address", resultSet -> new Address(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
    }

    /**
     * Registers new address to database.
     * @param address new address to be added to database.
     */
    @Override
    public void register(Address address) {
        dataStore.update("INSERT INTO Address VALUES(?,?,?,?)",address.id, address.country, address.city, address.street);
    }

    /**
     * Updates old address with new one.
     * @param address with the new changes that are going to be applied.
     */
    @Override
    public void update(Address address) {
        dataStore.update("UPDATE Address SET Country = ? ,City = ?, Street = ?, WHERE Id = ?",
                address.country, address.city, address.street, address.id);
    }

    /**
     * Deletes address from databse.
     * @param address is the target of deletion.
     */
    @Override
    public void delete(Address address) {
        dataStore.update("DELETE FROM Address WHERE Id = ?", address.id);
    }

    /**
     * Gets address by id.
     * @param id of address that is searched for.
     * @return address from database.
     */
    @Override
    public Address byId(Integer id) {
        return (Address) dataStore.fetchRows("SELECT * FROM Address WHERE Id = ?", resultSet -> new Address(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4)),id);
    }
}