package dev.arantes.lib.database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Crud {
    private Connection connection;
    private String table;

    public Crud(Connection connection, String table) {
        this.connection = connection;
        this.table = table;
    }
    public void createTable(String format) throws SQLException {
        PreparedStatement stm = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS " + table + " (" + format + ")"
        );
        stm.execute();
        stm.close();
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        return this.connection.prepareStatement(sql).executeQuery();
    }

    public void executeUpdate(String sql) throws SQLException {
        this.connection.prepareStatement(sql).executeUpdate();
    }

    public boolean update(String field, String value, String where) {
        try {
            connection.prepareStatement(
                    String.format("UPDATE %s SET %s=%s WHERE %s", table, field, value, where)
            ).executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean create(String values) {
        try {
            connection.prepareStatement(
                    String.format("INSERT INTO %s VALUES (%s)", table, values)
            ).executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public ResultSet get(String field, String where) {
        try {
            return executeQuery(String.format("SELECT %s FROM %s WHERE %s", field, table, where));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
