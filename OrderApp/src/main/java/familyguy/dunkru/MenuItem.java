/**
 * The abstract base class for all menu items
 *
 * @author Aaron Ordonez, Arjun Tomar
 */
package familyguy.dunkru;

public abstract class MenuItem {
    private double price;
    private int quantity;
    /**
     * Abstract method to calculate the price of the menu item.
     *
     * @return The price of the menu item.
     */
    public abstract double price();

    /**
     * Constructs a MenuItem object with the specified price and quantity.
     *
     * @param price    The price of the menu item.
     * @param quantity The quantity of the menu item.
     */
    public MenuItem(double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * Retrieves the price of the menu item.
     *
     * @return The price of the menu item.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the menu item.
     *
     * @param price The price to be set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Retrieves the quantity of the menu item.
     *
     * @return The quantity of the menu item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the menu item.
     *
     * @param quantity The quantity to be set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
