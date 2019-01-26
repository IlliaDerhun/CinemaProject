package cinemaproject.illiaderhun.com.github.util;

import org.apache.log4j.Logger;
import org.osjava.sj.loader.SJDataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {

    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class.getSimpleName());

    /*public static Connection getConnection() {
        LOGGER.info("method getConnection started with no parameters");

        Connection connection = null;

        try {
            DataSource dataSource = (SJDataSource) (new InitialContext().lookup("db"));
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }

        return connection;
    }*/

    static Connection connection = null;

    public static Connection getConnection() {
        LOGGER.info("method getConnection started with no parameters");

        if (connection == null) {
            try {
                Context context = (Context) (new InitialContext().lookup("java:comp/env"));
                DataSource dataSource = (DataSource) context.lookup("jdbc/dbpool");
                connection = dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

}
