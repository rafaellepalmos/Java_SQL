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
public class Room {

    public Room() {
        String sql;
        final String DB_URL
                = "jdbc:derby://localhost:1527//FinalReservationDB;create=true";

        try {
            // Create a connection to the database.
            Connection conn
                    = DriverManager.getConnection(DB_URL);

            // Create a Statement object.
            Statement stmt = conn.createStatement();

            stmt.execute("DROP TABLE RoomData");

            // Create the Room Data table.
            System.out.println("Creating the Room Data table...");
            stmt.execute("CREATE TABLE RoomData ("
                    + "RoomNumber INTEGER NOT NULL PRIMARY KEY, "
                    + "RoomType VARCHAR(20), "
                    + "RoomCapacity INTEGER, "
                    + "RoomPrice INTEGER, "
                    + "RoomDesc VARCHAR(150), "
                    + "RoomAvailable BOOLEAN)");

            // Add Deluxe Rooms to table
            sql = "INSERT INTO RoomData VALUES"
                    + "(101, 'Deluxe Room', 2, 6000, 'Studio-type room with no amenities', true)";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO RoomData VALUES"
                    + "(201, 'Deluxe Room', 2, 6000, 'Studio-type room with no amenities', false)";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO RoomData VALUES"
                    + "(301, 'Deluxe Room', 2, 6000, 'Studio-type room with no amenities', false)";
            stmt.executeUpdate(sql);

            //Add Superior Rooms to table
            sql = "INSERT INTO RoomData VALUES"
                    + "(102, 'Superior Room', 2, 7000, 'Studio-type room with amenities', false)";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO RoomData VALUES"
                    + "(202, 'Superior Room', 2, 7000, 'Studio-type room with amenities', false)";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO RoomData VALUES"
                    + "(302, 'Superior Room', 2, 7000, 'Studio-type room with amenities', false)";
            stmt.executeUpdate(sql);

            //Add Family Rooms to table
            sql = "INSERT INTO RoomData VALUES"
                    + "(103, 'Family Room', 4, 8000, '2-BR, 1-Bath, 1 common area', false)";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO RoomData VALUES"
                    + "(203, 'Family Room', 4, 8000, '2-BR, 1-Bath, 1 common area', false)";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO RoomData VALUES"
                    + "(303, 'Family Room', 4, 8000, '2-BR, 1-Bath, 1 common area', false)";
            stmt.executeUpdate(sql);

            //Add Theme Suites to table
            sql = "INSERT INTO RoomData VALUES"
                    + "(104, 'Theme Suite', 6, 10000, 'choice of Cottage, Marble, Stone themes', false)";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO RoomData VALUES"
                    + "(204, 'Theme Suite', 6, 10000, 'choice of Cottage, Marble, Stone themes', false)";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO RoomData VALUES"
                    + "(304, 'Theme Suite', 6, 10000, 'choice of Cottage, Marble, Stone themes', false)";
            stmt.executeUpdate(sql);

            //Add VIP Suites to table
            sql = "INSERT INTO RoomData VALUES"
                    + "(401, 'VIP Suite', 8, 12000, '2-floor suite with all the functions of a house', true)";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO RoomData VALUES"
                    + "(402, 'VIP Suite', 8, 12000, '2-floor suite with all the functions of a house', false)";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO RoomData VALUES"
                    + "(403, 'VIP Suite', 8, 12000, '2-floor suite with all the functions of a house', false)";
            stmt.executeUpdate(sql);

            //Add Penthouse Suites to table
            sql = "INSERT INTO RoomData VALUES"
                    + "(501, 'Penthouse Suite', 8, 15000, '2-floor suite with all the functions of a house located at the top-most floor of the building with panoramic floor-to-ceiling glass windows.', false)";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO RoomData VALUES"
                    + "(502, 'Penthouse Suite', 8, 15000, '2-floor suite with all the functions of a house located at the top-most floor of the building with panoramic floor-to-ceiling glass windows.', false)";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO RoomData VALUES"
                    + "(503, 'Penthouse Suite', 8, 15000, '2-floor suite with all the functions of a house located at the top-most floor of the building with panoramic floor-to-ceiling glass windows.', false)";
            stmt.executeUpdate(sql);

            // Close the resources.
            stmt.close();
            conn.close();
            System.out.println("Room Data Done");
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

}
