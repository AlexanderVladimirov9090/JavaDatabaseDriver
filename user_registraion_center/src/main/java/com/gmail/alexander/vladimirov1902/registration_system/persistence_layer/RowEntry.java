package com.gmail.alexander.vladimirov1902.registration_system.persistence_layer;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *             This interface is use for fetching the result from database.
 */
public interface RowEntry<T> {
    /**
     * Fetches result of database.
     * @param resultSet is used for getting the data from database.
     * @return
     */
    T fetchRow(ResultSet resultSet) throws SQLException;
}
