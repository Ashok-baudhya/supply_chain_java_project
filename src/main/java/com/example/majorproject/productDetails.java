package com.example.majorproject;

import javafx.beans.Observable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class productDetails {

    public TableView<product> productTable;

    public Pane getAllProduct()
    {
        TableColumn id = new TableColumn("Id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name = new TableColumn("name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn price = new TableColumn("price");
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        ObservableList<product> data = FXCollections.observableArrayList();
        data.add(new product(1,"lenove",8439));
        data.add(new product(2,"hp",9789));

        productTable = new TableView<>();
        productTable.setItems(data);
        productTable.getColumns().addAll(id,name,price);

        Pane tablePane = new Pane();
        tablePane.getChildren().add(productTable);
        return tablePane;


    }

}
