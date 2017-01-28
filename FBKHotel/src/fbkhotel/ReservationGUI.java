/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fbkhotel;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Rafaelle, Kelly, Bello
 */
public class ReservationGUI extends javax.swing.JFrame {

    /**
     * Creates new form ReservationGUI
     */
    //combo box choices for dates
    final DefaultComboBoxModel longmonth = new DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"});
    final DefaultComboBoxModel shortmonth = new DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"});
    final DefaultComboBoxModel febmonth = new DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"});
    final DefaultComboBoxModel leapmonth = new DefaultComboBoxModel(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29"});

    //combo box choices for room types
    final DefaultComboBoxModel two = new DefaultComboBoxModel(new String[]{"Deluxe Room", "Superior Room", "Family Room", "Theme Suite", "VIP Suite", "Penthouse Suite"});
    final DefaultComboBoxModel four = new DefaultComboBoxModel(new String[]{"Family Room", "Theme Suite", "VIP Suite", "Penthouse Suite"});
    final DefaultComboBoxModel six = new DefaultComboBoxModel(new String[]{"Theme Suite", "VIP Suite", "Penthouse Suite"});
    final DefaultComboBoxModel eight = new DefaultComboBoxModel(new String[]{"VIP Suite", "Penthouse Suite"});
    String chkinday;
    String chkinmonth;
    String chkinyear;
    String chkoutday;
    String chkoutmonth;
    String chkoutyear;
    String chkin;
    String chkout;
    double days = 0;
    int guests;
    int reservationNum = 1;
    int roomnum = 0;
    int roomprice = 6000;
    int bedprice = 500;
    int breakfastprice = 250;
    int totalprice = 6000;
    String sql;
    final String DB_URL = "jdbc:derby://localhost:1527//FinalReservationDB;create=true";
    String roomavailtext;
    String firstname;
    String lastname;
    String phone;
    String email;
    String id;
    String paymentmethod;
    String cardtype;
    String cardfirst;
    String cardlast;
    String cardnum;
    String cardmonth;
    String cardyear;
    String cardexpiry;
    String cardCVV;
    String cardaddress;
    int query = 0;
    String[] roompic = {"deluxeroom.png", "superiorroom.png", "familyroom.png", "themesuite.png", "vipsuite.png", "penthousesuite.png"};
    Icon[] roomicon = {
        new ImageIcon(getClass().getResource(roompic[0])),
        new ImageIcon(getClass().getResource(roompic[1])),
        new ImageIcon(getClass().getResource(roompic[2])),
        new ImageIcon(getClass().getResource(roompic[3])),
        new ImageIcon(getClass().getResource(roompic[4])),
        new ImageIcon(getClass().getResource(roompic[5])),};

    public ReservationGUI() {
        initComponents();
        pnlWelcome.setVisible(true);
        pnlRoomCheck.setVisible(false);
        pnlRoomList.setVisible(false);
        pnlCustomerInfo.setVisible(false);
        pnlPaymentInfo.setVisible(false);
        pnlDone.setVisible(false);
        pnlCheckDB.setVisible(false);
        lblRoomInfo.setIcon(roomicon[0]);

        try {
            // Create a connection to the database.
            Connection conn
                    = DriverManager.getConnection(DB_URL);

            // Create a Statement object.
            Statement stmt = conn.createStatement();

            stmt.execute("DROP TABLE ReservationDB");

            // Create the Room Data table.
            System.out.println("Creating the ReservationDB table...");
            stmt.execute("CREATE TABLE ReservationDB ("
                    + "ReservationNumber INTEGER NOT NULL PRIMARY KEY, "
                    + "FirstName VARCHAR(20), "
                    + "LastName VARCHAR(20), "
                    + "ID VARCHAR(10), "
                    + "Phone VARCHAR(15), "
                    + "Email VARCHAR(20), "
                    + "CheckIn DATE, "
                    + "CheckOut DATE, "
                    + "GuestNumber INTEGER, "
                    + "RoomNumber INTEGER, "
                    + "TotalBill INTEGER, "
                    + "PaymentMethod VARCHAR(20), "
                    + "CreditType VARCHAR(20), "
                    + "CreditFirstName VARCHAR(20), "
                    + "CreditLastName VARCHAR(20), "
                    + "CreditNumber VARCHAR(20), "
                    + "CreditExpiry VARCHAR(20), "
                    + "CreditCVV VARCHAR(5), "
                    + "CreditAddress VARCHAR(150))");

            //Makes table RoomData's primary key as table ReservationDB's foreign key
            //stmt.execute("ALTER TABLE ReservationDB ADD FOREIGN KEY (RoomNumber) REFERENCES RoomData (RoomNumber)");
            // Close the resources.
            stmt.close();
            conn.close();
            System.out.println("ReservationDB Done");
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpPaymentMethod = new javax.swing.ButtonGroup();
        pnlWelcome = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        btnStartBooking = new javax.swing.JButton();
        bgWelcome = new javax.swing.JLabel();
        pnlRoomCheck = new javax.swing.JPanel();
        lblCheckIn = new javax.swing.JLabel();
        lblCheckInDay = new javax.swing.JLabel();
        lblCheckInMonth = new javax.swing.JLabel();
        lblCheckInYear = new javax.swing.JLabel();
        lblCheckOutDay = new javax.swing.JLabel();
        lblCheckOutMonth = new javax.swing.JLabel();
        cmbCheckInDay = new javax.swing.JComboBox();
        cmbCheckInMonth = new javax.swing.JComboBox();
        cmbCheckInYear = new javax.swing.JComboBox();
        cmbCheckOutDay = new javax.swing.JComboBox();
        cmbCheckOutMonth = new javax.swing.JComboBox();
        lblCheckOutYear = new javax.swing.JLabel();
        cmbCheckOutYear = new javax.swing.JComboBox();
        lblNumGuest = new javax.swing.JLabel();
        cmbNumGuest = new javax.swing.JComboBox();
        btnRoomCheck = new javax.swing.JButton();
        lblCheckOut = new javax.swing.JLabel();
        bgRoomCheck = new javax.swing.JLabel();
        pnlRoomList = new javax.swing.JPanel();
        lblRoomType = new javax.swing.JLabel();
        cmbRoomType = new javax.swing.JComboBox();
        cbxExtraBed = new javax.swing.JCheckBox();
        cbxBreakfast = new javax.swing.JCheckBox();
        btnBookNow = new javax.swing.JButton();
        txfTotal = new javax.swing.JTextField();
        lblTotal = new javax.swing.JLabel();
        btnBack1 = new javax.swing.JButton();
        lblRoomInfo = new javax.swing.JLabel();
        bgRoomList = new javax.swing.JLabel();
        pnlCustomerInfo = new javax.swing.JPanel();
        lblFirstName = new javax.swing.JLabel();
        txfFirstName = new javax.swing.JTextField();
        lblLastName = new javax.swing.JLabel();
        txfLastName = new javax.swing.JTextField();
        lblPhone = new javax.swing.JLabel();
        txfPhone = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        txfEmail = new javax.swing.JTextField();
        lblID = new javax.swing.JLabel();
        txfID = new javax.swing.JTextField();
        btnNext = new javax.swing.JButton();
        btnBack2 = new javax.swing.JButton();
        lblCusInfo = new javax.swing.JLabel();
        bgCustomerInfo = new javax.swing.JLabel();
        pnlPaymentInfo = new javax.swing.JPanel();
        radCash = new javax.swing.JRadioButton();
        radCredit = new javax.swing.JRadioButton();
        sepPayment = new javax.swing.JSeparator();
        lblCardFirst = new javax.swing.JLabel();
        txfCardFirst = new javax.swing.JTextField();
        lblCardLast = new javax.swing.JLabel();
        txfCardLast = new javax.swing.JTextField();
        lblCardNum = new javax.swing.JLabel();
        txfCardNum = new javax.swing.JTextField();
        lblExpiry = new javax.swing.JLabel();
        cmbExpiryMonth = new javax.swing.JComboBox();
        cmbExpiryYear = new javax.swing.JComboBox();
        lblCVV = new javax.swing.JLabel();
        txfCardCVV = new javax.swing.JTextField();
        lblAddress = new javax.swing.JLabel();
        txfCardAddress = new javax.swing.JTextField();
        btnBack3 = new javax.swing.JButton();
        btnSubmit = new javax.swing.JButton();
        cmbPayment = new javax.swing.JComboBox();
        lblPayInfo = new javax.swing.JLabel();
        bgPaymentInfo = new javax.swing.JLabel();
        pnlDone = new javax.swing.JPanel();
        lblThankYou = new javax.swing.JLabel();
        btnDone = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnCheckDB = new javax.swing.JButton();
        bgDone = new javax.swing.JLabel();
        pnlCheckDB = new javax.swing.JPanel();
        scrDisplay = new javax.swing.JScrollPane();
        txaDisplay = new javax.swing.JTextArea();
        lblQuery = new javax.swing.JLabel();
        txfQuery = new javax.swing.JTextField();
        btnQuery = new javax.swing.JButton();
        btnBack4 = new javax.swing.JButton();
        lblCheckDB = new javax.swing.JLabel();
        bgCheckDB = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pnlWelcome.setOpaque(false);
        pnlWelcome.setPreferredSize(new java.awt.Dimension(500, 350));
        pnlWelcome.setLayout(null);

        lblTitle.setBackground(new java.awt.Color(102, 102, 102));
        lblTitle.setFont(new java.awt.Font("French Script MT", 1, 48)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("FBK Hotel");
        pnlWelcome.add(lblTitle);
        lblTitle.setBounds(50, 30, 400, 90);

        btnStartBooking.setText("Start Booking");
        btnStartBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartBookingActionPerformed(evt);
            }
        });
        pnlWelcome.add(btnStartBooking);
        btnStartBooking.setBounds(337, 283, 120, 30);

        bgWelcome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fbkhotel/rooftop2.jpg"))); // NOI18N
        bgWelcome.setMaximumSize(new java.awt.Dimension(500, 350));
        bgWelcome.setMinimumSize(new java.awt.Dimension(500, 350));
        bgWelcome.setPreferredSize(new java.awt.Dimension(500, 350));
        pnlWelcome.add(bgWelcome);
        bgWelcome.setBounds(0, 0, 500, 350);

        pnlRoomCheck.setPreferredSize(new java.awt.Dimension(500, 350));
        pnlRoomCheck.setLayout(null);

        lblCheckIn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCheckIn.setText("Check In");
        pnlRoomCheck.add(lblCheckIn);
        lblCheckIn.setBounds(10, 86, 70, 17);

        lblCheckInDay.setText("Day");
        pnlRoomCheck.add(lblCheckInDay);
        lblCheckInDay.setBounds(10, 121, 30, 20);

        lblCheckInMonth.setText("Month");
        pnlRoomCheck.add(lblCheckInMonth);
        lblCheckInMonth.setBounds(130, 120, 50, 20);

        lblCheckInYear.setText("Year");
        pnlRoomCheck.add(lblCheckInYear);
        lblCheckInYear.setBounds(320, 120, 40, 20);

        lblCheckOutDay.setText("Day");
        pnlRoomCheck.add(lblCheckOutDay);
        lblCheckOutDay.setBounds(10, 200, 30, 20);

        lblCheckOutMonth.setText("Month");
        pnlRoomCheck.add(lblCheckOutMonth);
        lblCheckOutMonth.setBounds(130, 200, 50, 20);

        cmbCheckInDay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        pnlRoomCheck.add(cmbCheckInDay);
        cmbCheckInDay.setBounds(50, 120, 50, 20);

        cmbCheckInMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        cmbCheckInMonth.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCheckInMonthItemStateChanged(evt);
            }
        });
        pnlRoomCheck.add(cmbCheckInMonth);
        cmbCheckInMonth.setBounds(190, 120, 110, 20);

        cmbCheckInYear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2015", "2016", "2017" }));
        cmbCheckInYear.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCheckInYearItemStateChanged(evt);
            }
        });
        pnlRoomCheck.add(cmbCheckInYear);
        cmbCheckInYear.setBounds(380, 120, 70, 20);

        cmbCheckOutDay.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        pnlRoomCheck.add(cmbCheckOutDay);
        cmbCheckOutDay.setBounds(50, 200, 50, 20);

        cmbCheckOutMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        cmbCheckOutMonth.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCheckOutMonthItemStateChanged(evt);
            }
        });
        pnlRoomCheck.add(cmbCheckOutMonth);
        cmbCheckOutMonth.setBounds(190, 200, 110, 20);

        lblCheckOutYear.setText("Year");
        pnlRoomCheck.add(lblCheckOutYear);
        lblCheckOutYear.setBounds(320, 200, 40, 20);

        cmbCheckOutYear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2015", "2016", "2017" }));
        cmbCheckOutYear.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCheckOutYearItemStateChanged(evt);
            }
        });
        pnlRoomCheck.add(cmbCheckOutYear);
        cmbCheckOutYear.setBounds(380, 200, 70, 20);

        lblNumGuest.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNumGuest.setText("Number of Guest(s):");
        pnlRoomCheck.add(lblNumGuest);
        lblNumGuest.setBounds(10, 250, 150, 17);

        cmbNumGuest.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }));
        pnlRoomCheck.add(cmbNumGuest);
        cmbNumGuest.setBounds(160, 250, 40, 20);

        btnRoomCheck.setText("Check");
        btnRoomCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRoomCheckActionPerformed(evt);
            }
        });
        pnlRoomCheck.add(btnRoomCheck);
        btnRoomCheck.setBounds(390, 305, 90, 23);

        lblCheckOut.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCheckOut.setText("Check Out");
        pnlRoomCheck.add(lblCheckOut);
        lblCheckOut.setBounds(10, 160, 80, 17);

        bgRoomCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fbkhotel/fbkhotel.png"))); // NOI18N
        pnlRoomCheck.add(bgRoomCheck);
        bgRoomCheck.setBounds(0, 0, 500, 350);

        pnlRoomList.setPreferredSize(new java.awt.Dimension(500, 350));
        pnlRoomList.setLayout(null);

        lblRoomType.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblRoomType.setText("Room");
        pnlRoomList.add(lblRoomType);
        lblRoomType.setBounds(20, 190, 50, 17);

        cmbRoomType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Deluxe Room", "Superior Room", "Family Room", "Theme Suite", "VIP Suite", "Penthouse Suite" }));
        cmbRoomType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbRoomTypeItemStateChanged(evt);
            }
        });
        pnlRoomList.add(cmbRoomType);
        cmbRoomType.setBounds(80, 190, 120, 20);

        cbxExtraBed.setText("Extra Bed");
        cbxExtraBed.setOpaque(false);
        cbxExtraBed.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxExtraBedItemStateChanged(evt);
            }
        });
        pnlRoomList.add(cbxExtraBed);
        cbxExtraBed.setBounds(210, 190, 100, 23);

        cbxBreakfast.setText("Breakfast Included");
        cbxBreakfast.setOpaque(false);
        cbxBreakfast.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxBreakfastItemStateChanged(evt);
            }
        });
        pnlRoomList.add(cbxBreakfast);
        cbxBreakfast.setBounds(325, 190, 150, 23);

        btnBookNow.setText("Book Now");
        btnBookNow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBookNowActionPerformed(evt);
            }
        });
        pnlRoomList.add(btnBookNow);
        btnBookNow.setBounds(390, 316, 100, 23);
        pnlRoomList.add(txfTotal);
        txfTotal.setBounds(70, 240, 410, 30);

        lblTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTotal.setText("Total");
        pnlRoomList.add(lblTotal);
        lblTotal.setBounds(20, 240, 35, 17);

        btnBack1.setText("Back");
        btnBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack1ActionPerformed(evt);
            }
        });
        pnlRoomList.add(btnBack1);
        btnBack1.setBounds(10, 316, 70, 23);
        pnlRoomList.add(lblRoomInfo);
        lblRoomInfo.setBounds(20, 70, 460, 100);

        bgRoomList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fbkhotel/fbkhotel.png"))); // NOI18N
        pnlRoomList.add(bgRoomList);
        bgRoomList.setBounds(0, 0, 500, 350);

        pnlCustomerInfo.setPreferredSize(new java.awt.Dimension(500, 350));
        pnlCustomerInfo.setLayout(null);

        lblFirstName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblFirstName.setText("First Name");
        pnlCustomerInfo.add(lblFirstName);
        lblFirstName.setBounds(20, 100, 80, 17);
        pnlCustomerInfo.add(txfFirstName);
        txfFirstName.setBounds(20, 120, 140, 30);

        lblLastName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblLastName.setText("Last Name");
        pnlCustomerInfo.add(lblLastName);
        lblLastName.setBounds(210, 100, 80, 17);
        pnlCustomerInfo.add(txfLastName);
        txfLastName.setBounds(210, 120, 140, 30);

        lblPhone.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblPhone.setText("Phone Number");
        pnlCustomerInfo.add(lblPhone);
        lblPhone.setBounds(20, 160, 110, 17);
        pnlCustomerInfo.add(txfPhone);
        txfPhone.setBounds(180, 160, 220, 30);

        lblEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEmail.setText("Email Address");
        pnlCustomerInfo.add(lblEmail);
        lblEmail.setBounds(20, 200, 100, 17);
        pnlCustomerInfo.add(txfEmail);
        txfEmail.setBounds(180, 200, 220, 30);

        lblID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblID.setText("ID/Passport Number");
        pnlCustomerInfo.add(lblID);
        lblID.setBounds(20, 240, 150, 17);
        pnlCustomerInfo.add(txfID);
        txfID.setBounds(180, 240, 220, 30);

        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        pnlCustomerInfo.add(btnNext);
        btnNext.setBounds(405, 310, 70, 23);

        btnBack2.setText("Back");
        btnBack2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack2ActionPerformed(evt);
            }
        });
        pnlCustomerInfo.add(btnBack2);
        btnBack2.setBounds(20, 310, 70, 23);

        lblCusInfo.setFont(new java.awt.Font("Poor Richard", 1, 24)); // NOI18N
        lblCusInfo.setForeground(new java.awt.Color(153, 153, 153));
        lblCusInfo.setText("Customer Information");
        pnlCustomerInfo.add(lblCusInfo);
        lblCusInfo.setBounds(270, 20, 220, 28);

        bgCustomerInfo.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        bgCustomerInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fbkhotel/fbkhotel.png"))); // NOI18N
        pnlCustomerInfo.add(bgCustomerInfo);
        bgCustomerInfo.setBounds(0, 0, 500, 350);

        pnlPaymentInfo.setPreferredSize(new java.awt.Dimension(500, 350));
        pnlPaymentInfo.setLayout(null);

        grpPaymentMethod.add(radCash);
        radCash.setText("Cash");
        radCash.setOpaque(false);
        radCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radCashActionPerformed(evt);
            }
        });
        pnlPaymentInfo.add(radCash);
        radCash.setBounds(10, 83, 70, 23);

        grpPaymentMethod.add(radCredit);
        radCredit.setText("Credit Card");
        radCredit.setOpaque(false);
        radCredit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radCreditActionPerformed(evt);
            }
        });
        pnlPaymentInfo.add(radCredit);
        radCredit.setBounds(253, 83, 100, 23);
        pnlPaymentInfo.add(sepPayment);
        sepPayment.setBounds(10, 115, 480, 10);

        lblCardFirst.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCardFirst.setText("First Name");
        pnlPaymentInfo.add(lblCardFirst);
        lblCardFirst.setBounds(10, 140, 80, 17);
        pnlPaymentInfo.add(txfCardFirst);
        txfCardFirst.setBounds(90, 140, 150, 30);

        lblCardLast.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCardLast.setText("Last Name");
        pnlPaymentInfo.add(lblCardLast);
        lblCardLast.setBounds(250, 140, 73, 17);
        pnlPaymentInfo.add(txfCardLast);
        txfCardLast.setBounds(330, 140, 163, 30);

        lblCardNum.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCardNum.setText("Card Number");
        pnlPaymentInfo.add(lblCardNum);
        lblCardNum.setBounds(10, 184, 93, 17);
        pnlPaymentInfo.add(txfCardNum);
        txfCardNum.setBounds(120, 180, 260, 30);

        lblExpiry.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblExpiry.setText("Expiry Date");
        pnlPaymentInfo.add(lblExpiry);
        lblExpiry.setBounds(10, 222, 79, 17);

        cmbExpiryMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        pnlPaymentInfo.add(cmbExpiryMonth);
        cmbExpiryMonth.setBounds(120, 220, 90, 20);

        cmbExpiryYear.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2015", "2016", "2017" }));
        pnlPaymentInfo.add(cmbExpiryYear);
        cmbExpiryYear.setBounds(220, 220, 70, 20);

        lblCVV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCVV.setText("CVV");
        pnlPaymentInfo.add(lblCVV);
        lblCVV.setBounds(320, 220, 30, 17);
        pnlPaymentInfo.add(txfCardCVV);
        txfCardCVV.setBounds(370, 220, 80, 30);

        lblAddress.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAddress.setText("Address");
        pnlPaymentInfo.add(lblAddress);
        lblAddress.setBounds(10, 260, 56, 17);
        pnlPaymentInfo.add(txfCardAddress);
        txfCardAddress.setBounds(120, 260, 360, 30);

        btnBack3.setText("Back");
        btnBack3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack3ActionPerformed(evt);
            }
        });
        pnlPaymentInfo.add(btnBack3);
        btnBack3.setBounds(10, 320, 70, 23);

        btnSubmit.setText("Submit");
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });
        pnlPaymentInfo.add(btnSubmit);
        btnSubmit.setBounds(405, 320, 80, 23);

        cmbPayment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Visa", "Master Card", "American Express" }));
        pnlPaymentInfo.add(cmbPayment);
        cmbPayment.setBounds(371, 84, 110, 20);

        lblPayInfo.setFont(new java.awt.Font("Poor Richard", 1, 24)); // NOI18N
        lblPayInfo.setForeground(new java.awt.Color(153, 153, 153));
        lblPayInfo.setText("Payment Information");
        pnlPaymentInfo.add(lblPayInfo);
        lblPayInfo.setBounds(270, 10, 210, 50);

        bgPaymentInfo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bgPaymentInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fbkhotel/fbkhotel.png"))); // NOI18N
        pnlPaymentInfo.add(bgPaymentInfo);
        bgPaymentInfo.setBounds(0, 0, 500, 350);

        pnlDone.setPreferredSize(new java.awt.Dimension(500, 350));
        pnlDone.setLayout(null);

        lblThankYou.setBackground(new java.awt.Color(102, 102, 102));
        lblThankYou.setFont(new java.awt.Font("French Script MT", 1, 48)); // NOI18N
        lblThankYou.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblThankYou.setText("Thank You!");
        pnlDone.add(lblThankYou);
        lblThankYou.setBounds(140, 40, 214, 56);

        btnDone.setText("Done");
        btnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoneActionPerformed(evt);
            }
        });
        pnlDone.add(btnDone);
        btnDone.setBounds(90, 260, 300, 23);

        btnNew.setText("Make another reservation");
        btnNew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        pnlDone.add(btnNew);
        btnNew.setBounds(90, 210, 300, 23);

        btnCheckDB.setText("Check reservation");
        btnCheckDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckDBActionPerformed(evt);
            }
        });
        pnlDone.add(btnCheckDB);
        btnCheckDB.setBounds(90, 160, 300, 23);

        bgDone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fbkhotel/rooftop2.jpg"))); // NOI18N
        pnlDone.add(bgDone);
        bgDone.setBounds(0, 0, 500, 350);

        pnlCheckDB.setMinimumSize(new java.awt.Dimension(500, 350));
        pnlCheckDB.setLayout(null);

        txaDisplay.setColumns(20);
        txaDisplay.setRows(5);
        scrDisplay.setViewportView(txaDisplay);

        pnlCheckDB.add(scrDisplay);
        scrDisplay.setBounds(10, 134, 480, 176);

        lblQuery.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblQuery.setText("Reservation Number");
        pnlCheckDB.add(lblQuery);
        lblQuery.setBounds(30, 90, 160, 20);
        pnlCheckDB.add(txfQuery);
        txfQuery.setBounds(210, 90, 70, 30);

        btnQuery.setText("Check");
        btnQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQueryActionPerformed(evt);
            }
        });
        pnlCheckDB.add(btnQuery);
        btnQuery.setBounds(391, 100, 80, 23);

        btnBack4.setText("Back");
        btnBack4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack4ActionPerformed(evt);
            }
        });
        pnlCheckDB.add(btnBack4);
        btnBack4.setBounds(10, 316, 70, 23);

        lblCheckDB.setFont(new java.awt.Font("Poor Richard", 1, 24)); // NOI18N
        lblCheckDB.setForeground(new java.awt.Color(153, 153, 153));
        lblCheckDB.setText("Check Reservation");
        pnlCheckDB.add(lblCheckDB);
        lblCheckDB.setBounds(280, 8, 190, 50);

        bgCheckDB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fbkhotel/fbkhotel.png"))); // NOI18N
        pnlCheckDB.add(bgCheckDB);
        bgCheckDB.setBounds(0, 0, 500, 350);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlRoomCheck, 490, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlCustomerInfo, 490, 490, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlRoomList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlPaymentInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlDone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlCheckDB, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlRoomCheck, 339, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlCustomerInfo, 339, 339, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlRoomList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlPaymentInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlDone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pnlCheckDB, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pnlRoomList.getAccessibleContext().setAccessibleName("");
        pnlCustomerInfo.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartBookingActionPerformed
        // TODO add your handling code here:
        pnlWelcome.setVisible(false);
        pnlRoomCheck.setVisible(true);
        pnlRoomList.setVisible(false);
        pnlCustomerInfo.setVisible(false);
        pnlPaymentInfo.setVisible(false);
        pnlDone.setVisible(false);
        pnlCheckDB.setVisible(false);
    }//GEN-LAST:event_btnStartBookingActionPerformed

    private void btnRoomCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRoomCheckActionPerformed
        // TODO add your handling code here:
        pnlWelcome.setVisible(false);
        pnlRoomCheck.setVisible(false);
        pnlRoomList.setVisible(true);
        pnlCustomerInfo.setVisible(false);
        pnlPaymentInfo.setVisible(false);
        pnlDone.setVisible(false);
        pnlCheckDB.setVisible(false);

        //gets selected item index from combo box
        //add 1 so day 1 at index 0 will save 1 instead of 0
        //converts integer to String in two-digit format
        chkinday = String.format("%02d", (cmbCheckInDay.getSelectedIndex() + 1));
        System.out.println("Check-In Day: " + chkinday);//printed for debugging purposes

        //gets selected item index from combo box
        //add 1 so January at index 0 will save 1 instead of 0
        //converts integer to String in two-digit format so 1 will save as 01
        chkinmonth = String.format("%02d", (cmbCheckInMonth.getSelectedIndex() + 1));
        System.out.println("Check-In Month: " + chkinmonth);

        //year is already in our desired format: year based on gregorian calendar and yyyy
        chkinyear = (String) cmbCheckInYear.getSelectedItem();
        System.out.println("Check-In Year: " + chkinyear);

        //gets selected item index from combo box
        //add 1 so day 1 at index 0 will save 1 instead of 0
        //converts integer to String in two-digit format
        chkoutday = String.format("%02d", (cmbCheckOutDay.getSelectedIndex() + 1));
        System.out.println("Check-Out Day: " + chkoutday);

        //gets selected item index from combo box
        //add 1 so January at index 0 will save 1 instead of 0
        //converts integer to String in two-digit format so 1 will save as 01
        chkoutmonth = String.format("%02d", (cmbCheckOutMonth.getSelectedIndex() + 1));
        System.out.println("Check-Out Month: " + chkoutmonth);

        //year is already in our desired format: year based on gregorian calendar and yyyy
        chkoutyear = (String) cmbCheckOutYear.getSelectedItem();
        System.out.println("Check-Out Year: " + chkoutyear);

        guests = cmbNumGuest.getSelectedIndex() + 1;
        System.out.println("Guests: " + guests);

        //database format
        chkin = chkinyear + "-" + chkinmonth + "-" + chkinday;
        System.out.println("Check-In: " + chkin);
        chkout = chkoutyear + "-" + chkoutmonth + "-" + chkoutday;
        System.out.println("Check-Out: " + chkout);

        //to count the number of days between check in and check out
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date indate = format.parse(chkin);//conversion
            java.util.Date outdate = format.parse(chkout);

            Calendar incalendar = Calendar.getInstance();
            incalendar.setTime(indate);
            Calendar outcalendar = Calendar.getInstance();
            outcalendar.setTime(outdate);
            
            days = (outcalendar.getTimeInMillis()-incalendar.getTimeInMillis())/(1000*60*60*24);
            //System.out.println("Number of days: "+days);
            
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (guests == 7 || guests == 8) {
            cmbRoomType.setModel(eight);//list of rooms with 8-person capacity
        } else if (guests == 5 || guests == 6) {
            cmbRoomType.setModel(six);//list of rooms with 6-person capacity
        } else if (guests == 3 || guests == 4) {
            cmbRoomType.setModel(four);//list of rooms with 4-person capacity
        } else if (guests == 1 || guests == 2) {
            cmbRoomType.setModel(two);//list of rooms with 2-person capacity
        } else {
            cmbRoomType.setModel(two);//default is to show all rooms available
        }
        txfTotal.setEditable(false);
    }//GEN-LAST:event_btnRoomCheckActionPerformed

    private void btnBookNowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookNowActionPerformed
        // TODO add your handling code here:

        String roomtype = (String) cmbRoomType.getSelectedItem();
        PreparedStatement pstmt = null;
        ResultSet result = null;

        try {
            // Create a connection to the database.
            Connection conn
                    = DriverManager.getConnection(DB_URL);

            // Create a Statement object.
            Statement stmt = conn.createStatement();

            // Create the Room Data table.
            System.out.println("Searching for " + roomtype + "s available...");
            java.sql.Date datein = java.sql.Date.valueOf(chkin);
            //System.out.println("datein " +datein);//for debugging purposes
            java.sql.Date dateout = java.sql.Date.valueOf(chkout);
            //System.out.println("dateout " +dateout);//for debugging purposes
            switch (roomtype) {
                //check for deluxe rooms availability
                case "Deluxe Room":
                    pstmt = conn.prepareStatement("SELECT * FROM ReservationDB "
                            + "WHERE RoomNumber = 101 "
                            + "AND CheckIn <= ? "
                            + "and CheckOut >= ?");
                    pstmt.setDate(1, dateout);
                    pstmt.setDate(2, datein);
                    result = pstmt.executeQuery();
                    if (result.next())//will search for results until result.next returns true
                    {
                        pstmt = conn.prepareStatement("SELECT * FROM ReservationDB "
                                + "WHERE RoomNumber = 201 "
                                + "AND CheckIn <= ? "
                                + "and CheckOut >= ?");
                        pstmt.setDate(1, dateout);
                        pstmt.setDate(2, datein);
                        result = pstmt.executeQuery();

                        if (result.next())//will search for results until result.next returns true
                        {
                            pstmt = conn.prepareStatement("SELECT * FROM ReservationDB "
                                    + "WHERE RoomNumber = 301 "
                                    + "AND CheckIn <= ? "
                                    + "and CheckOut >= ?");
                            pstmt.setDate(1, dateout);
                            pstmt.setDate(2, datein);
                            result = pstmt.executeQuery();

                            if (result.next())//will search for results until result.next returns true
                            {
                                JOptionPane.showMessageDialog(null, "All " + roomtype + "s are booked on those dates. Please choose a different room.", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                pnlWelcome.setVisible(false);
                                pnlRoomCheck.setVisible(false);
                                pnlRoomList.setVisible(false);
                                pnlCustomerInfo.setVisible(true);
                                pnlPaymentInfo.setVisible(false);
                                pnlDone.setVisible(false);
                                pnlCheckDB.setVisible(false);
                                roomnum = 301;
                            }//end else

                        } else {
                            pnlWelcome.setVisible(false);
                            pnlRoomCheck.setVisible(false);
                            pnlRoomList.setVisible(false);
                            pnlCustomerInfo.setVisible(true);
                            pnlPaymentInfo.setVisible(false);
                            pnlDone.setVisible(false);
                            pnlCheckDB.setVisible(false);
                            roomnum = 201;
                        }//end else

                    } else {
                        pnlWelcome.setVisible(false);
                        pnlRoomCheck.setVisible(false);
                        pnlRoomList.setVisible(false);
                        pnlCustomerInfo.setVisible(true);
                        pnlPaymentInfo.setVisible(false);
                        pnlDone.setVisible(false);
                        pnlCheckDB.setVisible(false);
                        roomnum = 101;
                    }//end else
                    break;

                //check for superior rooms availability
                case "Superior Room":
                    pstmt = conn.prepareStatement("SELECT * FROM ReservationDB "
                            + "WHERE RoomNumber = 102 "
                            + "AND CheckIn <= ? "
                            + "and CheckOut >= ?");
                    pstmt.setDate(1, dateout);
                    pstmt.setDate(2, datein);
                    result = pstmt.executeQuery();
                    if (result.next())//will search for results until result.next returns true
                    {
                        pstmt = conn.prepareStatement("SELECT * FROM ReservationDB "
                                + "WHERE RoomNumber = 202 "
                                + "AND CheckIn <= ? "
                                + "and CheckOut >= ?");
                        pstmt.setDate(1, dateout);
                        pstmt.setDate(2, datein);
                        result = pstmt.executeQuery();

                        if (result.next())//will search for results until result.next returns true
                        {
                            pstmt = conn.prepareStatement("SELECT * FROM ReservationDB "
                                    + "WHERE RoomNumber = 302 "
                                    + "AND CheckIn <= ? "
                                    + "and CheckOut >= ?");
                            pstmt.setDate(1, dateout);
                            pstmt.setDate(2, datein);
                            result = pstmt.executeQuery();

                            if (result.next())//will search for results until result.next returns true
                            {
                                JOptionPane.showMessageDialog(null, "All " + roomtype + "s are booked on those dates. Please choose a different room.", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                pnlWelcome.setVisible(false);
                                pnlRoomCheck.setVisible(false);
                                pnlRoomList.setVisible(false);
                                pnlCustomerInfo.setVisible(true);
                                pnlPaymentInfo.setVisible(false);
                                pnlDone.setVisible(false);
                                pnlCheckDB.setVisible(false);
                                roomnum = 302;
                            }//end else

                        } else {
                            pnlWelcome.setVisible(false);
                            pnlRoomCheck.setVisible(false);
                            pnlRoomList.setVisible(false);
                            pnlCustomerInfo.setVisible(true);
                            pnlPaymentInfo.setVisible(false);
                            pnlDone.setVisible(false);
                            pnlCheckDB.setVisible(false);
                            roomnum = 202;
                        }//end else

                    } else {
                        pnlWelcome.setVisible(false);
                        pnlRoomCheck.setVisible(false);
                        pnlRoomList.setVisible(false);
                        pnlCustomerInfo.setVisible(true);
                        pnlPaymentInfo.setVisible(false);
                        pnlDone.setVisible(false);
                        pnlCheckDB.setVisible(false);
                        roomnum = 102;
                    }//end else
                    break;

                //check for deluxe rooms availability
                case "Family Room":
                    pstmt = conn.prepareStatement("SELECT * FROM ReservationDB "
                            + "WHERE RoomNumber = 103 "
                            + "AND CheckIn <= ? "
                            + "and CheckOut >= ?");
                    pstmt.setDate(1, dateout);
                    pstmt.setDate(2, datein);
                    result = pstmt.executeQuery();
                    if (result.next())//will search for results until result.next returns true
                    {
                        pstmt = conn.prepareStatement("SELECT * FROM ReservationDB "
                                + "WHERE RoomNumber = 203 "
                                + "AND CheckIn <= ? "
                                + "and CheckOut >= ?");
                        pstmt.setDate(1, dateout);
                        pstmt.setDate(2, datein);
                        result = pstmt.executeQuery();

                        if (result.next())//will search for results until result.next returns true
                        {
                            pstmt = conn.prepareStatement("SELECT * FROM ReservationDB "
                                    + "WHERE RoomNumber = 303 "
                                    + "AND CheckIn <= ? "
                                    + "and CheckOut >= ?");
                            pstmt.setDate(1, dateout);
                            pstmt.setDate(2, datein);
                            result = pstmt.executeQuery();

                            if (result.next())//will search for results until result.next returns true
                            {
                                JOptionPane.showMessageDialog(null, "All " + roomtype + "s are booked on those dates. Please choose a different room.", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                pnlWelcome.setVisible(false);
                                pnlRoomCheck.setVisible(false);
                                pnlRoomList.setVisible(false);
                                pnlCustomerInfo.setVisible(true);
                                pnlPaymentInfo.setVisible(false);
                                pnlDone.setVisible(false);
                                pnlCheckDB.setVisible(false);
                                roomnum = 303;
                            }//end else

                        } else {
                            pnlWelcome.setVisible(false);
                            pnlRoomCheck.setVisible(false);
                            pnlRoomList.setVisible(false);
                            pnlCustomerInfo.setVisible(true);
                            pnlPaymentInfo.setVisible(false);
                            pnlDone.setVisible(false);
                            pnlCheckDB.setVisible(false);
                            roomnum = 203;
                        }//end else

                    } else {
                        pnlWelcome.setVisible(false);
                        pnlRoomCheck.setVisible(false);
                        pnlRoomList.setVisible(false);
                        pnlCustomerInfo.setVisible(true);
                        pnlPaymentInfo.setVisible(false);
                        pnlDone.setVisible(false);
                        pnlCheckDB.setVisible(false);
                        roomnum = 103;
                    }//end else
                    break;

                //check for deluxe rooms availability
                case "Theme Suite":
                    pstmt = conn.prepareStatement("SELECT * FROM ReservationDB "
                            + "WHERE RoomNumber = 104 "
                            + "AND CheckIn <= ? "
                            + "and CheckOut >= ?");
                    pstmt.setDate(1, dateout);
                    pstmt.setDate(2, datein);
                    result = pstmt.executeQuery();
                    if (result.next())//will search for results until result.next returns true
                    {
                        pstmt = conn.prepareStatement("SELECT * FROM ReservationDB "
                                + "WHERE RoomNumber = 204 "
                                + "AND CheckIn <= ? "
                                + "and CheckOut >= ?");
                        pstmt.setDate(1, dateout);
                        pstmt.setDate(2, datein);
                        result = pstmt.executeQuery();

                        if (result.next())//will search for results until result.next returns true
                        {
                            pstmt = conn.prepareStatement("SELECT * FROM ReservationDB "
                                    + "WHERE RoomNumber = 304 "
                                    + "AND CheckIn <= ? "
                                    + "and CheckOut >= ?");
                            pstmt.setDate(1, dateout);
                            pstmt.setDate(2, datein);
                            result = pstmt.executeQuery();

                            if (result.next())//will search for results until result.next returns true
                            {
                                JOptionPane.showMessageDialog(null, "All " + roomtype + "s are booked on those dates. Please choose a different room.", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                pnlWelcome.setVisible(false);
                                pnlRoomCheck.setVisible(false);
                                pnlRoomList.setVisible(false);
                                pnlCustomerInfo.setVisible(true);
                                pnlPaymentInfo.setVisible(false);
                                pnlDone.setVisible(false);
                                pnlCheckDB.setVisible(false);
                                roomnum = 304;
                            }//end else

                        } else {
                            pnlWelcome.setVisible(false);
                            pnlRoomCheck.setVisible(false);
                            pnlRoomList.setVisible(false);
                            pnlCustomerInfo.setVisible(true);
                            pnlPaymentInfo.setVisible(false);
                            pnlDone.setVisible(false);
                            pnlCheckDB.setVisible(false);
                            roomnum = 204;
                        }//end else

                    } else {
                        pnlWelcome.setVisible(false);
                        pnlRoomCheck.setVisible(false);
                        pnlRoomList.setVisible(false);
                        pnlCustomerInfo.setVisible(true);
                        pnlPaymentInfo.setVisible(false);
                        pnlDone.setVisible(false);
                        pnlCheckDB.setVisible(false);
                        roomnum = 104;
                    }//end else
                    break;

                //check for deluxe rooms availability
                case "VIP Suite":
                    pstmt = conn.prepareStatement("SELECT * FROM ReservationDB "
                            + "WHERE RoomNumber = 401 "
                            + "AND CheckIn <= ? "
                            + "and CheckOut >= ?");
                    pstmt.setDate(1, dateout);
                    pstmt.setDate(2, datein);
                    result = pstmt.executeQuery();
                    if (result.next())//will search for results until result.next returns true
                    {
                        pstmt = conn.prepareStatement("SELECT * FROM ReservationDB "
                                + "WHERE RoomNumber = 402 "
                                + "AND CheckIn <= ? "
                                + "and CheckOut >= ?");
                        pstmt.setDate(1, dateout);
                        pstmt.setDate(2, datein);
                        result = pstmt.executeQuery();

                        if (result.next())//will search for results until result.next returns true
                        {
                            pstmt = conn.prepareStatement("SELECT * FROM ReservationDB "
                                    + "WHERE RoomNumber = 403 "
                                    + "AND CheckIn <= ? "
                                    + "and CheckOut >= ?");
                            pstmt.setDate(1, dateout);
                            pstmt.setDate(2, datein);
                            result = pstmt.executeQuery();

                            if (result.next())//will search for results until result.next returns true
                            {
                                JOptionPane.showMessageDialog(null, "All " + roomtype + "s are booked on those dates. Please choose a different room.", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                pnlWelcome.setVisible(false);
                                pnlRoomCheck.setVisible(false);
                                pnlRoomList.setVisible(false);
                                pnlCustomerInfo.setVisible(true);
                                pnlPaymentInfo.setVisible(false);
                                pnlDone.setVisible(false);
                                pnlCheckDB.setVisible(false);
                                roomnum = 403;
                            }//end else

                        } else {
                            pnlWelcome.setVisible(false);
                            pnlRoomCheck.setVisible(false);
                            pnlRoomList.setVisible(false);
                            pnlCustomerInfo.setVisible(true);
                            pnlPaymentInfo.setVisible(false);
                            pnlDone.setVisible(false);
                            pnlCheckDB.setVisible(false);
                            roomnum = 402;
                        }//end else

                    } else {
                        pnlWelcome.setVisible(false);
                        pnlRoomCheck.setVisible(false);
                        pnlRoomList.setVisible(false);
                        pnlCustomerInfo.setVisible(true);
                        pnlPaymentInfo.setVisible(false);
                        pnlDone.setVisible(false);
                        pnlCheckDB.setVisible(false);
                        roomnum = 401;
                    }//end else
                    break;

                //check for deluxe rooms availability
                case "Penthouse Suite":
                    pstmt = conn.prepareStatement("SELECT * FROM ReservationDB "
                            + "WHERE RoomNumber = 501 "
                            + "AND CheckIn <= ? "
                            + "and CheckOut >= ?");
                    pstmt.setDate(1, dateout);
                    pstmt.setDate(2, datein);
                    result = pstmt.executeQuery();
                    if (result.next())//will search for results until result.next returns true
                    {
                        pstmt = conn.prepareStatement("SELECT * FROM ReservationDB "
                                + "WHERE RoomNumber = 502 "
                                + "AND CheckIn <= ? "
                                + "and CheckOut >= ?");
                        pstmt.setDate(1, dateout);
                        pstmt.setDate(2, datein);
                        result = pstmt.executeQuery();

                        if (result.next())//will search for results until result.next returns true
                        {
                            pstmt = conn.prepareStatement("SELECT * FROM ReservationDB "
                                    + "WHERE RoomNumber = 503 "
                                    + "AND CheckIn <= ? "
                                    + "and CheckOut >= ?");
                            pstmt.setDate(1, dateout);
                            pstmt.setDate(2, datein);
                            result = pstmt.executeQuery();

                            if (result.next())//will search for results until result.next returns true
                            {
                                JOptionPane.showMessageDialog(null, "All " + roomtype + "s are booked on those dates. Please choose a different room.", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                pnlWelcome.setVisible(false);
                                pnlRoomCheck.setVisible(false);
                                pnlRoomList.setVisible(false);
                                pnlCustomerInfo.setVisible(true);
                                pnlPaymentInfo.setVisible(false);
                                pnlDone.setVisible(false);
                                pnlCheckDB.setVisible(false);
                                roomnum = 503;
                            }//end else

                        } else {
                            pnlWelcome.setVisible(false);
                            pnlRoomCheck.setVisible(false);
                            pnlRoomList.setVisible(false);
                            pnlCustomerInfo.setVisible(true);
                            pnlPaymentInfo.setVisible(false);
                            pnlDone.setVisible(false);
                            pnlCheckDB.setVisible(false);
                            roomnum = 502;
                        }//end else

                    } else {
                        pnlWelcome.setVisible(false);
                        pnlRoomCheck.setVisible(false);
                        pnlRoomList.setVisible(false);
                        pnlCustomerInfo.setVisible(true);
                        pnlPaymentInfo.setVisible(false);
                        pnlDone.setVisible(false);
                        pnlCheckDB.setVisible(false);
                        roomnum = 501;
                    }//end else
                    break;
            }

            //System.out.println("Room Number: " +roomnum);//for debuggin purposes
            // Close the resources.
            stmt.close();
            conn.close();
            System.out.println("Room Query Done");
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnBookNowActionPerformed

    private void btnBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack1ActionPerformed
        // TODO add your handling code here:
        pnlWelcome.setVisible(false);
        pnlRoomCheck.setVisible(true);
        pnlRoomList.setVisible(false);
        pnlCustomerInfo.setVisible(false);
        pnlPaymentInfo.setVisible(false);
        pnlDone.setVisible(false);
        pnlCheckDB.setVisible(false);
    }//GEN-LAST:event_btnBack1ActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        pnlWelcome.setVisible(false);
        pnlRoomCheck.setVisible(false);
        pnlRoomList.setVisible(false);
        pnlCustomerInfo.setVisible(false);
        pnlPaymentInfo.setVisible(true);
        pnlCheckDB.setVisible(false);
        pnlDone.setVisible(false);

        firstname = txfFirstName.getText();
        //System.out.println("First Name: " +firstname);//for debugging purposes
        lastname = txfLastName.getText();
        //System.out.println("Last Name: " +lastname);//for debugging purposes
        phone = txfPhone.getText();
        //System.out.println("Phone: " +phone);//for debugging purposes
        email = txfEmail.getText();
        //System.out.println("Email: " +email);//for debugging purposes
        id = txfID.getText();
        //System.out.println("ID: " +id);//for debugging purposes

        //fills in names if same name applies to the credit card
        txfCardFirst.setText(firstname);
        txfCardLast.setText(lastname);
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnBack2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack2ActionPerformed
        // TODO add your handling code here:
        pnlWelcome.setVisible(false);
        pnlRoomCheck.setVisible(false);
        pnlRoomList.setVisible(true);
        pnlCustomerInfo.setVisible(false);
        pnlPaymentInfo.setVisible(false);
        pnlDone.setVisible(false);
        pnlCheckDB.setVisible(false);
    }//GEN-LAST:event_btnBack2ActionPerformed

    private void btnBack3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack3ActionPerformed
        // TODO add your handling code here:
        pnlWelcome.setVisible(false);
        pnlRoomCheck.setVisible(false);
        pnlRoomList.setVisible(false);
        pnlCustomerInfo.setVisible(true);
        pnlPaymentInfo.setVisible(false);
        pnlDone.setVisible(false);
        pnlCheckDB.setVisible(false);
    }//GEN-LAST:event_btnBack3ActionPerformed

    private void radCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radCashActionPerformed
        // TODO add your handling code here:
        if (radCash.isSelected()) {
            cmbPayment.setEnabled(false);
            txfCardFirst.setEnabled(false);
            txfCardFirst.setEditable(false);
            txfCardLast.setEnabled(false);
            txfCardLast.setEditable(false);
            txfCardNum.setEnabled(false);
            txfCardNum.setEditable(false);
            cmbExpiryMonth.setEnabled(false);
            cmbExpiryYear.setEnabled(false);
            txfCardCVV.setEnabled(false);
            txfCardCVV.setEditable(false);
            txfCardAddress.setEnabled(false);
            txfCardAddress.setEditable(false);
        }
    }//GEN-LAST:event_radCashActionPerformed

    private void radCreditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radCreditActionPerformed
        // TODO add your handling code here:
        if (radCredit.isSelected()) {
            cmbPayment.setEnabled(true);
            txfCardFirst.setEnabled(true);
            txfCardFirst.setEditable(true);
            txfCardLast.setEnabled(true);
            txfCardLast.setEditable(true);
            txfCardNum.setEnabled(true);
            txfCardNum.setEditable(true);
            cmbExpiryMonth.setEnabled(true);
            cmbExpiryYear.setEnabled(true);
            txfCardCVV.setEnabled(true);
            txfCardCVV.setEditable(true);
            txfCardAddress.setEnabled(true);
            txfCardAddress.setEditable(true);
        }
    }//GEN-LAST:event_radCreditActionPerformed

    private void cmbRoomTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbRoomTypeItemStateChanged
        // TODO add your handling code here:

        //to show total bill when a room is selected
        String roomtype = (String) cmbRoomType.getSelectedItem();
        //System.out.println("Room Type:" +roomtype);//for debuggin purposes

        try {
            // Create a connection to the database.
            Connection conn = DriverManager.getConnection(DB_URL);
            // Create a Statement object.
            Statement stmt = conn.createStatement();

            // Create the Room Data table.
            System.out.println("Searching for room price...");
            sql = "SELECT * FROM RoomData WHERE RoomAvailable = false AND RoomType = '" + roomtype + "'";
            //System.out.println("sql: " +sql);//for debugging purposes
            ResultSet result = stmt.executeQuery(sql);

            if (result.next())//will search for results until result.next returns true
            {
                roomprice = result.getInt("RoomPrice");
            }
            //System.out.println("Room Price: " +roomprice);//for debuggin purposes
            
            DecimalFormat format = new DecimalFormat("#.##");

            totalprice = (int) (roomprice*days);
            String price = Integer.toString(roomprice);
            String total = "Room:" + price +"x"+ format.format(days) + "days";
            if (cbxExtraBed.isSelected()) {
                total += " Extra Bed: 500";
                totalprice += bedprice;
            }
            if (cbxBreakfast.isSelected()) {
                total += " Breakfast: 250x" + Integer.toString(guests);
                totalprice += (250 * guests);
            }
            
            total += " GRAND TOTAL: " + totalprice + " THB";

            txfTotal.setText(total);//to show the roomavailtext string on text area
            txfTotal.setEditable(false);//to make a non-editable text area

            // Close the resources.
            stmt.close();
            conn.close();
            System.out.println("Room Price Acquired");
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        if (evt.getStateChange() == ItemEvent.SELECTED) {
            lblRoomInfo.setIcon(roomicon[cmbRoomType.getSelectedIndex()]);
        }
    }//GEN-LAST:event_cmbRoomTypeItemStateChanged

    private void cbxExtraBedItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxExtraBedItemStateChanged
        // TODO add your handling code here:
        String roomtype = (String) cmbRoomType.getSelectedItem();
        //System.out.println("Room Type:" +roomtype);//for debuggin purposes
        try {
            // Create a connection to the database.
            Connection conn = DriverManager.getConnection(DB_URL);
            // Create a Statement object.
            Statement stmt = conn.createStatement();

            // Create the Room Data table.
            System.out.println("Searching for room price...");
            sql = "SELECT * FROM RoomData WHERE RoomAvailable = false AND RoomType = '" + roomtype + "'";
            //System.out.println("sql: " +sql);//for debugging purposes
            ResultSet result = stmt.executeQuery(sql);

            if (result.next())//will search for results until result.next returns true
            {
                roomprice = result.getInt("RoomPrice");
            }
            //System.out.println("Room Price: " +roomprice);//for debuggin purposes

            DecimalFormat format = new DecimalFormat("#.##");

            totalprice = (int) (roomprice*days);
            String price = Integer.toString(roomprice);
            String total = "Room:" + price +"x"+ format.format(days) + "days";
            if (cbxExtraBed.isSelected()) {
                total += " Extra Bed: 500";
                totalprice += bedprice;
            }
            if (cbxBreakfast.isSelected()) {
                total += " Breakfast: 250x" + Integer.toString(guests);
                totalprice += (250 * guests);
            }
            
            total += " GRAND TOTAL: " + totalprice + " THB";

            txfTotal.setText(total);//to show the roomavailtext string on text area
            txfTotal.setEditable(false);//to make a non-editable text area

            // Close the resources.
            stmt.close();
            conn.close();
            System.out.println("Room Price Acquired");
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }//GEN-LAST:event_cbxExtraBedItemStateChanged

    private void cbxBreakfastItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxBreakfastItemStateChanged
        // TODO add your handling code here:
        String roomtype = (String) cmbRoomType.getSelectedItem();
        //System.out.println("Room Type:" +roomtype);//for debuggin purposes
        try {
            // Create a connection to the database.
            Connection conn = DriverManager.getConnection(DB_URL);
            // Create a Statement object.
            Statement stmt = conn.createStatement();

            // Create the Room Data table.
            System.out.println("Searching for room price...");
            sql = "SELECT * FROM RoomData WHERE RoomAvailable = false AND RoomType = '" + roomtype + "'";
            //System.out.println("sql: " +sql);//for debugging purposes
            ResultSet result = stmt.executeQuery(sql);

            if (result.next())//will search for results until result.next returns true
            {
                roomprice = result.getInt("RoomPrice");
            }
            //System.out.println("Room Price: " +roomprice);//for debuggin purposes

            DecimalFormat format = new DecimalFormat("#.##");

            totalprice = (int) (roomprice*days);
            String price = Integer.toString(roomprice);
            String total = "Room:" + price +"x"+ format.format(days) + "days";
            if (cbxExtraBed.isSelected()) {
                total += " Extra Bed: 500";
                totalprice += bedprice;
            }
            if (cbxBreakfast.isSelected()) {
                total += " Breakfast: 250x" + Integer.toString(guests);
                totalprice += (250 * guests);
            }
            
            total += " GRAND TOTAL: " + totalprice + " THB";

            txfTotal.setText(total);//to show the roomavailtext string on text area
            txfTotal.setEditable(false);//to make a non-editable text area

            // Close the resources.
            stmt.close();
            conn.close();
            System.out.println("Room Price Acquired");
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }//GEN-LAST:event_cbxBreakfastItemStateChanged

    private void cmbCheckInMonthItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCheckInMonthItemStateChanged
        // TODO add your handling code here:

        //to vary the days on combo box
        if ("January".equals(cmbCheckInMonth.getSelectedItem())
                || "March".equals(cmbCheckInMonth.getSelectedItem())
                || "May".equals(cmbCheckInMonth.getSelectedItem())
                || "July".equals(cmbCheckInMonth.getSelectedItem())
                || "August".equals(cmbCheckInMonth.getSelectedItem())
                || "October".equals(cmbCheckInMonth.getSelectedItem())
                || "December".equals(cmbCheckInMonth.getSelectedItem())) {
            cmbCheckInDay.setModel(longmonth);//for 31-day months
        } else if ("April".equals(cmbCheckInMonth.getSelectedItem())
                || "June".equals(cmbCheckInMonth.getSelectedItem())
                || "September".equals(cmbCheckInMonth.getSelectedItem())
                || "November".equals(cmbCheckInMonth.getSelectedItem())) {
            cmbCheckInDay.setModel(shortmonth);//for 30-day months
        } else if ("February".equals(cmbCheckInMonth.getSelectedItem())
                && "2016".equals(cmbCheckInYear.getSelectedItem())) {
            cmbCheckInDay.setModel(leapmonth);//for leap year 2016
        } else {
            cmbCheckInDay.setModel(febmonth);//for february months
        }
    }//GEN-LAST:event_cmbCheckInMonthItemStateChanged

    private void cmbCheckInYearItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCheckInYearItemStateChanged
        // TODO add your handling code here:
        //to vary the days on combo box
        if ("January".equals(cmbCheckInMonth.getSelectedItem())
                || "March".equals(cmbCheckInMonth.getSelectedItem())
                || "May".equals(cmbCheckInMonth.getSelectedItem())
                || "July".equals(cmbCheckInMonth.getSelectedItem())
                || "August".equals(cmbCheckInMonth.getSelectedItem())
                || "October".equals(cmbCheckInMonth.getSelectedItem())
                || "December".equals(cmbCheckInMonth.getSelectedItem())) {
            cmbCheckInDay.setModel(longmonth);//for 31-day months
        } else if ("April".equals(cmbCheckInMonth.getSelectedItem())
                || "June".equals(cmbCheckInMonth.getSelectedItem())
                || "September".equals(cmbCheckInMonth.getSelectedItem())
                || "November".equals(cmbCheckInMonth.getSelectedItem())) {
            cmbCheckInDay.setModel(shortmonth);//for 30-day months
        } else if ("February".equals(cmbCheckInMonth.getSelectedItem())
                && "2016".equals(cmbCheckInYear.getSelectedItem())) {
            cmbCheckInDay.setModel(leapmonth);//for leap year 2016
        } else {
            cmbCheckInDay.setModel(febmonth);//for february months
        }
    }//GEN-LAST:event_cmbCheckInYearItemStateChanged

    private void cmbCheckOutMonthItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCheckOutMonthItemStateChanged
        // TODO add your handling code here:
        //to vary the days on combo box
        if ("January".equals(cmbCheckOutMonth.getSelectedItem())
                || "March".equals(cmbCheckOutMonth.getSelectedItem())
                || "May".equals(cmbCheckOutMonth.getSelectedItem())
                || "July".equals(cmbCheckOutMonth.getSelectedItem())
                || "August".equals(cmbCheckOutMonth.getSelectedItem())
                || "October".equals(cmbCheckOutMonth.getSelectedItem())
                || "December".equals(cmbCheckOutMonth.getSelectedItem())) {
            cmbCheckOutDay.setModel(longmonth);//for 31-day months
        } else if ("April".equals(cmbCheckOutMonth.getSelectedItem())
                || "June".equals(cmbCheckOutMonth.getSelectedItem())
                || "September".equals(cmbCheckOutMonth.getSelectedItem())
                || "November".equals(cmbCheckOutMonth.getSelectedItem())) {
            cmbCheckOutDay.setModel(shortmonth);//for 30-day months
        } else if ("February".equals(cmbCheckOutMonth.getSelectedItem())
                && "2016".equals(cmbCheckOutYear.getSelectedItem())) {
            cmbCheckOutDay.setModel(leapmonth);//for leap year 2016
        } else {
            cmbCheckOutDay.setModel(febmonth);//for february months
        }
    }//GEN-LAST:event_cmbCheckOutMonthItemStateChanged

    private void cmbCheckOutYearItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCheckOutYearItemStateChanged
        // TODO add your handling code here:
        if ("January".equals(cmbCheckOutMonth.getSelectedItem())
                || "March".equals(cmbCheckOutMonth.getSelectedItem())
                || "May".equals(cmbCheckOutMonth.getSelectedItem())
                || "July".equals(cmbCheckOutMonth.getSelectedItem())
                || "August".equals(cmbCheckOutMonth.getSelectedItem())
                || "October".equals(cmbCheckOutMonth.getSelectedItem())
                || "December".equals(cmbCheckOutMonth.getSelectedItem())) {
            cmbCheckOutDay.setModel(longmonth);//for 31-day months
        } else if ("April".equals(cmbCheckOutMonth.getSelectedItem())
                || "June".equals(cmbCheckOutMonth.getSelectedItem())
                || "September".equals(cmbCheckOutMonth.getSelectedItem())
                || "November".equals(cmbCheckOutMonth.getSelectedItem())) {
            cmbCheckOutDay.setModel(shortmonth);//for 30-day months
        } else if ("February".equals(cmbCheckOutMonth.getSelectedItem())
                && "2016".equals(cmbCheckOutYear.getSelectedItem())) {
            cmbCheckOutDay.setModel(leapmonth);//for leap year 2016
        } else {
            cmbCheckOutDay.setModel(febmonth);//for february months
        }
    }//GEN-LAST:event_cmbCheckOutYearItemStateChanged

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        // TODO add your handling code here:

        if (radCash.isSelected() || radCredit.isSelected()) {
            pnlWelcome.setVisible(false);
            pnlRoomCheck.setVisible(false);
            pnlRoomList.setVisible(false);
            pnlCustomerInfo.setVisible(false);
            pnlPaymentInfo.setVisible(false);
            pnlDone.setVisible(true);
            pnlCheckDB.setVisible(false);

            if (radCash.isSelected()) {
                paymentmethod = "Cash";
                cardtype = "";
                cardfirst = "";
                cardlast = "";
                cardnum = "";
                cardexpiry = "";
                cardCVV = "";
                cardaddress = "";
            } else {
                paymentmethod = "Credit Card";
                //System.out.println("Payment Method: " +paymentmethod);//for debugging purposes

                cardtype = (String) cmbPayment.getSelectedItem();
                //System.out.println("Card Type: " +cardtype);//for debugging purposes

                cardfirst = txfCardFirst.getText();
                //System.out.println("First Name: " +cardfirst);//for debugging purposes
                cardlast = txfCardLast.getText();
                //System.out.println("Last Name: " +cardlast);//for debugging purposes
                cardnum = txfCardNum.getText();
                //System.out.println("Credit Number: " +cardnum);//for debugging purposes

                cardmonth = (String) cmbExpiryMonth.getSelectedItem();
                cardyear = (String) cmbExpiryYear.getSelectedItem();
                cardexpiry = cardmonth + " " + cardyear;
                //System.out.println("Expiry: " +cardexpiry);//for debugging purposes

                cardCVV = txfCardCVV.getText();
                //System.out.println("CVV: " +cardCVV);//for debugging purposes
                cardaddress = txfCardAddress.getText();
                //System.out.println("Address: " +cardaddress);//for debugging purposes
            }

            try {
                // Create a connection to the database.
                Connection conn
                        = DriverManager.getConnection(DB_URL);

                // Create a Statement object.
                Statement stmt = conn.createStatement();

                // Add Deluxe Rooms to table
                sql = "INSERT INTO ReservationDB (ReservationNumber) VALUES " + reservationNum;
                //System.out.println("SQL reservation number: " + sql);//for debugging purposes
                stmt.executeUpdate(sql);

                // Close the resources.
                stmt.close();
                conn.close();
                System.out.println("Reservation number sent to DB");
            } catch (Exception ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }

            //writing on database
            Reservation r = new Reservation(reservationNum, chkinday, chkinmonth, chkinyear, chkoutday, chkoutmonth, chkoutyear, chkin, chkout, guests, roomnum, totalprice);
            r.sendReservationDB();//add reservation info on the database;

            Guest g = new Guest(reservationNum, firstname, lastname, id, phone, email);
            g.SendGuestDB();

            Payment p = new Payment(reservationNum, paymentmethod, cardtype, cardfirst, cardlast, cardnum, cardexpiry, cardCVV, cardaddress);
            p.sendPaymentDB();

            JOptionPane.showMessageDialog(null, "Booking Success!\nYour reservation number is: " + reservationNum, "FBK Hotel", JOptionPane.PLAIN_MESSAGE);

            reservationNum++;
        } else {
            JOptionPane.showMessageDialog(null, "Please choose a payment method.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSubmitActionPerformed

    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoneActionPerformed
        // TODO add your handling code here:
        // Exit the app
        System.exit(0);
    }//GEN-LAST:event_btnDoneActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        pnlWelcome.setVisible(false);
        pnlRoomCheck.setVisible(true);
        pnlRoomList.setVisible(false);
        pnlCustomerInfo.setVisible(false);
        pnlPaymentInfo.setVisible(false);
        pnlDone.setVisible(false);
        pnlCheckDB.setVisible(false);

        //clear Room Check Panel
        cmbCheckInDay.setSelectedIndex(0);
        cmbCheckInMonth.setSelectedIndex(0);
        cmbCheckInYear.setSelectedIndex(0);
        cmbCheckOutDay.setSelectedIndex(0);
        cmbCheckOutMonth.setSelectedIndex(0);
        cmbCheckOutYear.setSelectedIndex(0);
        cmbNumGuest.setSelectedIndex(0);

        //clear Room List Panel
        //txaRoomAvail.setText("");
        cmbRoomType.setSelectedIndex(0);
        cbxExtraBed.setSelected(false);
        cbxBreakfast.setSelected(false);
        txfTotal.setText("");

        //clear Customer Info Panel
        txfFirstName.setText("");
        txfLastName.setText("");
        txfPhone.setText("");
        txfEmail.setText("");
        txfID.setText("");

        //clear Payment Info
        grpPaymentMethod.clearSelection();
        cmbPayment.setSelectedIndex(0);
        txfCardFirst.setText("");
        txfCardLast.setText("");
        txfCardNum.setText("");
        cmbExpiryMonth.setSelectedIndex(0);
        cmbExpiryYear.setSelectedIndex(0);
        txfCardCVV.setText("");
        txfCardAddress.setText("");

        //clear CheckDB panel
        txfQuery.setText("");
        txaDisplay.setText("");
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnCheckDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckDBActionPerformed
        // TODO add your handling code here:
        pnlWelcome.setVisible(false);
        pnlRoomCheck.setVisible(false);
        pnlRoomList.setVisible(false);
        pnlCustomerInfo.setVisible(false);
        pnlPaymentInfo.setVisible(false);
        pnlDone.setVisible(false);
        pnlCheckDB.setVisible(true);
    }//GEN-LAST:event_btnCheckDBActionPerformed

    private void btnBack4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack4ActionPerformed
        // TODO add your handling code here
        pnlWelcome.setVisible(false);
        pnlRoomCheck.setVisible(false);
        pnlRoomList.setVisible(false);
        pnlCustomerInfo.setVisible(false);
        pnlPaymentInfo.setVisible(false);
        pnlDone.setVisible(true);
        pnlCheckDB.setVisible(false);
    }//GEN-LAST:event_btnBack4ActionPerformed

    private void btnQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQueryActionPerformed
        // TODO add your handling code here:

        query = Integer.parseInt(txfQuery.getText());
        System.out.println("Query number: " + query);//for debugging purposes
        String querydisplay = "";

        try {
            // Create a connection to the database.
            Connection conn = DriverManager.getConnection(DB_URL);
            // Create a Statement object.
            Statement stmt = conn.createStatement();

            // Create the Room Data table.
            System.out.println("Searching database...");
            sql = "SELECT * FROM ReservationDB WHERE ReservationNumber = " + query;
            //System.out.println("sql: " +sql);//for debugging purposes
            ResultSet result = stmt.executeQuery(sql);

            if (result.next())//will search for results until result.next returns true
            {
                querydisplay += ("Reservation Number: #" + query + "\n"
                        + "Name: Kh." + result.getString("FirstName") + " " + result.getString("LastName") + "\n"
                        + "ID: " + result.getString("ID") + "\n"
                        + "Phone: " + result.getString("Phone") + "\n"
                        + "Email: " + result.getString("Email") + "\n"
                        + "Check In: " + result.getDate("CheckIn") + "\n"
                        + "Check Out: " + result.getDate("Checkout") + "\n"
                        + "Number of Guests: " + result.getInt("GuestNumber") + "\n"
                        + "Room Number: " + result.getInt("RoomNumber") + "\n"
                        + "Payment Method: " + result.getString("PaymentMethod") + "\n"
                        + "Total Bill: ฿" + result.getInt("TotalBill"));
            }
            //System.out.println("Query: " +querydisplay);//for debuggin purposes

            txaDisplay.setText(querydisplay);//to show the roomavailtext string on text area
            txaDisplay.setEditable(false);//to make a non-editable text area
            txaDisplay.setCaretPosition(0);//to set position to top of text area

            // Close the resources.
            stmt.close();
            conn.close();
            System.out.println("Database Queried");
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnQueryActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReservationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReservationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReservationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReservationGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReservationGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bgCheckDB;
    private javax.swing.JLabel bgCustomerInfo;
    private javax.swing.JLabel bgDone;
    private javax.swing.JLabel bgPaymentInfo;
    private javax.swing.JLabel bgRoomCheck;
    private javax.swing.JLabel bgRoomList;
    private javax.swing.JLabel bgWelcome;
    private javax.swing.JButton btnBack1;
    private javax.swing.JButton btnBack2;
    private javax.swing.JButton btnBack3;
    private javax.swing.JButton btnBack4;
    private javax.swing.JButton btnBookNow;
    private javax.swing.JButton btnCheckDB;
    private javax.swing.JButton btnDone;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnQuery;
    private javax.swing.JButton btnRoomCheck;
    private javax.swing.JButton btnStartBooking;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JCheckBox cbxBreakfast;
    private javax.swing.JCheckBox cbxExtraBed;
    private javax.swing.JComboBox cmbCheckInDay;
    private javax.swing.JComboBox cmbCheckInMonth;
    private javax.swing.JComboBox cmbCheckInYear;
    private javax.swing.JComboBox cmbCheckOutDay;
    private javax.swing.JComboBox cmbCheckOutMonth;
    private javax.swing.JComboBox cmbCheckOutYear;
    private javax.swing.JComboBox cmbExpiryMonth;
    private javax.swing.JComboBox cmbExpiryYear;
    private javax.swing.JComboBox cmbNumGuest;
    private javax.swing.JComboBox cmbPayment;
    private javax.swing.JComboBox cmbRoomType;
    private javax.swing.ButtonGroup grpPaymentMethod;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblCVV;
    private javax.swing.JLabel lblCardFirst;
    private javax.swing.JLabel lblCardLast;
    private javax.swing.JLabel lblCardNum;
    private javax.swing.JLabel lblCheckDB;
    private javax.swing.JLabel lblCheckIn;
    private javax.swing.JLabel lblCheckInDay;
    private javax.swing.JLabel lblCheckInMonth;
    private javax.swing.JLabel lblCheckInYear;
    private javax.swing.JLabel lblCheckOut;
    private javax.swing.JLabel lblCheckOutDay;
    private javax.swing.JLabel lblCheckOutMonth;
    private javax.swing.JLabel lblCheckOutYear;
    private javax.swing.JLabel lblCusInfo;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblExpiry;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblLastName;
    private javax.swing.JLabel lblNumGuest;
    private javax.swing.JLabel lblPayInfo;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblQuery;
    private javax.swing.JLabel lblRoomInfo;
    private javax.swing.JLabel lblRoomType;
    private javax.swing.JLabel lblThankYou;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel pnlCheckDB;
    private javax.swing.JPanel pnlCustomerInfo;
    private javax.swing.JPanel pnlDone;
    private javax.swing.JPanel pnlPaymentInfo;
    private javax.swing.JPanel pnlRoomCheck;
    private javax.swing.JPanel pnlRoomList;
    private javax.swing.JPanel pnlWelcome;
    private javax.swing.JRadioButton radCash;
    private javax.swing.JRadioButton radCredit;
    private javax.swing.JScrollPane scrDisplay;
    private javax.swing.JSeparator sepPayment;
    private javax.swing.JTextArea txaDisplay;
    private javax.swing.JTextField txfCardAddress;
    private javax.swing.JTextField txfCardCVV;
    private javax.swing.JTextField txfCardFirst;
    private javax.swing.JTextField txfCardLast;
    private javax.swing.JTextField txfCardNum;
    private javax.swing.JTextField txfEmail;
    private javax.swing.JTextField txfFirstName;
    private javax.swing.JTextField txfID;
    private javax.swing.JTextField txfLastName;
    private javax.swing.JTextField txfPhone;
    private javax.swing.JTextField txfQuery;
    private javax.swing.JTextField txfTotal;
    // End of variables declaration//GEN-END:variables
}
