/**
 * controller for when the user orders sandwiches
 *
 * @author Aaron Ordonez, Arjun Tomar
 */
package familyguy.dunkru;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class OrderSandwichesController {
    @FXML
    private Label sandwichTitle;
    @FXML
    private ImageView sandwichImage;
    @FXML
    private Label breadLabel;
    @FXML
    private Label proteinLabel;
    @FXML
    private Label addLabel;
    @FXML
    private Label subLabel;
    @FXML
    private TextField total;
    @FXML
    private Button addOrder;
    @FXML
    private RadioButton bagelBread;
    @FXML
    private RadioButton wheatBread;
    @FXML
    private RadioButton sourBread;
    @FXML
    private AnchorPane AnchorP;
    @FXML
    private RadioButton beef;
    @FXML
    private RadioButton fish;
    @FXML
    private RadioButton chicken;
    @FXML
    private GridPane breadPane;
    @FXML
    private GridPane addPane;
    @FXML
    private GridPane proteinPane;
    @FXML
    private CheckBox lettuce;
    @FXML
    private CheckBox tomato;
    @FXML
    private CheckBox cheese;
    @FXML
    private CheckBox onion;
    @FXML
    private ToggleGroup proteinGroup;
    @FXML
    private ToggleGroup breadTypeGroup;
    private double subtotal = 0.00;

    private static final double cheeses = 1.00;
    private static final double lettuces = 0.30;
    private static final double tomatoes = 0.30;
    private static final double onions = 0.30;
    private Order currentOrder;

    /**
     * Initializes the current order for the controller.
     *
     * @param currentOrder The current order to be initialized.
     */
    public void initOrder(Order currentOrder){
        this.currentOrder = currentOrder;
    }

    /**
     * Initializes the controller.
     */
    @FXML
    public void initialize() {
        total.setText(String.format("$%.2f", BeefSandwich.BEEF_SANDWICH_PRICE));


        beef.setOnAction(event -> updateSubtotal());
        fish.setOnAction(event -> updateSubtotal());
        chicken.setOnAction(event -> updateSubtotal());

        lettuce.setOnAction(event -> updateSubtotal());
        tomato.setOnAction(event -> updateSubtotal());
        cheese.setOnAction(event -> updateSubtotal());
        onion.setOnAction(event -> updateSubtotal());
    }

    /**
     * Updates the subtotal based on the selected sandwich and additional toppings.
     */
    private void updateSubtotal() {
        subtotal = 0.00;


        if (beef.isSelected()) {
            subtotal += BeefSandwich.BEEF_SANDWICH_PRICE;
        }
        else if (fish.isSelected()) {
            subtotal += FishSandwich.FISH_SANDWICH_PRICE;
        }
        else if (chicken.isSelected()){
            subtotal += ChickenSandwich.CHICKEN_SANDWICH_PRICE;
        }


        if (lettuce.isSelected()) {
            subtotal += lettuces;
        }
        if (tomato.isSelected()) {
            subtotal += tomatoes;
        }
        if (cheese.isSelected()) {
            subtotal += cheeses;
        }
        if (onion.isSelected()) {
            subtotal += onions;
        }

        // Update the subtotal label
        total.setText(String.format("$%.2f", subtotal));
    }

    /**
     * Adds the selected sandwich with toppings to the current order.
     */
    @FXML
    public void addToOrder() {
        RadioButton selectedBread = (RadioButton) breadTypeGroup.getSelectedToggle();
        String breadType = selectedBread.getText();

        Sandwich barebones;

        RadioButton selectedProtein = (RadioButton) proteinGroup.getSelectedToggle();
        switch(selectedProtein.getText()){
            case "Beef":
                barebones = new BeefSandwich(1, breadType);
                break;
            case "Fish":
                barebones = new FishSandwich(1, breadType);
                break;
            case "Chicken":
                barebones = new ChickenSandwich(1, breadType);
                break;
            default:
                barebones = new Sandwich();
                break;
        }

        if (lettuce.isSelected()) {
            barebones.addAddOn("Lettuce");
        }
        if (tomato.isSelected()) {
            barebones.addAddOn("Tomato");
        }
        if (cheese.isSelected()) {
            barebones.addAddOn("Cheese");
        }
        if (onion.isSelected()) {
            barebones.addAddOn("Onion");
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to add this item(s) to the current order?", ButtonType.YES, ButtonType.NO);
        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
        if (ButtonType.YES.equals(result)) {
            currentOrder.add(barebones);
        }

    }
}
