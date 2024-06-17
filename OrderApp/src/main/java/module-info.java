module familyguy.dunkru {
    requires javafx.controls;
    requires javafx.fxml;


    opens familyguy.dunkru to javafx.fxml;
    exports familyguy.dunkru;
}