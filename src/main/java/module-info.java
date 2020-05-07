module com.sample {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.sample.controllers to javafx.fxml;
    opens com.sample.components to javafx.base;
    opens com.sample.users to javafx.base;
    exports com.sample;
}