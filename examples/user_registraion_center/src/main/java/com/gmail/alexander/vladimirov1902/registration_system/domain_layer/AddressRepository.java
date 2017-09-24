package com.gmail.alexander.vladimirov1902.registration_system.domain_layer;

import java.util.List;

/**
 * Created by zumba on 29.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *             This interface provides methods for executing queries to database.
 */
public interface AddressRepository {
    /**
     * Gets all contacts from database.
     * @return list of all contacts.
     */
    List<Address> getAll();

    /**
     * Registers Adress to database.
     * @param address new address to be added to database.
     */
    void register(Address address);

    /**
     * Updates new changes to existing address in database.
     * @param address with the new changes that are going to be applied.
     */
    void update(Address address);
    /**
     * Deletes existing address from database.
     * @param address is the target of deletion.
     */
    void delete(Address address);


    Address byId(Integer Id);
}
