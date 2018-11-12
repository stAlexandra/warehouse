import com.me.DataSource;
import org.junit.After;
import org.junit.Test;

import java.sql.*;
//import java.sql.Driver;


//import com.mysql.jdbc.Driver;
public class DBConnectionTest {

    private static final String URL = "jdbc:mysql://localhost:3306/warehouse?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String LOGIN = "root";
    private static final String PASS = "root";

    @Test
    public void testGetConnection(){
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //Driver driver = new Driver();
            //DriverManager.registerDriver(driver);
            Connection connection = DriverManager.getConnection(URL, LOGIN, PASS);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM items");
            while(resultSet.next()){
                System.out.println(resultSet.getInt("id"));
            }
            if(!connection.isClosed()){
                System.out.println("not closed");
            }
            resultSet.close();
            statement.close();
            connection.close();
            if(connection.isClosed()){
                System.out.println("closed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
