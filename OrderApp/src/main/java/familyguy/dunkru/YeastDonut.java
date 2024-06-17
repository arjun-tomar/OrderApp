/**
 * represents a yeast donut that the user can pick in the donut fx
 *
 * @author Aaron Ordonez, Arjun Tomar
 */
package familyguy.dunkru;

import java.text.DecimalFormat;

public class YeastDonut extends Donut {
    private static final double YEAST_DONUT_PRICE = 1.79;
    public static final String[] YEAST_FLAVORS = {"Jelly","Chocolate Frosted","Strawberry Frosted", "Glazed", "Lemon Filled", "Sugar"};

    /**
     * Constructs a YeastDonut object with the specified quantity.
     *
     * @param quantity The quantity of yeast donuts.
     */
    public YeastDonut(int quantity) {
        super(YEAST_DONUT_PRICE, quantity, "Yeast");
    }

    /**
     * Calculates the price of the yeast donut.
     *
     * @return The price of the yeast donut.
     */
    @Override
    public double price() {
        return YEAST_DONUT_PRICE*super.getQuantity();
    }

    /**
     * Returns a string representation of the yeast donut.
     *
     * @return A string representation of the yeast donut.
     */
    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("0.00");
        String curPriceStr = ("$" + df.format(price()));
        return  (int)super.getQuantity() + "x "+ super.toString();
    }
}
