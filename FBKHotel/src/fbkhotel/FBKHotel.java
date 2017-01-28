/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbkhotel;

/**
 *
 * @author Rafaelle, Kelly, Bello
 */
public class FBKHotel {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        Room roomdb = new Room();
        System.out.println(roomdb);
        ReservationGUI reservation = new ReservationGUI();
        reservation.setVisible(true);        
    }
    
}
