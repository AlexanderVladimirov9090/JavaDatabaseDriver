package com.gmail.alexander.vladimirov1902;

import com.google.common.collect.Lists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by clouway on 04.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *
 *        This is driver write for databases.
 *        The class uses two public method for updating to database and fetching from database.
 *        One priveta method for filling up statement.
 */
public class DataStore<T> {
    private final Connection dbConnection;

    public DataStore(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    /**
     * Executes change to the table in database.
     *
     * @param query   executed query to the database.
     * @param objects used when update, write or remove record from database.
     */
    public void update(String query, Object... objects) {

        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            fillStatement(statement, objects);
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException("Connection to the database wasn't established");
        }
    }

    /**
     * Fetches rows(records) from database.
     *
     * @param query      that is pushed for execution.
     * @param rowEntry is used to provide places to store data from database to object.
     * @return list filled with objects.
     */
    public List<T> fetchRows(String query, RowEntry<T> rowEntry) {
        List list = Lists.newArrayList();
        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Optional possibleRow = Optional.of(rowEntry.fetchRow(resultSet));
                list.add(possibleRow.get());
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Connection to the database wasn't established");
        }
        return list;
    }

    /**
     * Fills statement for query
     *
     * @param statement statement that is going to be filled.
     * @param objects given object to fill statement.
     * @throws SQLException is thrown by statement.
     */
    private void fillStatement(PreparedStatement statement, Object... objects) throws SQLException {
        for (int i = 0; i < objects.length; i++) {
            statement.setObject(i + 1, objects[i]);
        }
    }
}