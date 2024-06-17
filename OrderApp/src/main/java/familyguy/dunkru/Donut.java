/**
 * Represents a donut and extends menuitem
 * @author Aaron Ordonez, Arjun Tomar
 */
package familyguy.dunkru;

import java.util.ArrayList;
import java.util.Collections;

public class Donut extends MenuItem {
    private ArrayList<String> flavors = new ArrayList<>();
    private String type;

    /**
     * Constructs a Donut object with the specified price, quantity, and type.
     *
     * @param price    The price of the donut.
     * @param quantity The quantity of donuts.
     * @param type     The type of the donut.
     */
    public Donut(double price, int quantity, String type) {
        super(price, quantity);
        this.type = type;
    }

    /**
     * Adds a donut with a flavor.
     *
     * @param addInName The flavor to be added.
     * @return true if the flavor was added successfully, otherwise false.
     */
    public boolean add(String addInName){
        if (flavors.add(addInName)){
            Collections.sort(flavors);
            return true;
        }
        return false;
    }
    /**
     * Removes a donut with a flavor.
     *
     * @param addInName The flavor to be removed.
     * @return true if the flavor was removed successfully, otherwise false.
     */
    public boolean remove(String addInName){
        if (flavors.remove(addInName)){
            Collections.sort(flavors);
            return true;
        }
        return false;
    }

    /**
     * Sets the type of the donut.
     *
     * @param type The type of the donut.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Calculates the price of the donut item.
     *
     * @return The total price of the donut item.
     */
    @Override
    public double price() {
        // This method is overridden by subclasses, so not implemented here.
        return 0; // Default return, not used.
    }
    /**
     * Returns a string representation of the donut item.
     *
     * @return A string containing the flavors of the donut.
     */
    @Override
    public String toString(){
        return flavors.toString();
    }
}
