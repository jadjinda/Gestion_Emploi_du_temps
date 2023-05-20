module com.example.interfacegraphique {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.interfacegraphique to javafx.fxml;
    exports com.example.interfacegraphique;
}