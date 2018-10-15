package cinemaproject.illiaderhun.com.github;

import org.apache.log4j.Logger;
import org.osjava.sj.loader.SJDataSource;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getSimpleName());

    public static void main(String[] args) {
        try {
            DataSource dataSource = (SJDataSource) (new InitialContext().lookup("db"));
            Connection connection = dataSource.getConnection();

            String query = "SELECT * FROM cinema.movie;";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            System.out.println(resultSet.getString(2));

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
