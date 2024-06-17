/**
 * manages lists of orders
 *
 * @author Aaron Ordonez, Arjun Tomar
 */
package familyguy.dunkru;

import java.util.ArrayList;

public class OrderList {
    private ArrayList<Order> orders = new ArrayList<Order>();

    /**
     * Adds an order to the list.
     *
     * @param object The order to add.
     * @return true if the order was added successfully, otherwise false.
     */
    public boolean add(Object object){
        if( object instanceof Order){
            Order orderItems = (Order)object;
            if(orderItems == null) return false;
            orders.add(orderItems);
            return true;
        }else{
            return false;
        }
    }

    /**
     * Removes an order from the list.
     *
     * @param object The order to remove.
     * @return true if the order was removed successfully, otherwise false.
     */
    public boolean remove(Object object){
        if( object instanceof Order){
            Order orderItems = (Order)object;
            if(orderItems == null) return false;
            if(!orders.contains(orderItems)) return false;
            orders.remove(orderItems);
            return true;
        }else {
            return false;
        }
    }

    /**
     * Returns the list of orders.
     *
     * @return The list of orders.
     */
    public ArrayList<Order> returnOrder() { return orders;}

}
