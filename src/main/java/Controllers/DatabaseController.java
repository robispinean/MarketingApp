package Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseController {
    //URL of Oracle database server
    private static String url = "jdbc:oracle:thin:@localhost:1521:xe";;

    public Connection connectDatabase() throws SQLException {
        try {
            //properties for creating connection to Oracle database
            Properties props = new Properties();
            props.setProperty("user", "SEF");
            props.setProperty("password", "123");

            //creating connection to Oracle database
            Connection con = DriverManager.getConnection(url, props);
            return con;
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return null;
    }

    public boolean closeConnection(Connection con) throws SQLException {
        boolean result;

        try {
            // Close your connection
            con.close();
            result = true;
        } catch (Exception e) {
            System.out.println(e);
            result = false;
        }
        return result;
    }
}

