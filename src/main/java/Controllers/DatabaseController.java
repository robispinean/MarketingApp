package Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseController {
    //URL of Oracle database server
    private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static String USER = "SEF_PROJECT";
    private static String PASS = "123";

    public boolean initialize(){

        try(Connection con = DriverManager.getConnection(url, USER, PASS);
        ) {
            try{
                Statement stmt = con.createStatement();
                String sql = "CREATE TABLE APP_USERS \n" +
                        "( ID VARCHAR2(150) NOT NULL ENABLE,\n" +
                        "PASSWORD VARCHAR2(64) NOT NULL ENABLE,\n" +
                        "ROLE CHAR(8) NOT NULL ENABLE,\n" +
                        "EMAIL VARCHAR2(30) NOT NULL ENABLE,\n" +
                        "CONSTRAINT " + '"' + "APP_USERS_PK" + '"' + " PRIMARY KEY (ID) ENABLE\n" +
                        ") ";

                stmt.executeUpdate(sql);
                System.out.println("Created APP_USERS table in database...");

            } catch(SQLException e){
                System.out.println("APP_USERS table already exits.");
            }

            try{
                Statement stmt = con.createStatement();
                String sql = "CREATE TABLE ITEM_INFO \n" +
                    "( ITEM_ID NUMBER NOT NULL ENABLE,\n" +
                    "ITEM_NAME VARCHAR2(50),\n" +
                    "ITEM_DESCRIPTION VARCHAR2(2000),\n" +
                    "ITEM_PRICE NUMBER(8,2),\n" +
                    "ITEM_IMAGE VARCHAR2(100),\n" +
                    "CONSTRAINT " + '"' + "ITEM_INFO_PK" + '"' + " PRIMARY KEY (ITEM_ID) ENABLE\n" +
                    ") ";

                stmt.executeUpdate(sql);
                System.out.println("Created ITEM_INFO table in database...");

            } catch(SQLException e){
                System.out.println("ITEM_INFO table already exits.");
            }

            try{
                Statement stmt = con.createStatement();
                String item = "INSERT INTO ITEM_INFO\n" +
                        "VALUES(1,'ITEM1','DESCRIPTION1',1,'img/item1.png') ";
                stmt.executeUpdate(item);

                stmt = con.createStatement();
                item = "INSERT INTO ITEM_INFO\n" +
                        "VALUES(2,'ITEM2','DESCRIPTION2',2,'img/item2.png') ";
                stmt.executeUpdate(item);

                stmt = con.createStatement();
                item = "INSERT INTO ITEM_INFO\n" +
                        "VALUES(3,'ITEM3','DESCRIPTION3',23.2,'img/item3.png') ";
                stmt.executeUpdate(item);

                stmt = con.createStatement();
                item = "INSERT INTO ITEM_INFO\n" +
                        "VALUES(4,'ITEM4','DESCRIPTION4',4.42,'img/item4.png') ";
                stmt.executeUpdate(item);

                stmt = con.createStatement();
                item = "INSERT INTO ITEM_INFO\n" +
                        "VALUES(5,'ITEM5','DESCRIPTION5',5.5,'img/item5.png') ";
                stmt.executeUpdate(item);

                stmt = con.createStatement();
                item = "INSERT INTO ITEM_INFO\n" +
                        "VALUES(6,'ITEM6','DESCRIPTION6',66,'img/item6.png') ";
                stmt.executeUpdate(item);

                stmt = con.createStatement();
                item = "INSERT INTO ITEM_INFO\n" +
                        "VALUES(7,'ITEM7','DESCRIPTION7',12,'img/item7.png') ";
                stmt.executeUpdate(item);

                stmt = con.createStatement();
                item = "INSERT INTO ITEM_INFO\n" +
                        "VALUES(8,'ITEM8','DESCRIPTION8',11,'img/item8.png') ";
                stmt.executeUpdate(item);

                stmt = con.createStatement();
                item = "INSERT INTO ITEM_INFO\n" +
                        "VALUES(9,'ITEM9','DESCRIPTION9',9/2,'img/item9.png') ";
                stmt.executeUpdate(item);

                stmt = con.createStatement();
                item = "INSERT INTO ITEM_INFO\n" +
                        "VALUES(10,'ITEM10','DESCRIPTION10',10.5,'img/item10.png') ";
                stmt.executeUpdate(item);

                System.out.println("Inserted items in database...");

            }catch (SQLException e){
                System.out.println("Item already in table...");
            }

            return true;
        }catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public Connection connectDatabase() throws SQLException {
        try {
            //properties for creating connection to Oracle database
            Properties props = new Properties();
            props.setProperty("user", USER);
            props.setProperty("password", PASS);

            //creating connection to Oracle database
            Connection con = DriverManager.getConnection(url, props);
            return con;
        }catch(Exception e)
        {
            System.out.println(e);
            return null;
        }
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

