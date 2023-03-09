package FontSide;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Methods to create database, save data to database
 * @author Imperfeton
 */
public  class SaveData {
    /** Set up database
     *
     */

    public static void setupDatabase() {
        Properties props = new Properties();
        FileInputStream in;
        in = null;
        try {
            in = new FileInputStream("./db.props");
            props.load(in);
            in.close();

            // specify the data source, username and password
            String url = props.getProperty("jdbc.url");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");
            String schema = props.getProperty("jdbc.schema");

            
            String CREATE_DATABASE = String.format("CREATE DATABASE IF NOT EXISTS %s;",schema);
            String USE_DATABASE = String.format("USE %s;",schema);
            String CREATE_TABLE_USER =String.format(
                    "CREATE TABLE IF NOT EXISTS `%s`.`users`(" +
                            "  `userLastName` varchar(15) NOT NULL," +
                            "  `userFirstName` varchar(15) NOT NULL," +
                            "  `userEmail` varchar(50) ," +
                            "  `phone` varchar(30)," +
                            "  `pass` varchar(30) NOT NULL," +
                            "  `status` varchar(30)," +
                            "  PRIMARY KEY(`userEmail`)" +
                            ")ENGINE=MyISAM DEFAULT CHARSET=latin1;",schema);
            String CREATE_TABLE_ORDER_PIZZA = String.format(
                    "CREATE TABLE IF NOT EXISTS `%s`.`ordered_Pizza`(" +
                            "  `orderID` varchar(10) NOT NULL," +
                            "  `pizzaName` varchar(30) NOT NULL," +
                            "  `size` varchar(3)," +
                            "  `base` varchar(25)," +
                            "  `sauce` varchar(20)," +
                            "  `note` varchar(300));",schema);
            
            String CREATE_TABLE_ORDER_PASTA = String.format(
                    "CREATE TABLE IF NOT EXISTS `%s`.`ordered_Pasta`(" +
                            "  `orderID` varchar(30) NOT NULL," +
                            "  `pastaName` varchar(30) NOT NULL," +
                            "  `quantity` varchar(30) NOT NULL);",schema);

            String CREATE_TABLE_ORDER_DRINK = String.format(
                    "CREATE TABLE IF NOT EXISTS `%s`.`ordered_Drink`(" +
                            "  `orderID` varchar(10) NOT NULL," +
                            "  `drinkName` varchar(30) NOT NULL," +
                            "  `quantityDrink` integer NOT NULL);",schema);
            
             String CREATE_TABLE_ORDER_CUP = String.format(
                    "CREATE TABLE IF NOT EXISTS `%s`.`ordered_Cup`(" +
                            "  `orderID` varchar(10) NOT NULL," +
                            "  `quantityCup` integer);",schema);

            String CREATE_TABLE_PRICE_PIZZA = String.format(
                    "CREATE TABLE IF NOT EXISTS `%s`.`pizza_price`(" +
                            "  `pizzaName` varchar(20) NOT NULL," +
                            "  `size` integer NOT NULL," +
                            "  `price` integer," +
                            "  PRIMARY KEY(`pizzaName`, `size`));",schema);
            
            String CREATE_TABLE_PRICE_PASTA = String.format(
                    "CREATE TABLE IF NOT EXISTS `%s`.`pasta_price`(" +
                            "  `pastaName` varchar(20) NOT NULL," +
                            "  `price` integer NOT NULL, " +
                            "  PRIMARY KEY(`pastaName`)) ;",schema);
            
            String CREATE_TABLE_PRICE_DRINK = String.format(
                    "CREATE TABLE IF NOT EXISTS `%s`.`drink_price`(" +
                            "  `drinkName` varchar(20) NOT NULL," +
                            "  `price` integer NOT NULL, " +
                            "  PRIMARY KEY(`drinkName`));",schema);
            
            String INSERT_PIZZA_SUPREME_8_INCH =  "INSERT IGNORE INTO pizza_price VALUES ('supreme',8,13);";
            String INSERT_PIZZA_SUPREME_11_INCH =  "INSERT IGNORE INTO pizza_price VALUES ('supreme',11,17);";
            String INSERT_PIZZA_SUPREME_14_INCH =  "INSERT IGNORE INTO pizza_price VALUES ('supreme',14,19);";
            String INSERT_PIZZA_CHICKEN_8_INCH =  "INSERT IGNORE INTO pizza_price VALUES ('chicken',8,12);";
            String INSERT_PIZZA_CHICKEN_11_INCH =  "INSERT IGNORE INTO pizza_price VALUES ('chicken',11,16);";
            String INSERT_PIZZA_CHICKEN_14_INCH =  "INSERT IGNORE INTO pizza_price VALUES ('chicken',14,18);";
            String INSERT_PIZZA_SAUSAGE_8_INCH =  "INSERT IGNORE INTO pizza_price VALUES ('sausage',8,14);";
            String INSERT_PIZZA_SAUSAGE_11_INCH =  "INSERT IGNORE INTO pizza_price VALUES ('sausage',11,18);";
            String INSERT_PIZZA_SAUSAGE_14_INCH =  "INSERT IGNORE INTO pizza_price VALUES ('sausage',14,20);";
            String INSERT_PIZZA_HAWAIIAN_8_INCH =  "INSERT IGNORE INTO pizza_price VALUES ('hawaiian',8,10);";
            String INSERT_PIZZA_HAWAIIAN_11_INCH =  "INSERT IGNORE INTO pizza_price VALUES ('hawaiian',11,14);";
            String INSERT_PIZZA_HAWAIIAN_14_INCH =  "INSERT IGNORE INTO pizza_price VALUES ('hawaiian',14,16);";
            String INSERT_PIZZA_VEGGIE_8_INCH =  "INSERT IGNORE INTO pizza_price VALUES ('veggie',8,12);";
            String INSERT_PIZZA_VEGGIE_11_INCH =  "INSERT IGNORE INTO pizza_price VALUES ('veggie',11,16);";
            String INSERT_PIZZA_VEGGIE_14_INCH =  "INSERT IGNORE INTO pizza_price VALUES ('veggie',14,18);";
            String INSERT_PASTA_CREAMY =  "INSERT IGNORE INTO pasta_price VALUES ('creamy',16);";
            String INSERT_PASTA_BOLOGNESE =  "INSERT IGNORE INTO pasta_price VALUES ('bolognese',18);";
            String INSERT_DRINK_COKE =  "INSERT IGNORE INTO drink_price VALUES ('coke',3);";
            String INSERT_DRINK_PEPSI =  "INSERT IGNORE INTO drink_price VALUES ('pepsi',3);";
            String INSERT_DRINK_SOLO =  "INSERT IGNORE INTO drink_price VALUES ('solo',3);";
            String INSERT_DRINK_ORANGEJUICE =  "INSERT IGNORE INTO drink_price VALUES ('orange_juice',9);";
            String INSERT_DRINK_MANGOJUICE =  "INSERT IGNORE INTO drink_price VALUES ('mango_juice',11);";
            
            // get a connection
            Connection connection = DBConnection.getInstance();
            Statement st = connection.createStatement();
            st.execute(CREATE_DATABASE);
            st.execute(USE_DATABASE);
            st.execute(CREATE_TABLE_USER);
            st.execute(CREATE_TABLE_ORDER_PIZZA);
            st.execute(CREATE_TABLE_ORDER_PASTA);
            st.execute(CREATE_TABLE_ORDER_DRINK);
            st.execute(CREATE_TABLE_ORDER_CUP);
            st.execute(CREATE_TABLE_PRICE_PIZZA);
            st.execute(CREATE_TABLE_PRICE_PASTA);
            st.execute(CREATE_TABLE_PRICE_DRINK);
            
            //inti product price
            //add pizza price
            st.execute(INSERT_PIZZA_SUPREME_8_INCH);
            st.execute(INSERT_PIZZA_SUPREME_11_INCH);
            st.execute(INSERT_PIZZA_SUPREME_14_INCH);
            
            st.execute(INSERT_PIZZA_CHICKEN_8_INCH);
            st.execute(INSERT_PIZZA_CHICKEN_11_INCH);
            st.execute(INSERT_PIZZA_CHICKEN_14_INCH);
            
            st.execute(INSERT_PIZZA_HAWAIIAN_8_INCH);
            st.execute(INSERT_PIZZA_HAWAIIAN_11_INCH);
            st.execute(INSERT_PIZZA_HAWAIIAN_14_INCH);
            
            st.execute(INSERT_PIZZA_SAUSAGE_8_INCH);
            st.execute(INSERT_PIZZA_SAUSAGE_11_INCH);
            st.execute(INSERT_PIZZA_SAUSAGE_14_INCH);
            
            st.execute(INSERT_PIZZA_VEGGIE_8_INCH);
            st.execute(INSERT_PIZZA_VEGGIE_11_INCH);
            st.execute(INSERT_PIZZA_VEGGIE_14_INCH);
            
            //add pasta price
            st.execute(INSERT_PASTA_CREAMY);
            
            st.execute(INSERT_PASTA_BOLOGNESE);
            
            //add drink price
            st.execute(INSERT_DRINK_COKE);
            
            st.execute(INSERT_DRINK_PEPSI);
            
            st.execute(INSERT_DRINK_SOLO);
            
            st.execute(INSERT_DRINK_ORANGEJUICE);
            
            st.execute(INSERT_DRINK_MANGOJUICE);
            
            st.close();
        } catch (SQLException | FileNotFoundException sqle) {
            System.err.println(sqle);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    /** Save new user if user doesn't exits in database
     *
     * @param newUser name of user
     * @throws SQLException  error to connect SQL
     */
    public static void saveNewUser(User newUser) throws SQLException  {
        String insertUser = String.format("INSERT INTO users VALUES ('%s','%s','%s', '%s', '%s', '%s');",
                newUser.getUserLastName(), newUser.getUserFirstName(), newUser.getUserEmail(), newUser.getUserPhone(), newUser.getPassword(), newUser.getStatus());
        Connection connection = DBConnection.getInstance();
        Statement st = connection.createStatement();
        st.execute(insertUser);
        st.close();
    }
    
    /** Save new user if user doesn't exits in database
     *
     * @param newPizza new pizza order from user
     * @throws SQLException  error to connect SQL
     */
    public static void saveNewOrderPizza(Pizza newPizza) throws SQLException  {
        String insertPizza = String.format("INSERT INTO ordered_Pizza VALUES (%d,'%s',%d,'%s','%s','%s');",
             newPizza.getOrderID(), newPizza.getPizzaName(), newPizza.getSize(), newPizza.getBase(), newPizza.getSauce(), newPizza.getNote());
        Connection connection = DBConnection.getInstance();
        Statement st = connection.createStatement();
        st.execute(insertPizza);
        st.close();
    }
    
    /** Save new user if user doesn't exits in database
     *
     * @param newPasta new pasta order from user
     * @throws SQLException  error to connect SQL
     */
    public static void saveNewOrderPasta(Pasta newPasta) throws SQLException  {
        String insertPizza = String.format("INSERT INTO ordered_Pasta VALUES (%d,'%s','%d');",
                newPasta.getOrderID(), newPasta.getPastaName(), newPasta.getQuantity());
        Connection connection = DBConnection.getInstance();
        Statement st = connection.createStatement();
        st.execute(insertPizza);
        st.close();
    }
    
    /** Save new user if user doesn't exits in database
     *
     * @param newDrink new drink order from user
     * @throws SQLException  error to connect SQL
     */
    public static void saveNewOrderDrink(Drink newDrink) throws SQLException  {
        String insertDrink = String.format("INSERT INTO ordered_Drink VALUES (%d,'%s',%d);",
                newDrink.getOrderID(), newDrink.getDrinkName(), newDrink.getDrinkQuantity());
        Connection connection = DBConnection.getInstance();
        Statement st = connection.createStatement();
        st.execute(insertDrink);
        st.close();
    }
    
    public static void saveNewOrderCup(Integer orderID, Integer quantity) throws SQLException  {
        String insertCup = String.format("INSERT INTO ordered_Cup VALUES (%d,%d);",
                orderID, quantity);
        Connection connection = DBConnection.getInstance();
        Statement st = connection.createStatement();
        st.execute(insertCup);
        st.close();
    }
    
    public static void removeOrderPizza(Pizza pizzaServed ) throws SQLException{
        String removePizza = String.format("DELETE from ordered_Pizza WHERE orderID = %d and pizzaName = '%s' and size = %d and base = '%s' and sauce = '%s' and note = '%s';", 
                pizzaServed.getOrderID() , pizzaServed.getPizzaName(),pizzaServed.getSize(),pizzaServed.getBase(), pizzaServed.getSauce(),pizzaServed.getNote());
        Connection connection = DBConnection.getInstance();
        Statement st = connection.createStatement();
        st.execute(removePizza);
        st.close();
    }
    
    public static void removeOrderPasta(Pasta pastaServed ) throws SQLException{
        String removePasta = String.format("DELETE from ordered_Pasta WHERE orderID = %d and pastaName = '%s' and quantity = %d;", 
                pastaServed.getOrderID(), pastaServed.getPastaName(), pastaServed.getQuantity());
        Connection connection = DBConnection.getInstance();
        Statement st = connection.createStatement();
        st.execute(removePasta);
        st.close();
    }
    
    public static void removeOrderDrink(Drink drinkServed ) throws SQLException{
        String removePizza = String.format("DELETE from ordered_Drink WHERE orderID = %d and drinkName = '%s' and quantityDrink = %d;", 
                drinkServed.getOrderID(), drinkServed.getDrinkName(), drinkServed.getDrinkQuantity());
        Connection connection = DBConnection.getInstance();
        Statement st = connection.createStatement();
        st.execute(removePizza);
        st.close();
    }
    
    public static void removeOrderCup(Integer orderID, Integer quantity ) throws SQLException{
        String removeCups = String.format("DELETE from ordered_Cup WHERE orderID = %d and quantityCup = %d;", 
                orderID, quantity);
        Connection connection = DBConnection.getInstance();
        Statement st = connection.createStatement();
        st.execute(removeCups);
        st.close();
    }
}