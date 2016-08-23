package vladimirov.alexander.registration_system.persistence_layer;

import vladimirov.alexander.registration_system.domain_layer.User;
import vladimirov.alexander.registration_system.domain_layer.UserRepository;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by zumba on 29.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *             This is conrete implementation of UserRepository interface for SQL database.
 */
public class PersistenceUserRepository implements UserRepository {
    private final DataStore dataStore;

    public PersistenceUserRepository(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    /**
     * Gets all users from database.
     * @return List of users.
     */
    @Override
    public List<User> getAll() {
        return dataStore.fetchRows("SELECT * FROM Users", (ResultSet resultSet) -> new User(resultSet.getInt(1), resultSet.getString(2)));
    }

    /**
     * Gets single user by Id
     * @param id id of the user.
     * @return instance of user class.
     */
    @Override
    public User byId(Integer id) {
        return (User) dataStore.fetchRows("SELECT * FROM Users WHERE Id = ?", resultSet -> new User(resultSet.getInt(1),resultSet.getString(2)),id);
    }

    /**
     * Register new user to database.
     * @param user is new user that is going to be recorded to database.
     */
    @Override
    public void register(User user) {
    dataStore.update("INSERT INTO Users VALUES(?,?)", user.id , user.name);
    }

    /**
     * Updates user from database with new data.
     * @param user with changes that are going to be be applied.
     */
    @Override
    public void update(User user) {
        dataStore.update("UPDATE Users SET Name = ? Where Id = ?", user.name , user.id);
    }

    /**
     * Deletes user from database.g
     * @param user is the target of deletions.
     */
    @Override
    public void delete(User user) {
        dataStore.update("DELETE FROM Users WHERE Id = ?", user.id);
    }
}
