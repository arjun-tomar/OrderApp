/**
 * Controller class for managing all orders.
 *
 * @author Aaron Ordonez, Arjun Tomar
 */
package familyguy.dunkru;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class AllOrdersController {
    private Order currentOrder;
    private ArrayList<Order> storeOrdersList;
    private int numStoreOrders;
    private double total = 0;
    private OrderList allOrders;
    private ArrayList<MenuItem> orderList;

    @FXML
    private ComboBox<Integer> box_orderlist;

    @FXML
    private ListView<MenuItem> list_orderlist;

    @FXML
    private TextField totalAmount;

    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() {
        list_orderlist.getItems().clear();
        box_orderlist.getItems().clear();
        updateTotalField();

        box_orderlist.setOnAction(event -> {
            int selectedIndex = box_orderlist.getSelectionModel().getSelectedIndex();
            setOrderList(selectedIndex);
        });
    }

    /**
     * Cancels the selected order.
     *
     * @param event The ActionEvent triggering the cancellation.
     */
    @FXML
    void cancelOrder(ActionEvent event) {
        if (currentOrder == null) {
            showAlert(Alert.AlertType.ERROR, "Cannot Cancel Order", "No Orders Exist!");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to cancel this order?", ButtonType.YES, ButtonType.NO);
        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
        if (ButtonType.YES.equals(result)) {
            if (allOrders.remove(currentOrder)) {
                storeOrdersList.remove(currentOrder);
                numStoreOrders--;
                clearOrderList();
                updateOrders();
            } else {
                showAlert(Alert.AlertType.ERROR, "Cannot Cancel Order", "No Orders Exist!");
            }
        }
    }

    /**
     * Exports the selected order details to a text file.
     *
     * @param event The ActionEvent triggering the export.
     */
    @FXML
    void exportOrder(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targetFile;

        Alert a = new Alert(Alert.AlertType.ERROR);

        if(numStoreOrders == 0){
            a.setTitle("Orders Could Not Be Exported");
            a.setContentText("\tThere are no orders to be exported!");
            a.show();
            return;
        }

        try{
            targetFile = chooser.showSaveDialog(stage);
            if(targetFile == null){
                a.setTitle("No File Selected");
                a.setContentText("\tPlease Select or Create a new File.");
                a.show();
                return;
            }

            FileWriter fileWriter = new FileWriter(targetFile, false);
            DecimalFormat df = new DecimalFormat("0.00");

            for(int index = 0; index < numStoreOrders; index++) {
                Order curOrder = storeOrdersList.get(index);
                String curSubTotalStr = df.format(curOrder.getSubTotal());
                String curTaxStr = df.format(curOrder.getTax());
                String curTotalStr = df.format(curOrder.getTotal());

                // Write order header
                fileWriter.write("Order Number: " + curOrder.getOrderNumber() + "\n");
                fileWriter.write("Sub Total: $" + curSubTotalStr + "\n");
                fileWriter.write("Sales Tax: $" + curTaxStr + "\n");
                fileWriter.write("Total: $" + curTotalStr + "\n\n");

                // Write order items
                fileWriter.write("Items:\n");
                ArrayList<MenuItem> tempOrder = curOrder.getCurrentOrder();
                for(int j = 0; j < tempOrder.size(); j++) {
                    fileWriter.write(tempOrder.get(j).toString() + "\n");
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        }catch(Exception e){
            a.setTitle("Orders Could Not Be Exported");
            a.setContentText("\tAn error has occurred selecting the file.\n");
            a.show();
            return;
        }
    }

    /**
     * Initializes the controller with the list of all orders.
     *
     * @param allOrders The list of all orders.
     */
    public void initOrder(OrderList allOrders) {
        this.allOrders = allOrders;
        storeOrdersList = allOrders.returnOrder();
        numStoreOrders = storeOrdersList.size()-1;
        updateOrders();
    }

    /**
     * Updates the orders ComboBox with the list of order numbers.
     */
    private void updateOrders() {
        if (numStoreOrders > 0) {
            for (int i = 0; i < numStoreOrders; i++) {
                box_orderlist.getItems().add(storeOrdersList.get(i).getOrderNumber());
            }
            box_orderlist.getSelectionModel().select(0);
            setOrderList(0);
        } else {
            currentOrder = null;
            updateTotalField();
        }
    }

    /**
     * Sets the selected order list.
     *
     * @param orderNumber The index of the order.
     */
    private void setOrderList(int orderNumber) {
        if (orderNumber >= 0 && orderNumber < storeOrdersList.size()) {
            currentOrder = storeOrdersList.get(orderNumber);
            orderList = currentOrder.getCurrentOrder();
            updateTotalField();
            list_orderlist.getItems().setAll(orderList);
        }
    }

    /**
     * Updates the total amount field.
     */
    private void updateTotalField() {
        if (currentOrder != null) {
            total = currentOrder.getTotal();
        } else {
            total = 0;
        }
        totalAmount.setText("$ " + new DecimalFormat("0.00").format(total));
    }

    /**
     * Clears the order list.
     */
    private void clearOrderList() {
        list_orderlist.getItems().clear();
        box_orderlist.getItems().clear();
    }

    /**
     * Displays an alert message.
     *
     * @param type    The type of the alert.
     * @param title   The title of the alert.
     * @param content The content of the alert.
     */
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }
}
