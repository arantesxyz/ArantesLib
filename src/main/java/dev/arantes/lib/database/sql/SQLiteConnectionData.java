package dev.arantes.lib.database.sql;

import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class SQLiteConnectionData implements ConnectionData {
    private final JavaPlugin plugin;
    private final String filename;

    SQLiteConnectionData(final JavaPlugin plugin, final String filename) {
        this.plugin = plugin;
        this.filename = filename;
    }

    public Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection("jdbc:sqlite:" + plugin.getDataFolder() + "/" + filename);
    }
}