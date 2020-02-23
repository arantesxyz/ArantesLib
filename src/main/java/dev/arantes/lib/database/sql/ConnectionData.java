package dev.arantes.lib.database.sql;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionData {
    Connection connect() throws ClassNotFoundException, SQLException;
}
