package dev.arantes.lib.database;

import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    private final JavaPlugin plugin;
    private Connection connection;

    public DatabaseManager(final JavaPlugin plugin) {
        this.plugin = plugin;
    }

    // (uuid TEXT, rank TEXT)
    public void createTable(String name, String format) throws SQLException {
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

    private void connect(ConnectionData data) throws SQLException, ClassNotFoundException {
            this.connection = data.connect();
    }
}
