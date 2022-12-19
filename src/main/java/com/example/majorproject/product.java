package com.example.majorproject;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class product {

        private SimpleIntegerProperty id;
        private SimpleStringProperty name;
        private SimpleDoubleProperty price;


        public int getId()
        {
           return id.get();
        }

        public String getName()
        {
            return name.get();
        }

        public double getPrice()
        {
            return price.get();
        }

        public product(int id,String name, double price)
        {
            this.id = new SimpleIntegerProperty(id);
            this.name = new SimpleStringProperty(name);
            this.price = new SimpleDoubleProperty(price);
        }

        public static ObservableList<product> getAllProduct()
        {
            DatabaseConnection databaseConnection  = new DatabaseConnection();
            ObservableList<product> productList = FXCollections.observableArrayList();
            String selectProduct = "SELECT * FROM product";
            try{
                ResultSet rs = databaseConnection.getQueryTable(selectProduct);
                while(rs.next())
                {
                    productList.add(new product(rs.getInt("product_id"),
                            rs.getString("name"),rs.getDouble("price")
                    ));
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return productList;
        }



    public static ObservableList<product> getAllProductByName(String productName)
    {
        DatabaseConnection databaseConnection  = new DatabaseConnection();
        ObservableList<product> productList = FXCollections.observableArrayList();
        String selectProduct = String.format("SELECT * FROM product where lower(name) like '%%%s%%'",productName.toLowerCase());
        try{
            ResultSet rs = databaseConnection.getQueryTable(selectProduct);
            while(rs.next())
            {
                productList.add(new product(rs.getInt("product_id"),
                        rs.getString("name"),rs.getDouble("price")
                ));
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return productList;
    }


    }



