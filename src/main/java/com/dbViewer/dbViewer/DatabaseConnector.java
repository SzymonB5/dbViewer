package com.dbViewer.dbViewer;

import java.sql.*;

public class DatabaseConnector {
    public static final String jdbcUrl = "jdbc:postgresql://ep-wild-bush-363371.eu-central-1.aws.neon.tech/neondb";
    public static final String username = "SzymonB5";
    public static final String password = "AiSRg15nCLpa";

    private static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            return null;
        }
        try {
            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            return null;
        }
    }

    private static boolean closeConnection(Connection connection) {
        try {
            connection.close();
            return true;
        }
        catch (SQLException exception) {
            return false;
        }
    }

    public static String getDatabaseName() {
        Connection connection = getConnection();
        String ret;
        try {
            assert connection != null;
            ret = connection.getCatalog();
        } catch (SQLException e) {
            return null;
        }
        closeConnection(connection);

        return ret;
    }

    public static void test() {
        try {
            // Register the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish the connection
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Execute a simple query
            String query = "SELECT * FROM users";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Process the result set
            while (resultSet.next()) {
                // Assuming your table has columns named "column1" and "column2"
                String column1Value = resultSet.getString("name");
                int column2Value = resultSet.getInt("counter");

                // Print the retrieved values
                System.out.println("column1: " + column1Value);
                System.out.println("column2: " + column2Value);
                System.out.println("--------------------");
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();

            System.out.println("Connection to the database and query execution were successful!");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database or execute the query.");
            e.printStackTrace();
        }
    }
}
