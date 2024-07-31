module com.workmanager.workmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.jdi;
    requires mysql.connector.j;
    requires animatefx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.ikonli.bootstrapicons;


    opens com.ProWorks to javafx.fxml;
    exports com.ProWorks;
}