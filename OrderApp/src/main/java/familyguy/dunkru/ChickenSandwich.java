/**
 * Represents a chicken sandwich and extends the sandwich class
 * @author Aaron Ordonez, Arjun Tomar
 */
package familyguy.dunkru;

public class ChickenSandwich extends Sandwich {
    public static final double CHICKEN_SANDWICH_PRICE = 8.99;

    /**
     * Constructs a ChickenSandwich object with the specified quantity and bread type.
     *
     * @param quantity   The quantity of chicken sandwiches.
     * @param breadType  The type of bread for the sandwich.
     */
    public ChickenSandwich(int quantity, String breadType) {
        super(CHICKEN_SANDWICH_PRICE, quantity, "Chicken", breadType);
    }
}
