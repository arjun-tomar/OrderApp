/**
 * Controller for the main menu
 * @author Aaron Ordonez, Arjun Tomar
 */
package familyguy.dunkru;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Modality;

import java.io.IOException;

public class MainMenuController {
    @FXML
    private ImageView imageDonuts;
    @FXML
    private ImageView imageCoffee;

    @FXML
    private ImageView imageSandwich;

    @FXML
    private ImageView imageCurrent;

    @FXML
    private ImageView imageAll;
    Image donut = new Image((getClass().getResourceAsStream("otherdonuts.jpg")));
    Image coffee = new Image((getClass().getResourceAsStream("othercoffee.jpg")));
    Image sandwich = new Image((getClass().getResourceAsStream("othersandwich.jpg")));
    Image allOrdersIcon = new Image((getClass().getResourceAsStream("allorderslogo.png")));
    Image currentOrderIcon = new Image((getClass().getResourceAsStream("currentorderlogo.png")));
    @FXML
    private Label titleMain;
    @FXML
    private Button viewCurrent;

    @FXML
    private Button viewAll;

    @FXML
    private Button donutOrder;

    @FXML
    private Button sandwichOrder;

    @FXML
    private Button coffeeOrder;

    @FXML
    private HBox hboxOrder;

    @FXML
    private HBox hboxCurrent;

    @FXML
    private HBox hboxAll;
    private Order currentOrder;
    private OrderList allOrders;

    /**
     * Initializes the main menu controller.
     * Creates a new order and initializes the images.
     */
    @FXML
    public void initialize(){
        currentOrder = new Order();
        allOrders = new OrderList();
        allOrders.add(currentOrder);

        imageCurrent.setImage(currentOrderIcon);
        imageAll.setImage(allOrdersIcon);
        imageCoffee.setImage(coffee);
        imageDonuts.setImage(donut);
        imageSandwich.setImage(sandwich);
    }

    /**
     * Handles the action event for ordering donuts.
     * Loads the "OrderDonuts.fxml" file and opens a new stage for ordering donuts.
     *
     * @param event The action event triggered by clicking the "Order Donuts" button.
     * @throws IOException If an I/O exception occurs while loading the FXML file.
     */
    @FXML
    public void orderDonut(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderDonuts.fxml"));
        Parent root = loader.load();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Order Donuts");

        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setResizable(false);

        OrderDonutsController controller = loader.getController();
        controller.initOrder(currentOrder);

        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.show();

    }

    /**
     * Handles the action event for ordering coffee.
     * Loads the "OrderCoffee.fxml" file and opens a new stage for ordering coffee.
     *
     * @param event The action event triggered by clicking the "Order Coffee" button.
     * @throws IOException If an I/O exception occurs while loading the FXML file.
     */
    @FXML
    public void orderCoffee(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderCoffee.fxml"));
        Parent root = loader.load();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Order Coffee");

        OrderCoffeeController controller = loader.getController();
        controller.initOrder(currentOrder);


        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.show();
    }

    /**
     * Handles the action event for ordering sandwiches.
     * Loads the "OrderSandwiches.fxml" file and opens a new stage for ordering sandwiches.
     *
     * @param event The action event triggered by clicking the "Order Sandwiches" button.
     * @throws IOException If an I/O exception occurs while loading the FXML file.
     */
    @FXML
    public void orderSandwich(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("OrderSandwiches.fxml"));
        Parent root = loader.load();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Order Sandwiches");

        OrderSandwichesController controller = loader.getController();
        controller.initOrder(currentOrder);

        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setResizable(false);
        newStage.show();

    }

    /**
     * Handles the action event for viewing the current order.
     * Loads the "CurrentOrder.fxml" file and opens a new stage for viewing the current order.
     * Updates the current order and all orders after the viewing is complete.
     *
     * @param event The action event triggered by clicking the "View Current" button.
     * @throws IOException If an I/O exception occurs while loading the FXML file.
     */
    @FXML
    public void viewCurrent(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("CurrentOrder.fxml"));
        Parent root = loader.load();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("Current Order");

        CurrentOrderController controller = loader.getController();
        controller.initOrder(currentOrder, allOrders);

        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setResizable(false);
        newStage.showAndWait();

        int numStoreOrders = allOrders.returnOrder().size()-1;
        currentOrder = allOrders.returnOrder().get(numStoreOrders);
    }

    /**
     * Handles the action event for viewing all orders.
     * Loads the "AllOrders.fxml" file and opens a new stage for viewing all orders.
     *
     * @param event The action event triggered by clicking the "View All" button.
     * @throws IOException If an I/O exception occurs while loading the FXML file.
     */
    @FXML
    public void viewAll(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AllOrders.fxml"));
        Parent root = loader.load();

        Stage newStage = new Stage();
        newStage.setScene(new Scene(root));
        newStage.setTitle("All Orders");

        AllOrdersController controller = loader.getController();
        controller.initOrder(allOrders);

        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setResizable(false);
        newStage.showAndWait();
    }
}
