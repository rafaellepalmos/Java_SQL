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
public class Reservation {

    final String DB_URL = "jdbc:derby://localhost:1527//FinalReservationDB;create=true";
    private String sql;
    private int reservationnumber;
    private String dayIn;
    private String monthIn;
    private String yearIn;
    private String dayOut;
    private String monthOut;
    private String yearOut;
    private String checkin;
    private String checkout;
    private int numGuest;
    private int roomnumber;
    private int totalbill;

    public Reservation(int resnum,
            String checkInDay,
            String checkInMonth,
            String checkInYear,
            String checkOutDay,
            String checkOutMonth,
            String checkOutYear,
            String in,
            String out,
            int guestNum,
            int roomnum,
            int total) {

        reservationnumber = resnum;
        dayIn = checkInDay;
        monthIn = checkInMonth;
        yearIn = checkInYear;
        dayOut = checkOutDay;
        monthOut = checkOutMonth;
        yearOut = checkOutYear;
        checkin = in;
        checkout = out;
        numGuest = guestNum;
        roomnumber = roomnum;
        totalbill = total;
    }

    public void sendReservationDB() {

        try {
            // Create a connection to the database.
            Connection conn
                    = DriverManager.getConnection(DB_URL);

            // Create a Statement object.
            Statement stmt = conn.createStatement();

            java.sql.Date datein = java.sql.Date.valueOf(checkin);
            //System.out.println("datein " +datein);//for debugging purposes
            java.sql.Date dateout = java.sql.Date.valueOf(checkout);
            //System.out.println("dateout " +dateout);//for debugging purposes
            PreparedStatement pstmt = conn.prepareStatement("UPDATE ReservationDB SET CheckIn = ?, CheckOut = ? WHERE ReservationNumber = " + reservationnumber);
            pstmt.setDate(1, datein);
            pstmt.setDate(2, dateout);
            pstmt.executeUpdate();

            // Update reservation table
            sql = "UPDATE ReservationDB SET GuestNumber = " + numGuest + ", RoomNumber = " + roomnumber + ", TotalBill = " + totalbill + "WHERE ReservationNumber = " + reservationnumber;
            //System.out.println("Reservation: "+sql);//for debugging purposes
            stmt.executeUpdate(sql);

            // Close the resources.
            stmt.close();
            conn.close();
            System.out.println("Reservation Info sent to DB");
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public String getCheckInDay() {
        return dayIn;
    }

    public void setCheckInDay(String checkInDay) {
        dayIn = checkInDay;
    }

    public String getCheckInMonth() {
        return monthIn;
    }

    public void setCheckInMonth(String checkInMonth) {
        monthIn = checkInMonth;
    }

    public String getCheckInYear() {
        return yearIn;
    }

    public void setCheckInYear(String checkInYear) {
        yearIn = checkInYear;
    }

    public String getCheckOutDay() {
        return dayOut;
    }

    public void setCheckOutDay(String checkOutDay) {
        dayOut = checkOutDay;
    }

    public String getCheckOutMonth() {
        return monthOut;
    }

    public void setCheckOutMonth(String checkOutMonth) {
        monthOut = checkOutMonth;
    }

    public String getCheckOutYear() {
        return yearOut;
    }

    public void setCheckOutYear(String checkOutYear) {
        yearOut = checkOutYear;
    }

    public String getCheckIn() {
        return checkin;
    }

    public void setCheckIn(String in) {
        checkin = in;
    }

    public String getCheckOut() {
        return checkout;
    }

    public void setCheckOut(String out) {
        checkout = out;
    }

    public int getNumGuest() {
        return numGuest;
    }

    public void setNumGuest(int guestNum) {
        numGuest = guestNum;
    }

    public int getRoomNumber() {
        return roomnumber;
    }

    public void setRoomNumber(int roomnum) {
        roomnumber = roomnum;
    }

    public int getTotalBill() {
        return totalbill;
    }

    public void setTotalBill(int total) {
        totalbill = total;
    }
}
