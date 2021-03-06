package com.gmail.alexander.vladimirov1902.magazine.persistence_layer;

import com.google.common.collect.Lists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *         This class provides methods for executing queries for sql databases.
 */
public class DataStore<T> {
    private final Connection dbConnection;

    public DataStore(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    /**
     * Executes change queries.
     *
     * @param query   that is pushed for execution.
     * @param objects if needed to push record to database or make table.
     */
    public void update(String query, Object... objects) {
        try (PreparedStatement statement = dbConnection.prepareStatement(query)) {
            fillStatement(statement, objects);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
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
    public List<T> fetchRows(String query, RowEntry<T> rowEntry, Object... objects) {
        List list = Lists.newArrayList();
        try {
            PreparedStatement statement = dbConnection.prepareStatement(query);
            fillStatement(statement, objects);
            ResultSet resultSet = statement.executeQuery();
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
     * @param statement
     * @param objects
     * @throws SQLException
     */
    private void fillStatement(PreparedStatement statement, Object... objects) throws SQLException {
        for (int i = 0; i < objects.length; i++) {
            statement.setObject(i + 1, objects[i]);
        }
    }
}