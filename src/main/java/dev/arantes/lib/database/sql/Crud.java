package dev.arantes.lib.database.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
                String.format("CREATE TABLE IF NOT EXISTS %s (%s)", table, format)
        );
        stm.execute();
    }

    public QueryResponse executeQuery(String sql, Object... values) throws SQLException {
        PreparedStatement stm = connection.prepareStatement(sql);

        for (int i = 0; i < values.length; i++) {
            stm.setObject(i +1, values[i]);
        }

        return new QueryResponse(stm, stm.executeQuery());
    }

    public boolean executeUpdate(String sql, Object... values) {
        PreparedStatement stm = null;

        try {
            stm = connection.prepareStatement(sql);

            for (int i = 0; i < values.length; i++) {
                stm.setObject(i +1, values[i]);
            }

            stm.executeUpdate();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (stm != null) {
                    stm.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return false;
    }

    public boolean update(String where, String query, Object... values) {
        return executeUpdate(String.format("UPDATE %s SET %s WHERE %s", table, query, where), values);
    }

    public boolean create(String query, Object... values) {
        return executeUpdate(String.format("INSERT INTO %s VALUES (%s)", table, query), values);
    }

    public QueryResponse get(String field, String where) throws SQLException {
        return executeQuery(String.format("SELECT %s FROM %s WHERE %s", field, table, where));
    }
}
