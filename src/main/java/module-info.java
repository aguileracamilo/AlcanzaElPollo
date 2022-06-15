module com.alcanzaelpollo.alcanzaelpollo {
    requires javafx.controls;
    requires java.base;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.desktop;
    requires java.sql;

    opens com.alcanzaelpollo to javafx.fxml;
    opens com.alcanzaelpollo.controlador to javafx.fxml, javafx.controls;
    exports com.alcanzaelpollo;
}