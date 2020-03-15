package dev.arantes.lib.database.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {
    private Connection connection;

    public DatabaseManager() {
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
