package com.gmail.alexander.vladimirov1902.magazine.persistence_layer;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public interface RowEntry<T> {
    T fetchRow(ResultSet resultSet) throws SQLException;
}
