package FontSide;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Methods to get data from database
 * @author Imperfecton
 */
public class GetData {
    
    public static ArrayList<String> getUserEmails() throws SQLException {
        ArrayList<String> userEmail = new ArrayList<>();
        Connection connection = DBConnection.getInstance();
        String getAllUsers = "SELECT * FROM users;";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(getAllUsers);
        while (rs.next())
        {
            userEmail.add(rs.getString(3));
        }
        st.close();
        rs.close();
        return userEmail;
    }
    
    //Get User in database by userEmail
    //return User
    
    public static User getUserByUserEmail (String userEmail) throws SQLException{
        
        Connection connection = DBConnection.getInstance();
        String getUser = String.format("SELECT * FROM users where userEmail = '%s';", userEmail);
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(getUser);
        if (rs.next())
        {
            String lastName = rs.getString(1);
            String firstName = rs.getString(2);
            String email = rs.getString(3);
            String phone = rs.getString(4);
            String pass = rs.getString(5);
            String status = rs.getString(6);
            
            st.close();
            rs.close();
            return new User(lastName, firstName, email, phone, pass, status);
        }
        st.close();
        rs.close();
        return null;
    }
    
    //get list of items in selected orderID
    public static ArrayList<String> getProductbyOrderID(Integer orderID) throws SQLException {
        ArrayList<String> ListOfProduct = new ArrayList<>();
        Connection connection = DBConnection.getInstance();
        String getPizzaOrdered = String.format("SELECT * FROM ordered_Pizza where  orderID = %d;",orderID);
        String getPastaOrdered = String.format("SELECT * FROM ordered_Pasta where orderID = %d;",orderID);
        String getDrinkOrdered = String.format("SELECT * FROM ordered_Drink where orderID = %d;",orderID);
        String getCupOrdered = String.format("SELECT * FROM ordered_Cup where orderID = %d ;",orderID);
        Statement st = connection.createStatement();
        ResultSet pizzasInOrder = st.executeQuery(getPizzaOrdered);
        ResultSet pastasInOrder = st.executeQuery(getPastaOrdered);
        ResultSet drinksInOrder = st.executeQuery(getDrinkOrdered);
        ResultSet cupsInOrder = st.executeQuery(getCupOrdered);
        
        //Add pizza to product list
        while (pizzasInOrder.next())
        {
            String orderLine = pizzasInOrder.getString(2) +"," + pizzasInOrder.getString(3) +","+ pizzasInOrder.getString(4) + "," + pizzasInOrder.getString(5) + "," + pizzasInOrder.getString(6)+" ";
            ListOfProduct.add(orderLine);
        }
        
        while (pastasInOrder.next())
        {
            String orderLine = pastasInOrder.getString(2) + "," + pastasInOrder.getString(3);
            ListOfProduct.add(orderLine);
        }
        
        while (drinksInOrder.next())
        {
            String orderLine = drinksInOrder.getString(2) + "," + drinksInOrder.getString(3);
            ListOfProduct.add(orderLine);
        }
        while (cupsInOrder.next())
        {
            String orderLine = "Cups," + cupsInOrder.getString(2);
            ListOfProduct.add(orderLine);
        }
        st.close();
        pizzasInOrder.close();
        pastasInOrder.close();
        drinksInOrder.close();
        cupsInOrder.close();
        return ListOfProduct;
    }
    
    public static HashMap<Integer, ArrayList<String>> getAllOrders() throws SQLException{
        HashMap<Integer, ArrayList<String>> orders = new HashMap<Integer, ArrayList<String>>();
        ArrayList<String> listOfProducts  = new  ArrayList<String>();
        Integer maxID  = getNewOrderID();
        for (int i = 1; i< maxID + 1 ; i++){
            listOfProducts = getProductbyOrderID(i);
            orders.put(i, listOfProducts);
        }
        return orders;
    }
    
    public static Integer getNewOrderID() throws SQLException {
        Connection connection = DBConnection.getInstance();
        String getPizzalastID = "SELECT max(orderID) FROM ordered_Pizza;";
        String getPastalastID = "SELECT max(orderID) FROM ordered_Pasta;";
        String getDrinklastID = "SELECT max(orderID) FROM ordered_Drink;";
        Statement st = connection.createStatement();
        ResultSet pizzalastID = st.executeQuery(getPizzalastID);
        ResultSet pastalastID = st.executeQuery(getPastalastID);
        ResultSet drinklastID = st.executeQuery(getDrinklastID);
        Integer pizzaID = 0;
        Integer pastaID = 0;
        Integer drinkID = 0;
        if(pizzalastID.next()) {
            pizzaID = pizzalastID.getInt(1);
        }
        if(pastalastID.next()) {
            pastaID = pastalastID.getInt(1);
        }
        if(pizzalastID.next()) {
            drinkID = drinklastID.getInt(1);
        }
        st.close();
        pizzalastID.close();
        pastalastID.close();
        drinklastID.close();
        return Math.max(pizzaID, Math.max(pastaID, drinkID)) + 1;
    }
    
    public static Integer getPizzaPrice (String pizzaName, Integer size) throws SQLException{
        Connection connection = DBConnection.getInstance();
        Statement st = connection.createStatement();
        String getPizzaPrice = String.format("SELECT price FROM pizza_price WHERE pizzaName = '%s' and size = %d;",pizzaName, size);
        ResultSet rs = st.executeQuery(getPizzaPrice);
        Integer result = 0;
        if(rs.next()) {
            result = rs.getInt(1);
        }
        st.close();
        rs.close();
        return result;
    }
    
    public static Integer getPastaPrice (String pastaName) throws SQLException{
        Connection connection = DBConnection.getInstance();
        Statement st = connection.createStatement();
        String getPastaPrice = String.format("SELECT price FROM pasta_price WHERE pastaName = '%s';",pastaName);
        ResultSet rs = st.executeQuery(getPastaPrice);
        Integer result = 0;
        if(rs.next()) {
            result = rs.getInt(1);
        }
        st.close();
        rs.close();
        return result;
    }
    
    public static Integer getDrinkPrice (String drinkName) throws SQLException{
        Connection connection = DBConnection.getInstance();
        Statement st = connection.createStatement();
        String getDrinkPrice = String.format("SELECT price FROM drink_price WHERE drinkName = '%s';",drinkName);
        ResultSet rs = st.executeQuery(getDrinkPrice);
        Integer result = 0;
        if(rs.next()) {
            result = rs.getInt(1);
        }
        st.close();
        rs.close();
        return result;
    }
}