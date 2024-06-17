/**
 * Controller class for handling donut order functionality
 *
 * @author Aaron Ordonez, Arjun Tomar
 */
package familyguy.dunkru;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;


public class OrderDonutsController{

    private double finalPrice;
    @FXML
    private Label OrderDonutsBillboard;
    @FXML
    private Button addToOrder;
    @FXML
    private TextField textSubtotal;
    @FXML
    private Label labelSubtotal;
    @FXML
    private ChoiceBox<String> donutChoiceBox;

    @FXML
    private ListView<String> donutListView;

    @FXML
    private ListView<String> donutListViewTransfer;

    @FXML
    private Button transferTo;

    @FXML
    private Button transferFrom;

    @FXML
    private ComboBox<Integer> donutQuantity;

    @FXML
    private ImageView donutImage;

    private final double yeastPrice = 1.79;
    private final double cakePrice = 1.89;
    private final double holePrice = 0.39;

    Image yeast = new Image((getClass().getResourceAsStream("yeast_donut.jpg")));
    Image cake = new Image((getClass().getResourceAsStream("cake_donut.jpg")));
    Image holes = new Image((getClass().getResourceAsStream("donut_hole.jpg")));
    private String[] DONUT_TYPES = {"yeast donuts", "donut holes", "cake donuts"};
    private final int MIN_VALUE = 1;
    private final int MAX_VALUE = 12;
    private Order currentOrder;
    private ObservableList<String> originalDonutFlavors;
    ObservableList<Integer> integers = FXCollections.observableArrayList();



    /**
     * Initializes the OrderDonutsController.
     */
    public void initialize() {
        originalDonutFlavors = FXCollections.observableArrayList();
        originalDonutFlavors.addAll(YeastDonut.YEAST_FLAVORS);
        originalDonutFlavors.addAll(DonutHole.HOLE_FLAVORS);
        originalDonutFlavors.addAll(CakeDonut.CAKE_FLAVORS);

        donutChoiceBox.getItems().clear();
        donutChoiceBox.getItems().addAll(DONUT_TYPES);
        donutChoiceBox.setValue(DONUT_TYPES[0]);

        ObservableList<String> items = FXCollections.observableArrayList(YeastDonut.YEAST_FLAVORS);
        for (int i = MIN_VALUE; i <= MAX_VALUE; i++) {
            integers.add(i);
        }
        donutListView.setItems(items);
        donutQuantity.setItems(integers);
        donutQuantity.setValue(integers.getFirst());
        donutChoiceBox.setOnAction(this::getFood);
    }

    /**
     * Updates the list of available donut flavors based on the selected donut type.
     *
     * @param event The ActionEvent triggering the method call.
     */
    public void getFood(ActionEvent event) {
        ObservableList<String> items = FXCollections.observableArrayList();
        String myDonut = donutChoiceBox.getValue();
        donutListView.getItems().clear();
        items.clear();
        if (Objects.equals(myDonut, "yeast donuts")) {
            items.addAll(YeastDonut.YEAST_FLAVORS);
            donutImage.setImage(yeast);
        } else if (Objects.equals(myDonut, "donut holes")) {
            items.addAll(DonutHole.HOLE_FLAVORS);
            donutImage.setImage(holes);
        } else if (Objects.equals(myDonut, "cake donuts")) {
            items.addAll(CakeDonut.CAKE_FLAVORS);
            donutImage.setImage(cake);
        }
        donutListView.getItems().addAll(items);
    }

    /**
     * Handles adding the selected donut flavor to the order and updates the subtotal.
     *
     * @param event The ActionEvent triggering the method call.
     */
    @FXML
    public void getFlavor(ActionEvent event) {
        int quantity = (donutQuantity.getValue());
        ObservableList<String> selectedItems = donutListView.getSelectionModel().getSelectedItems();
        if (!selectedItems.isEmpty()) {
            String selectedFlavor = selectedItems.get(0);
            donutListView.getItems().remove(selectedFlavor);
            donutListViewTransfer.getItems().add(quantity + "x " + selectedFlavor);
            updatePrice(selectedFlavor, quantity, true);
        }
    }

