package org.toby.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) {
        String connectionUrl =
                "jdbc:sqlserver://localhost:1434;"
                        + "databaseName=loldb;"
                        + "integratedSecurity=true;";

        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
            System.out.println("success");
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            System.out.println("fail");
            e.printStackTrace();
        }
    }
}

