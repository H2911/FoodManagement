package FontSide;

/**
 *
 * @author Imperfecton
 */
public class Drink {
    private Integer orderID;
    private String drinkName;
    private Integer drinkQuantity;
    
    public Drink(Integer orderID, String drinkName, Integer drinkQuantity){
        this.orderID = orderID;
        this.drinkName = drinkName;
        this.drinkQuantity = drinkQuantity;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public Integer getDrinkQuantity() {
        return drinkQuantity;
    }

    public Integer getOrderID() {
        return orderID;
    }
}
