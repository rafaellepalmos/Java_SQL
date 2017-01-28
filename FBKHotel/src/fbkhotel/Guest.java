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
public class Guest {

    final String DB_URL = "jdbc:derby://localhost:1527//FinalReservationDB;create=true";
    private String sql;
    private int reservationnumber;
    private String firstname;
    private String lastname;
    private String id;
    private String phone;
    private String email;

    //constructor
    public Guest(int resnum,
            String guestFirstName,
            String guestLastName,
            String guestID,
            String guestPhone,
            String guestEmail) {

        reservationnumber = resnum;
        firstname = guestFirstName;
        lastname = guestLastName;
        id = guestID;
        phone = guestPhone;
        email = guestEmail;

    }

    public void SendGuestDB() {
        try {
            // Create a connection to the database.
            Connection conn
                    = DriverManager.getConnection(DB_URL);

            // Create a Statement object.
            Statement stmt = conn.createStatement();

            // Update table
            sql = "UPDATE ReservationDB SET FirstName = '" + firstname + "', LastName = '" + lastname + "', ID = '" + id + "', Phone = '" + phone + "', Email = '" + email + "' WHERE ReservationNumber = " + reservationnumber;
            //System.out.println("Guest: " + sql);//for debugging purposes
            stmt.executeUpdate(sql);

            // Close the resources.
            stmt.close();
            conn.close();
            System.out.println("Guest Info sent to DB");
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String guestFirstName) {
        firstname = guestFirstName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String guestLastName) {
        lastname = guestLastName;
    }

    //get ID method
    public String getID() {
        return id;
    }

    //set ID method
    public void setID(String guestID) {
        id = guestID;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String guestPhone) {
        phone = guestPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String guestEmail) {
        email = guestEmail;
    }
}
