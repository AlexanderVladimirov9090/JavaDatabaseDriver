package com.gmail.alexander.vladimirov1902.registration_system.domain_layer;

import java.util.List;

/**
 * Created by zumba on 29.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public interface UserRepository {
    /**
     * Gets all users from database.
     * @return List of all users.
     */
    List<User> getAll();

    /**
     * Gets user by id
     * @param id id of the user.
     * @return  User instance from database.
     */
    User byId(Integer id);

    /**
     * Registers new user to database.
     * @param user is new user that is going to be recorded to database.
     */
    void register(User user);

    /**
     * Apply updates to exiting user in of database.
     * @param user with changes that are going to be be applied.
     */
    void update(User user);

    /**
     * Deletes user from database.
     * @param user is the target of deletions.
     */
    void delete(User user);
}
