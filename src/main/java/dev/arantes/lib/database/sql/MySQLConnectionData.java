package dev.arantes.lib.database.sql;

import org.bukkit.configuration.ConfigurationSection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionData implements ConnectionData {
    private boolean isEnabled;
    private final String host;
    private final String user;
    private final String pass;
    private final String db;

    public MySQLConnectionData(final ConfigurationSection config) {
        this.isEnabled = config.getBoolean("enable");
        this.host = config.getString("host");
        this.user = config.getString("user");
        this.pass = config.getString("pass");
        this.db = config.getString("database");
    }

    boolean isEnabled() {
        return isEnabled;
    }

    private String getURL() {
        return String.format("jdbc:mysql://%s/%s", host, db);
    }

    public Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(getURL(), user, pass);
    }
}