package com.example.majorproject;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;

public class Login {

    private static byte[] getSHA(String input){
    try{
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        return messageDigest.digest(input.getBytes(StandardCharsets.UTF_8));
    } catch(Exception e){
        e.printStackTrace();
    }
    return null;
}

    private String getEncryptedPassword(String password)
    {
        String encryptedPassword ="";

        try{
            BigInteger number = new BigInteger(1,getSHA(password));
            StringBuilder hexString = new StringBuilder(number.toString(16));
            return hexString.toString();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }



    public boolean customerLogin(String email, String password)
    {
        String query = String.format("select * from customer where email = '%s' AND  password ='%s'",email,password);
        try {
            DatabaseConnection dbcon = new DatabaseConnection();
            ResultSet rs = dbcon.getQueryTable(query);

            if (rs.next()) {
                return true;
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }

//    public static void main(String[] args) {
//        Login login = new Login();
//
////        login.customerLogin("angad@gmail.com","abc123");
//        System.out.print()
//    }

    public static void main(String[] args) {
        Login login = new Login();
        System.out.println(login.getEncryptedPassword("1234"));
    }


}
