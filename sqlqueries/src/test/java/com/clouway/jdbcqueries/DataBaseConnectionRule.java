package com.clouway.jdbcqueries;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by zumba on 10.11.16.
 *
 * @author Alexander Vladimirov
 *         <alexandervladimirov1902@gmail.com>
 *             This class is writen for automating and reusing connection to the database when writing tests.
 */
public class DataBaseConnectionRule implements TestRule {

    public   Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/jdbc_database", "root", "123123");

    public DataBaseConnectionRule() throws SQLException {
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Class.forName("com.mysql.jdbc.Driver");
                base.evaluate();
                closeConnection();
            }
        };
    }

    /**
     * This method is used to close connection to the database.
     */
    private void closeConnection(){
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}