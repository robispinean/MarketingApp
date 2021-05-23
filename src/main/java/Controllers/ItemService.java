package Controllers;

import AlertMessages.Message;
import Models.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemService {
    DatabaseController c= new DatabaseController();
    Message msg = new Message();

    public List<Item> getItems(){
        try {
            Connection con = c.connectDatabase();
            List<Item> items = new ArrayList<>();
            final String str= "SELECT * FROM ITEM_INFO";
            java.sql.PreparedStatement stmt=con.prepareStatement(str);
            ResultSet results=stmt.executeQuery();
            while(results.next()){
                Item s = new Item(results.getString("ITEM_ID"), results.getString("ITEM_NAME"), results.getString("ITEM_DESCRIPTION"), results.getDouble("ITEM_PRICE"), results.getString("ITEM_IMAGE"));
                items.add(s);
            }
            c.closeConnection(con);
            return items;
        }catch(Exception e){
            System.out.println(e);
            return new ArrayList<>();
        }
    }
}
