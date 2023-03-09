package FontSide;
import java.util.*; 

/**
 *
 * @author Imperfeton
 */
public class Cart {
    private String orderID;
    public static ArrayList<String> ListOfProduct;
    
    //contruct for geting order
    public Cart(String orderID, ArrayList<String> ListOfProduct){
        this.orderID = orderID;
        this.ListOfProduct = ListOfProduct;
    }
    
    public static void addProduct(String newProduct){
        ListOfProduct.add(newProduct);
    }
    
    public static void removeProduct (String oldProduct){
        ListOfProduct.remove(oldProduct);
    }

    public String getOrderID() {
        return orderID;
    }

    public ArrayList<String> getListOfProduct() {
        return ListOfProduct;
    }
    
    public static Integer getTotal(){
        Integer total = 0;
        String typeOfProduct;
        for(int i = 0;i < Cart.ListOfProduct.size(); i++){
            typeOfProduct = Cart.ListOfProduct.get(i).split(",")[0];
           
            switch (typeOfProduct) {
                case "Pizza":
                    total += Integer.parseInt(Cart.ListOfProduct.get(i).split(",")[7].split(" ")[0]);
                    break;
                case "Pasta":
                    total += Integer.parseInt(Cart.ListOfProduct.get(i).split(",")[3].split(" ")[0]);
                    break;
                case "Drink" :
                    total += Integer.parseInt(Cart.ListOfProduct.get(i).split(",")[3].split(" ")[0]);
                    break;
            }
        }
        return total ;
    }
}
