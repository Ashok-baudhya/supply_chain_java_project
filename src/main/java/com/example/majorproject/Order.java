package com.example.majorproject;

public class Order {

    public static boolean placeOrder(String customerEmail,product product)
    {

        DatabaseConnection databaseConnection = new DatabaseConnection();
        String query = String.format("INSERT into orders(customer_id,product_id) VALUES( (select customer_id fROM customer WHERE email = '%s'),%s)", customerEmail,product.getId());
        int rowCount = 0;
        try{

            rowCount = databaseConnection.executeUpdateQuery(query);

        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return rowCount!=0;
    }

}
