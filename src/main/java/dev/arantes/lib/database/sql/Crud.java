package dev.arantes.lib.database.sql;

import java.sql.*;

public class Crud {
    private Connection connection;
    private String table;

    public Crud(Connection connection, String table) {
        this.connection = connection;
        this.table = table;
    }

    public void createTable(String format) {
        PreparedStatement stm = null;

        try {
            stm = connection.prepareStatement(
                    String.format("CREATE TABLE IF NOT EXISTS %s (%s)", table, format)
            );

            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finalizeStatement(stm);
        }
    }

    public ResultSet executeQuery(String sql, Object... values) throws SQLException {
        PreparedStatement stm = null;
        ResultSet querySet = null;

        try{
            stm = connection.prepareStatement(sql);
            for (int i = 0; i < values.length; i++) {
                stm.setObject(i, values[i]);
            }

            querySet = stm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finalizeStatement(stm);

            if (querySet != null) {
                querySet.close();
            }
        }

        return querySet;
    }

    public void executeUpdate(String sql, Object... values) {
        PreparedStatement stm = null;

        try {
            stm = connection.prepareStatement(sql);
            for (int i = 0; i < values.length; i++) {
                stm.setObject(i, values[i]);
            }

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finalizeStatement(stm);
        }
    }

    public boolean update(String where, String query, Object... values) {
        PreparedStatement stm = null;

        try {
            stm = connection.prepareStatement(
                    String.format("UPDATE %s SET %s WHERE %s", table, query, where)
            );

            for (int i = 0; i < values.length; i++) {
                stm.setObject(i, values[i]);
            }

            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finalizeStatement(stm);
        }

        return false;
    }

    public boolean create(String query, Object... values) {
        PreparedStatement stm = null;

        try {
            stm = connection.prepareStatement(
                    String.format("INSERT INTO %s VALUES (%s)", table, query)
            );

            for (int i = 0; i < values.length; i++) {
                stm.setObject(i, values[i]);
            }

            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            finalizeStatement(stm);
        }

        return false;
    }

    public ResultSet get(String field, String where) {
        ResultSet resultSet = null;

        try {
            resultSet = executeQuery(String.format("SELECT %s FROM %s WHERE %s", field, table, where));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return resultSet;
    }

    private void finalizeStatement(PreparedStatement stm) {
        if (stm != null) {
            try {
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
