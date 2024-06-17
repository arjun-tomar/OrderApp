/**
 * Represents a coffee and extends menuitem
 * @author Aaron Ordonez, Arjun Tomar
 */
package familyguy.dunkru;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class Coffee extends MenuItem {
    private ArrayList<String> addIns = new ArrayList<>();
    private static final double ADD_IN_COST = 0.30;
    private static final double SHORT_COST = 1.99;
    private static final double TALL_COST = 2.49;
    private static final double GRANDE_COST = 2.99;
    private static final double VENTI_COST = 3.09;
    private String size;

    /**
     * Constructs a Coffee object with the specified quantity and size.
     *
     * @param quantity The quantity of coffee items.
     * @param size     The size of the coffee (Short, Tall, Grande, or Venti).
     */
    public Coffee(int quantity, String size) {
        super(0, quantity); // Price will be calculated dynamically
        this.size = size;
    }

    /**
     * Adds an add-in to the coffee.
     *
     * @param addIn The add-in to be added.
     * @return true if the add-in was added successfully, otherwise false.
     */
    public boolean addAddIn(String addIn) {
        return addIns.add(addIn);
    }

    /**
     * Removes an add-in from the coffee.
     *
     * @param addIn The add-in to be removed.
     * @return true if the add-in was removed successfully, otherwise false.
     */
    public boolean removeAddIn(String addIn) {
        return addIns.remove(addIn);
    }

    /**
     * Calculates the price of the coffee item.
     *
     * @return The total price of the coffee item.
     */
    @Override
    public double price() {
        double basePrice;
        switch (size) {
            case "Short":
                basePrice = SHORT_COST;
                break;
            case "Tall":
                basePrice = TALL_COST;
                break;
            case "Grande":
                basePrice = GRANDE_COST;
                break;
            case "Venti":
                basePrice = VENTI_COST;
                break;
            default:
                basePrice = 0;
        }

        double addInCost = addIns.size() * ADD_IN_COST;
        double totalPrice = (basePrice + addInCost) * getQuantity();

        return totalPrice;
    }

    /**
     * Returns a string representation of the coffee item.
     *
     * @return A string containing the quantity, size, add-ins, and price of the coffee item.
     */
    @Override
    public String toString(){
        DecimalFormat df = new DecimalFormat("0.00");
        String curPriceStr = "$" + df.format(price());
        String outputString = "Coffee(" + getQuantity() + ") " + size;
        if (!addIns.isEmpty()){
            outputString = outputString + " [" + String.join(", ", addIns) + "]";
        }
        return outputString;
    }

}
