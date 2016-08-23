package vladimirov.alexander.registration_system.domain_layer;

import java.util.List;

/**
 * Created by zumba on 29.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 * This interface is used for executing queries for Contact to the database.
 */
public interface ContactRepository {
    /**
     * Gets all contacts from database.
     * @return list of all contacts from database.
     */
    List<Contact> getAll();

    /**
     * Register new contact to database.
     * @param contact that is going to be recorded to database.
     */
    void register(Contact contact);

    /**
     * Updated contact from database.
     * @param contact with new changes that are going to be applied.
     */
    void update(Contact contact);

    /**
     * Deletes contact from database.
     * @param contact is the target of deletions.
     */
    void delete(Contact contact);
}
