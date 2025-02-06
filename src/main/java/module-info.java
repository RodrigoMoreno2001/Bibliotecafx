module com.example.bibliotecafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires jakarta.persistence;
    requires java.naming;
    requires org.hibernate.orm.core;
    exports com.example.bibliotecafx.app;
    opens com.example.bibliotecafx.app to javafx.fxml;
    opens com.example.bibliotecafx.modelo.entities to javafx.base, org.hibernate.orm.core;
    opens com.example.bibliotecafx.controlador to javafx.fxml;

    exports com.example.bibliotecafx.controlador;
}