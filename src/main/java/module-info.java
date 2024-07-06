module com.workmanager.workmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.jdi;
    requires mysql.connector.j;
    requires animatefx;


    opens com.workmanager.workmanager to javafx.fxml;
    exports com.workmanager.workmanager;
}