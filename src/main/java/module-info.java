module com.vat.vormanalysetool {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires java.sql;
    requires gson;

    opens com.vat to javafx.fxml;
    exports com.vat.frame;
    opens com.vat.frame to javafx.fxml;
}