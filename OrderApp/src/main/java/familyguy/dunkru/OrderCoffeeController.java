/**
 * Controller class for handling coffee order functionality
 *
 * @author Aaron Ordonez, Arjun Tomar
 */
package familyguy.dunkru;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import java.text.DecimalFormat;

public class OrderCoffeeController {
    @FXML
    private Button addOrderButton;

    @FXML
    private CheckBox caramel;

    @FXML
    private CheckBox frenchvanilla;

    @FXML
    private CheckBox irishcream;

    @FXML
    private CheckBox mocha;

    @FXML
    private ChoiceBox<Integer> quantitychoices;

    @FXML
    private ChoiceBox<String> sizechoices;

    @FXML
    private TextField subtotal;

    @FXML
    private CheckBox sweetcream;

    private Order currentOrder;
    private String[] sizes = {"Short","Tall","Grande","Venti"};
    private String[] addIns = {"Cream","Milk","Syrup","Caramel","Whipped Cream"};
    private Integer[] quantities = {1,2,3,4,5,6};

    /**
     * Sets the current order for the coffee order controller.
     *
     * @param currentOrder The current order.
     */
    public void initOrder(Order currentOrder){
        this.currentOrder = currentOrder;
    }
    /**
     * Initializes the coffee order controller.
     */
    @FXML
    public void initialize() {
        sizechoices.getItems().addAll(sizes);
        quantitychoices.getItems().addAll(quantities);

        sizechoices.getSelectionModel().select(0);
        quantitychoices.getSelectionModel().select(0);
        updateSubtotal();

        sizechoices.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateSubtotal());
        quantitychoices.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> updateSubtotal());

        sweetcream.setOnAction(event -> updateSubtotal());
        irishcream.setOnAction(event -> updateSubtotal());
        caramel.setOnAction(event -> updateSubtotal());
        frenchvanilla.setOnAction(event -> updateSubtotal());
        mocha.setOnAction(event -> updateSubtotal());
    }

    /**
     * Updates the subtotal based on the selected options.
     */
    private void updateSubtotal() {
        int quantity = quantitychoices.getValue();
        String size = sizechoices.getValue();
        Coffee coffee = new Coffee(quantity, size);

        if (sweetcream.isSelected()){
            coffee.addAddIn("Sweet Cream");
        }
        if (mocha.isSelected()){
            coffee.addAddIn("Mocha");
        }
        if (frenchvanilla.isSelected()){
            coffee.addAddIn("French Vanilla");
        }
        if (caramel.isSelected()){
            coffee.addAddIn("Caramel");
        }
        if (irishcream.isSelected()){
            coffee.addAddIn("Irish Cream");
        }

        double price  = coffee.price();
        DecimalFormat df = new DecimalFormat("0.00");
        String curPriceStr = df.format(price);
        subtotal.setText("$" + curPriceStr);
    }

    /**
     * Handles adding the coffee order to the current order.
     *
     * @param event The ActionEvent triggering the method call.
     */
    @FXML
    void addOrder(ActionEvent event) {
        int quantity = quantitychoices.getValue();
        String size = sizechoices.getValue();
        Coffee coffee = new Coffee(quantity, size);

        if (sweetcream.isSelected()){
            coffee.addAddIn("Sweet Cream");
        }
        if (mocha.isSelected()){
            coffee.addAddIn("Mocha");
        }
        if (frenchvanilla.isSelected()){
            coffee.addAddIn("French Vanilla");
        }
        if (caramel.isSelected()){
            coffee.addAddIn("Caramel");
        }
        if (irishcream.isSelected()){
            coffee.addAddIn("Irish Cream");
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to add this item(s) to the current order?", ButtonType.YES, ButtonType.NO);
        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
        if (ButtonType.YES.equals(result)) {
            currentOrder.add(coffee);
        }
    }


}
