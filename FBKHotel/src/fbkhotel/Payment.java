/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbkhotel;

import java.sql.*;

/**
 *
 * @author Rafaelle, Kelly, Bello
 */
public class Payment {

    final String DB_URL = "jdbc:derby://localhost:1527//FinalReservationDB;create=true";
    private String sql;
    private int reservationnumber;
    private String paymentmethod;
    private String cardtype;
    private String cardfirst;
    private String cardlast;
    private String cardnum;
    private String cardexpiry;
    private String cardCVV;
    private String cardaddress;

    //constructor
    public Payment(int resnum,
            String method,
            String type,
            String first,
            String last,
            String num,
            String expiry,
            String CVV,
            String add) {

        reservationnumber = resnum;
        paymentmethod = method;
        cardtype = type;
        cardfirst = first;
        cardlast = last;
        cardnum = num;
        cardexpiry = expiry;
        cardCVV = CVV;
        cardaddress = add;
    }

    public void sendPaymentDB() {
        try {
            // Create a connection to the database.
            Connection conn
                    = DriverManager.getConnection(DB_URL);

            // Create a Statement object.
            Statement stmt = conn.createStatement();

            // Update table
            sql = "UPDATE ReservationDB SET PaymentMethod = '" + paymentmethod + "', CreditType = '" + cardtype + "', CreditFirstName = '" + cardfirst + "', CreditLastName = '" + cardlast + "', CreditNumber = '" + cardnum + "', CreditExpiry = '" + cardexpiry + "', CreditCVV = '" + cardCVV + "', CreditAddress = '" + cardaddress + "' WHERE ReservationNumber = " + reservationnumber;
            //System.out.println("Payment: " + sql);//for debugging purposes
            stmt.executeUpdate(sql);

            // Close the resources.
            stmt.close();
            conn.close();
            System.out.println("Payment Info sent to DB");
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    //get ID method
    public String getPaymentMethod() {
        return paymentmethod;
    }

    //set ID method
    public void setPaymentMethod(String method) {
        paymentmethod = method;
    }

    public String getCardType() {
        return cardtype;
    }

    public void setCardType(String type) {
        cardtype = type;
    }

    public String getCardFirst() {
        return cardfirst;
    }

    public void setCardFirst(String first) {
        cardfirst = first;
    }

    public String getCardLast() {
        return cardlast;
    }

    public void setCardLast(String last) {
        cardlast = last;
    }

    public String getCardNum() {
        return cardnum;
    }

    public void setCardNum(String num) {
        cardnum = num;
    }

    public String getCardExpiry() {
        return cardexpiry;
    }

    public void setCardExpiry(String expiry) {
        cardexpiry = expiry;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(String cvv) {
        cardCVV = cvv;
    }

    public String getCardAddress() {
        return cardaddress;
    }

    public void setCardAddress(String add) {
        cardaddress = add;
    }
}
