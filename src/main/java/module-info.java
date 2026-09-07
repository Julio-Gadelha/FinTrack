module dio.fintrack {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens dio.fintrack to javafx.fxml;
    opens dio.fintrack.controller to javafx.fxml;

   exports dio.fintrack;
   exports dio.fintrack.model;
  exports dio.fintrack.repository;
//    exports dio.fintrack.dao;
   exports dio.fintrack.db;
    exports dio.fintrack.controller;
}