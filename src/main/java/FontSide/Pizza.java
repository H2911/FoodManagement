package FontSide;

/**
 *
 * @author Imperfecton
 */
public class Pizza {
    private Integer orderID;
    private String pizzaName;
    private Integer size;
    private String base;
    private String sauce;
    private String note;
    
    public Pizza(Integer orderID, String pizzaName,Integer size, String base, String sauce, String note) {
        this.orderID = orderID;
        this.pizzaName = pizzaName;
        this.size = size;
        this.base = base;
        this.sauce = sauce;
        this.note = note;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public Integer getSize() {
        return size;
    }

    public String getBase() {
        return base;
    }

    public String getSauce() {
        return sauce;
    }

    public String getNote() {
        return note;
    }

    public Integer getOrderID() {
        return orderID;
    }
}
