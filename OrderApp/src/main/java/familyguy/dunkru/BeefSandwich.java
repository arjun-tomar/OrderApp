/**
 * Represents a beef sandwich and extends the Sandwich class.
 * @author Aaron Ordonez, Arjun Tomar
 */
package familyguy.dunkru;

public class BeefSandwich extends Sandwich {
    public static final double BEEF_SANDWICH_PRICE = 10.99;

    /**
     * Constructs a BeefSandwich object with the specified quantity and bread type.
     *
     * @param quantity   The quantity of beef sandwiches.
     * @param breadType  The type of bread for the sandwich.
     */
    public BeefSandwich(int quantity, String breadType) {
        super(BEEF_SANDWICH_PRICE, quantity, "Beef", breadType);
    }
}
