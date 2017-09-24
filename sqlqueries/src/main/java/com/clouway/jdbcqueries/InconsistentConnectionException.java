package com.clouway.jdbcqueries;

/**
 * Created by clouway on 26.10.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 */
public class InconsistentConnectionException extends RuntimeException {
    public InconsistentConnectionException(String message) {
        super(message);
    }
}
