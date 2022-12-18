package com.example.majorproject;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

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

    }


