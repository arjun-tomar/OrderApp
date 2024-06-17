/**
 * Represents a donut hole menu item and extends donut
 * @author Aaron Ordonez, Arjun Tomar
 */
package familyguy.dunkru;

import java.text.DecimalFormat;

public class DonutHole extends Donut {
    private static final double DONUT_HOLE_PRICE = 0.39;
    public static final String[] HOLE_FLAVORS = {"Glazed Holes", "Jelly Holes", "Cinnamon Sugar Holes"};

    /**
     * Constructs a DonutHole object with the specified quantity.
     *
     * @param quantity The quantity of donut holes.
     */
    public DonutHole(int quantity) {
        super(DONUT_HOLE_PRICE, quantity, "Hole");
    }

    /**
     * Calculates the price of the donut hole item.
     *
     * @return The total price of the donut hole item.
     */
    @Override
    public double price() {
        return DONUT_HOLE_PRICE*super.getQuantity();
    }

    /**
     * Returns a string representation of the donut hole item.
     *
     * @return A string containing the quantity, price, type, and flavors of the donut hole item.
     */
    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("0.00");
        String curPriceStr = ("$" + df.format(price()));
        return  (int)super.getQuantity() + "x "+ super.toString();
    }
}
