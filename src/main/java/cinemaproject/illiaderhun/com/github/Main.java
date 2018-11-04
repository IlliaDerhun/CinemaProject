package cinemaproject.illiaderhun.com.github;

import cinemaproject.illiaderhun.com.github.util.ConnectionManager;
import cinemaproject.illiaderhun.com.github.util.QueriesManager;
import org.apache.log4j.Logger;
import org.osjava.sj.loader.SJDataSource;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getSimpleName());

    public static void main(String[] args) {
        try {
            /*DataSource dataSource = (SJDataSource) (new InitialContext().lookup("db"));
            Connection connection = dataSource.getConnection();*/

            Connection connection = ConnectionManager.getConnection();

            String query = "SELECT * FROM cinema.user where user_id = 2;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            System.out.println(resultSet.getString(2));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*Properties properties = QueriesManager.getProperties("user");
        System.out.println(properties.getProperty("insert"));*/

    }
}
