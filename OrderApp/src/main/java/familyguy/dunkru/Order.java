/**
 * Represents an order that a user places
 *
 * @author Aaron Ordonez, Arjun Tomar
 */
package familyguy.dunkru;

import java.util.ArrayList;

public class Order {
    private ArrayList<MenuItem> itemsArray = new ArrayList<MenuItem>(); //Initial Capacity of 10
    private double subTotal;
    private double salesTax;
    private double total;
    public static int runningOrderNumber;
    private static final double SALES_TAX_RATE = 0.06625;
    private int orderNumber;

    /**
     * Constructs an Order object with default values.
     */
    public Order(){
        subTotal = 0;
        salesTax = 0;
        total = 0;
        runningOrderNumber++;
        orderNumber = runningOrderNumber;

    }

    /**
     * Adds a MenuItem to the current order.
     *
     * @param object The MenuItem to be added.
     * @return true if the MenuItem was successfully added, otherwise false.
     */
    public boolean add(Object object){
        if(object instanceof MenuItem) {
            MenuItem menuItem = (MenuItem) object;
            if(menuItem == null) return false;

            itemsArray.add(menuItem);
            calculateSubTotal();
            return true;
        }else
        {
            return false;
        }

    }

    /**
     * Removes a MenuItem from the current order.
     *
     * @param object The MenuItem to be removed.
     * @return true if the MenuItem was successfully removed, otherwise false.
     */
    public boolean remove(Object object){
        if(object instanceof MenuItem){
            MenuItem menuItem = (MenuItem) object;
            if(!itemsArray.contains(menuItem)) return false;
            itemsArray.remove(menuItem);
            calculateSubTotal();
            return true;
        } else {
            return false;
        }

    }

    /**
     * Calculates the subtotal of the order.
     */
    private void calculateSubTotal(){
        double tempTotal = 0;

        for(int i=0; i<itemsArray.size(); i++){
            tempTotal += itemsArray.get(i).price();
        }

        subTotal = tempTotal;
    }

    /**
     * Updates the sales tax and total of the order.
     */
    public void updateTotals(){
        salesTax  = subTotal * SALES_TAX_RATE;
        total = subTotal + salesTax;
    }

    /**
     * Retrieves the subtotal of the order.
     *
     * @return The subtotal of the order.
     */
    public double getSubTotal(){
        return subTotal;
    }

    /**
     * Retrieves the total amount of the order.
     *
     * @return The total amount of the order.
     */
    public double getTotal(){
        return total;
    }

    /**
     * Retrieves the sales tax amount of the order.
     *
     * @return The sales tax amount of the order.
     */
    public double getTax() {return salesTax;}

    /**
     * Retrieves the list of items in the current order.
     *
     * @return The list of items in the current order.
     */
    public ArrayList<MenuItem> getCurrentOrder() {
        return itemsArray;
    }

    /**
     * Retrieves the order number.
     *
     * @return The order number.
     */
    public int getOrderNumber(){
        return orderNumber;
    }
}
