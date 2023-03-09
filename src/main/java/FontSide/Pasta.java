package FontSide;

/**
 *
 * @author Imperfecton
 */
public class Pasta{
    private Integer orderID;
    private String pastaName;
    private Integer quantity;
    
    public Pasta(Integer orderID, String pastaName, Integer quantity){
        this.orderID = orderID;
        this.pastaName = pastaName;
        this.quantity = quantity;
    }

    public String getPastaName() {
        return pastaName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getOrderID() {
        return orderID;
    }
}
