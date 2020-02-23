package dev.arantes.lib.database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    private Connection connection;

    public DatabaseManager() {
    }

    public void createTable(String name, String format) throws SQLException {
    // (uuid TEXT, rank TEXT)
            PreparedStatement stm = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS " + name + " (" + format + ")"
            );
            stm.execute();
            stm.close();
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public void connect(ConnectionData data) throws SQLException, ClassNotFoundException {
            this.connection = data.connect();
    }
}
