/**
 * Represents a cake donut and extends the donut class
 * @author Aaron Ordonez, Arjun Tomar
 */
package familyguy.dunkru;

import java.text.DecimalFormat;

public class CakeDonut extends Donut {
    private static final double CAKE_DONUT_PRICE = 1.89;
    public static final String[] CAKE_FLAVORS = {"Cinnamon Sugar", "Old Fashion", "Blueberry"};

    /**
     * Constructs a CakeDonut object with the specified quantity.
     *
     * @param quantity The quantity of cake donuts.
     */
    public CakeDonut(int quantity) {
        super(CAKE_DONUT_PRICE, quantity, "Cake");
    }

    /**
     * Calculates the price of the cake donuts.
     *
     * @return The total price of the cake donuts.
     */
    @Override
    public double price() {
        return CAKE_DONUT_PRICE*super.getQuantity();
    }

    /**
     * Returns a string representation of the cake donut.
     *
     * @return A string containing the quantity, type, flavor, and price of the cake donut.
     */
    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("0.00");
        String curPriceStr = ("$" + df.format(price()));
        return  (int)super.getQuantity() + "x " + super.toString();
    }
}
