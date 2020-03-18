package dev.arantes.lib.database.sql;

import java.sql.*;

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

    public ResultSet executeQuery(String sql, Object... values) throws SQLException {
        PreparedStatement stm = this.connection.prepareStatement(sql);

        for (int i = 0; i < values.length; i++) {
            stm.setObject(i, values[i]);
        }

        return stm.executeQuery();
    }

    public void executeUpdate(String sql, Object... values) throws SQLException {
        PreparedStatement stm = this.connection.prepareStatement(sql);

        for (int i = 0; i < values.length; i++) {
            stm.setObject(i, values[i]);
        }

        stm.executeUpdate();
    }

    public boolean update(String where, String query, Object... values) {
        try {
            PreparedStatement stm = connection.prepareStatement(
                    String.format("UPDATE %s SET %s WHERE %s", table, query, where)
            );

            for (int i = 0; i < values.length; i++) {
                stm.setObject(i, values[i]);
            }

            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean create(String query, Object... values) {
        try {
            PreparedStatement stm = connection.prepareStatement(
                    String.format("INSERT INTO %s VALUES (%s)", table, query)
            );

            for (int i = 0; i < values.length; i++) {
                stm.setObject(i, values[i]);
            }

            stm.executeUpdate();
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
