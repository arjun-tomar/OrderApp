/**
 * Represents a fish sandwich and extends sandwich
 * @author Aaron Ordonez, Arjun Tomar
 */
package familyguy.dunkru;

public class FishSandwich extends Sandwich {
    public static final double FISH_SANDWICH_PRICE = 9.99;

    /**
     * Constructs a FishSandwich object with the specified quantity and bread type.
     *
     * @param quantity   The quantity of fish sandwiches.
     * @param breadType  The type of bread used in the sandwich.
     */
    public FishSandwich(int quantity, String breadType) {
        super(FISH_SANDWICH_PRICE, quantity, "Fish", breadType);
    }
}
