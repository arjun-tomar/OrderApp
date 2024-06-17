/**
 * represents a sandwich item that the user can order
 *
 * @author Aaron Ordonez, Arjun Tomar
 */
package familyguy.dunkru;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Sandwich extends MenuItem {
    private String type;
    private String breadType;
    private ArrayList<String> addOns = new ArrayList<>();

    /**
     * Constructs a Sandwich object with the specified price, quantity, type, and bread type.
     *
     * @param price     The price of the sandwich.
     * @param quantity  The quantity of sandwiches.
     * @param type      The type of sandwich (e.g., "Chicken", "Fish").
     * @param breadType The type of bread used for the sandwich.
     */
    public Sandwich(double price, int quantity, String type, String breadType) {
        super(price, quantity);
        this.type = type;
        this.breadType = breadType;
    }

    /**
     * Constructs a default Sandwich object with zero price and one quantity.
     */
    public Sandwich() {
        super(0, 1);
        this.type = "None";
        this.breadType = "None";
    }

    /**
     * Adds an add-on to the sandwich.
     *
     * @param addOn The add-on to be added (e.g., "cheese", "lettuce").
     * @return true if the add-on was added successfully, false otherwise.
     */
    public boolean addAddOn(String addOn) {
        if(addOn.equalsIgnoreCase("cheese") && addOns.contains("cheese")){
            return false;
        }
        return addOns.add(addOn);
    }

    /**
     * Removes an add-on from the sandwich.
     *
     * @param addOn The add-on to be removed.
     * @return true if the add-on was removed successfully, false otherwise.
     */
    public boolean removeAddOn(String addOn) {
        return addOns.remove(addOn);
    }

    /**
     * Calculates the total price of the sandwich including add-ons.
     *
     * @return The total price of the sandwich.
     */
    @Override
    public double price() {
        double total = getPrice(); // Base price of the sandwich

        // Calculate additional cost for add-ons
        for (String addOn : addOns) {
            if (addOn.equalsIgnoreCase("cheese")) {
                total += 1.00; // Cheese costs $1 extra
            } else {
                total += 0.30; // Veggie add-ons cost $0.30 extra each
            }
        }
        total = total * getQuantity();
        total = Math.round(total * 100.0) / 100.0; // handling floating point error, unaware if this counts as a magic #
        return total;
    }

    /**
     * Returns a string representation of the sandwich.
     *
     * @return A string representation of the sandwich.
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        String curPriceStr = "$" + df.format(price());

        StringBuilder addOnsStr = new StringBuilder();
        for (String addOn : addOns) {
            addOnsStr.append(addOn).append(", ");
        }

        if (addOnsStr.length() > 0) {
            addOnsStr.delete(addOnsStr.length() - 2, addOnsStr.length());
            return getQuantity() + "x " + breadType + " " + type + " Sandwich with " + addOnsStr.toString();
        } else {
            return getQuantity() + "x " + breadType + " " + type + " Sandwich";
        }
    }

}
