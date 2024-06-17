package familyguy.dunkru;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class CurrentOrderController {

    @FXML
    private ListView<MenuItem> itemsList;

    @FXML
    private Button placeOrderButton;

    @FXML
    private Button removeButton;

    @FXML
    private TextField salesTax;

    @FXML
    private TextField subtotal;

    @FXML
    private TextField total;

    private Order currentOrder;
    private OrderList allOrders;
    private ArrayList<MenuItem> orderList;
    public void initOrder(Order currentOrder, OrderList allOrders) {
        this.currentOrder = currentOrder;
        this.allOrders = allOrders;

        orderList = currentOrder.getCurrentOrder();
        updateTotals();

        itemsList.getItems().removeAll(itemsList.getItems());
        for(int i = 0; i < orderList.size(); i++) {
            itemsList.getItems().addAll(orderList.get(i));
        }
    }

    private void updateTotals(){
        currentOrder.updateTotals();
        double subTotal = currentOrder.getSubTotal();
        double salesTaxD = currentOrder.getTax();
        double totalD = currentOrder.getTotal();

        DecimalFormat df = new DecimalFormat("0.00");
        String curSubtotalStr = df.format(subTotal);
        subtotal.setText("$ " + curSubtotalStr);
        String curSalesTaxStr = df.format(salesTaxD);
        salesTax.setText("$ " + curSalesTaxStr);
        String curTotalStr = df.format(totalD);
        total.setText("$ " + curTotalStr);
    }

    @FXML
    void removeOrder(ActionEvent event) {
        MenuItem selectedItem = itemsList.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like "
                + "to remove this item from the order?", ButtonType.YES, ButtonType.NO);
        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
        if (ButtonType.YES.equals(result)) {
            if (currentOrder.remove(selectedItem)) {
                itemsList.getItems().remove(selectedItem);
                updateTotals();

            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Cannot Remove Item");
                a.setContentText("\tNo Items Have Been Selected\n \tOr Item Does Not Exist!");
                a.show();
            }
        }
    }

    @FXML
    void placeOrders(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like " +
                "to place this order?", ButtonType.YES, ButtonType.NO);

        if(orderList.size() != 0) {
            ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
            if (ButtonType.YES.equals(result)) {
                Order newOrder = new Order();
                allOrders.add(newOrder);


                Stage stage = (Stage) placeOrderButton.getScene().getWindow();
                stage.close();
            }
        }else{
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Cannot Place Order");
            a.setContentText("\tNo Items Have Been Ordered!");
            a.show();
        }

    }
}