    /**
     * Handles removing the selected donut flavor from the order and updates the subtotal.
     *
     * @param event The ActionEvent triggering the method call.
     */
    @FXML
    public void removeFlavor(ActionEvent event) {
        int quantity = donutQuantity.getValue();
        ObservableList<String> selectedItems = donutListViewTransfer.getSelectionModel().getSelectedItems();
        if (!selectedItems.isEmpty()) {
            String selectedFlavor = selectedItems.get(0);
            donutListViewTransfer.getItems().remove(selectedFlavor);
            donutListView.getItems().add(selectedFlavor.substring(selectedFlavor.indexOf(" ") + 3)); // Add flavor back to list
            updatePrice(selectedFlavor, quantity, false);
        }
    }

    /**
     * Adds the selected donut flavors to the current order.
     *
     * @param event The ActionEvent triggering the method call.
     */
    @FXML
    public void addToCurrent(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to add this item(s) to the current order?", ButtonType.YES, ButtonType.NO);
        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
        if (ButtonType.YES.equals(result)) {
            ObservableList<String> items = donutListViewTransfer.getItems();
            textSubtotal.setText("$0.00");

            for (String item : items) {
                String[] parts = item.split(" ", 2); // Split quantity and flavor
                int quantity = Integer.parseInt(parts[0].replace("x", "").trim());
                String flavor = parts[1].trim();
                Donut donut = createDonut(flavor, quantity);

                if (donut != null) {
                    donut.add(flavor);
                    currentOrder.add(donut);
                }
            }

            donutListViewTransfer.getItems().clear();
        }
    }

    /**

     Creates a donut object based on the given flavor and quantity.
     @param flavor The flavor of the donut to be created.
     @param quantity The quantity of donuts to be created.
     @return A Donut object with the specified flavor and quantity, or null if the flavor is not recognized.
     */
    private Donut createDonut(String flavor, int quantity) {
        if (containsIgnoreCase(YeastDonut.YEAST_FLAVORS, flavor)) {
            return new YeastDonut(quantity);
        } else if (containsIgnoreCase(CakeDonut.CAKE_FLAVORS, flavor)) {
            return new CakeDonut(quantity);
        } else if (containsIgnoreCase(DonutHole.HOLE_FLAVORS, flavor)) {
            return new DonutHole(quantity);
        }
        return null; // Flavor not recognized
    }

    /**

     Checks if the given array contains a string that matches the specified key ignoring case.
     @param array The array of strings to search through.
     @param key The key to search for in the array.
     @return true if the array contains a string that matches the key ignoring case, false otherwise.
     */
    private boolean containsIgnoreCase(String[] array, String key) {
        for (String str : array) {
            if (str.equalsIgnoreCase(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Initializes the current order for the donut order controller.
     *
     * @param currentOrder The current order.
     */

    public void initOrder(Order currentOrder){
        this.currentOrder = currentOrder;
    }

    /**
     * Updates the price based on the selected flavor and quantity.
     *
     * @param flavor   The selected donut flavor.
     * @param quantity The quantity of the selected flavor.
     * @param add      true if adding, false if removing.
     */
    private void updatePrice(String flavor, int quantity, boolean add) {
        if (add) {
            if (donutChoiceBox.getValue().equals("yeast donuts")) {
                finalPrice += yeastPrice * quantity;
            } else if (donutChoiceBox.getValue().equals("donut holes")) {
                finalPrice += holePrice * quantity;
            } else if (donutChoiceBox.getValue().equals("cake donuts")) {
                finalPrice += cakePrice * quantity;
            }
        } else {
            if (donutChoiceBox.getValue().equals("yeast donuts")) {
                finalPrice -= yeastPrice * quantity;
            } else if (donutChoiceBox.getValue().equals("donut holes")) {
                finalPrice -= holePrice * quantity;
            } else if (donutChoiceBox.getValue().equals("cake donuts")) {
                finalPrice -= cakePrice * quantity;
            }
        }
        if (finalPrice < 0.00) {
            finalPrice = 0.00;
        }
        DecimalFormat df = new DecimalFormat("0.00");
        String curPriceStr = df.format(finalPrice);
        textSubtotal.setText("$" + curPriceStr);
    }
}
