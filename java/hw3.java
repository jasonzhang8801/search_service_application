/**
 * Created by jasonzhang on 3/25/16.
 */
// import necessary library
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.sql.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.SystemColor;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;
import org.jdesktop.swingx.JXDatePicker;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
//import javax.swing.table.DefaultTableModel;
//import java.text.ParseException;
//import java.awt.Component;
//import java.awt.font.*;
//import java.awt.EventQueue;
//import java.awt.BorderLayout;
//import java.awt.*;
//import java.awt.Image;
//import java.util.Date;
//import javax.swing.*;


public class hw3 {
    // declare the variable
    Connection connection = null;
    Boolean searchBusiness;
    String queryString;
    private JFrame frame;
    private JScrollPane scrollPane_query;
    private JTextArea textArea_query;
    private JComboBox comboBox_select_andor;
    private JTable table_result;
    private JScrollPane scrollPane_category;
    private JPanel panel_category;
    private ArrayList<JCheckBox> categories;
    private JCheckBox checkBox_category_1;
    private JCheckBox checkBox_category_2;
    private JCheckBox checkBox_category_3;
    private JCheckBox checkBox_category_4;
    private JCheckBox checkBox_category_5;
    private JCheckBox checkBox_category_6;
    private JCheckBox checkBox_category_7;
    private JCheckBox checkBox_category_8;
    private JCheckBox checkBox_category_9;
    private JCheckBox checkBox_category_10;
    private JCheckBox checkBox_category_11;
    private JCheckBox checkBox_category_12;
    private JCheckBox checkBox_category_13;
    private JCheckBox checkBox_category_14;
    private JCheckBox checkBox_category_15;
    private JCheckBox checkBox_category_16;
    private JCheckBox checkBox_category_17;
    private JCheckBox checkBox_category_18;
    private JCheckBox checkBox_category_19;
    private JCheckBox checkBox_category_20;
    private JCheckBox checkBox_category_21;
    private JCheckBox checkBox_category_22;
    private JCheckBox checkBox_category_23;
    private JCheckBox checkBox_category_24;
    private JCheckBox checkBox_category_25;
    private JCheckBox checkBox_category_26;
    private JCheckBox checkBox_category_27;
    private JCheckBox checkBox_category_28;
    private JPanel panel_subcategory;
    private JScrollPane scrollPane_subcategory;
    private JComboBox comboBox_checkin_day1;
    private JComboBox comboBox_checkin_day2;
    private JComboBox comboBox_checkin_hour1;
    private JComboBox comboBox_checkin_hour2;
    private JComboBox comboBox_checkin_numberofcheckin;
    private JTextField textField_checkin_value;
    private JXDatePicker datePicker_review_from;
    private JXDatePicker datePicker_review_to;
    private JTextField textField_review_value1;
    private JTextField textField_review_value2;
    private JComboBox comboBox_review_star;
    private JComboBox comboBox_review_votes;
    private org.jdesktop.swingx.JXDatePicker datePicker_user_membersince;
    private JComboBox comboBox_user_reviewcount;
    private JComboBox comboBox_user_numberoffriend;
    private JComboBox comboBox_user_avgstars;
    private JTextField textField_user_value1;
    private JTextField textField_user_value2;
    private JTextField textField_user_value3;



    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    hw3 window = new hw3();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static Connection dbConnector() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "", "");
            return conn;
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    /**
     * Create the application.
     */
    public hw3() {
        initialize();
        connection = dbConnector();

    }
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        searchBusiness = true;
        frame = new JFrame();
        frame.setBounds(100, 100, 1200, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        // create business_category
        panel_category = new JPanel();
        panel_category.setLayout(new GridLayout(0, 1, 0, 10));

        scrollPane_category = new JScrollPane(panel_category);
        scrollPane_category.setBounds(10, 80, 150, 310);
        frame.getContentPane().add(scrollPane_category);

        // create business_subcategory
        panel_subcategory = new JPanel();
        panel_subcategory.setLayout(new GridLayout(0, 1, 0, 10));
        checkBox_category_1 = new JCheckBox("Active Life");
        checkBox_category_2 = new JCheckBox("Arts & Entertainment");
        checkBox_category_3 = new JCheckBox("Automotive");
        checkBox_category_4 = new JCheckBox("Automotive");
        checkBox_category_5 = new JCheckBox("Car Rental");
        checkBox_category_6 = new JCheckBox("Cafes");
        checkBox_category_7 = new JCheckBox("Beauty & Spas");
        checkBox_category_8 = new JCheckBox("Convenience Stores");
        checkBox_category_9 = new JCheckBox("Dentists");
        checkBox_category_10 = new JCheckBox("Doctors");
        checkBox_category_11 = new JCheckBox("Drugstores");
        checkBox_category_12 = new JCheckBox("Department Stores");
        checkBox_category_13 = new JCheckBox("Education");
        checkBox_category_14 = new JCheckBox("Event Planning & Services");
        checkBox_category_15 = new JCheckBox("Flowers & Gifts");
        checkBox_category_16 = new JCheckBox("Food");
        checkBox_category_17 = new JCheckBox("Home Services");
        checkBox_category_18 = new JCheckBox("Home & Garden");
        checkBox_category_19 = new JCheckBox("Hospitals");
        checkBox_category_20 = new JCheckBox("Hotels & Travel");
        checkBox_category_21 = new JCheckBox("Hardware Stores");
        checkBox_category_22 = new JCheckBox("Grocery");
        checkBox_category_23 = new JCheckBox("Medical Centers");
        checkBox_category_24 = new JCheckBox("Nurseries & Gardening");
        checkBox_category_25 = new JCheckBox("Nightlife");
        checkBox_category_26 = new JCheckBox("Restaurants");
        checkBox_category_27 = new JCheckBox("Shopping");
        checkBox_category_28 = new JCheckBox("Transportation");

        categories = new ArrayList<>();
        categories.add(checkBox_category_1);
        categories.add(checkBox_category_2);
        categories.add(checkBox_category_3);
        categories.add(checkBox_category_4);
        categories.add(checkBox_category_5);
        categories.add(checkBox_category_6);
        categories.add(checkBox_category_7);
        categories.add(checkBox_category_8);
        categories.add(checkBox_category_9);
        categories.add(checkBox_category_10);
        categories.add(checkBox_category_11);
        categories.add(checkBox_category_12);
        categories.add(checkBox_category_13);
        categories.add(checkBox_category_14);
        categories.add(checkBox_category_15);
        categories.add(checkBox_category_16);
        categories.add(checkBox_category_17);
        categories.add(checkBox_category_18);
        categories.add(checkBox_category_19);
        categories.add(checkBox_category_20);
        categories.add(checkBox_category_21);
        categories.add(checkBox_category_22);
        categories.add(checkBox_category_23);
        categories.add(checkBox_category_24);
        categories.add(checkBox_category_25);
        categories.add(checkBox_category_26);
        categories.add(checkBox_category_27);
        categories.add(checkBox_category_28);
        for (int i = 0; i < categories.size(); i++) {
            panel_category.add(categories.get(i));
        }
        for (int i = 0; i < categories.size(); i++) {
            categories.get(i).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    setBusinessQueryString();
                }
            });
        }
        JPanel panel_checkin = new JPanel();
        panel_checkin.setBounds(310, 80, 150, 310);
        frame.getContentPane().add(panel_checkin);
        panel_checkin.setLayout(null);
        JLabel label_checkin_from = new JLabel("From");
        label_checkin_from.setBounds(5, 5, 60, 15);
        panel_checkin.add(label_checkin_from);
        comboBox_checkin_day1 = new JComboBox();
        comboBox_checkin_day1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setBusinessQueryString();
            }
        });
        comboBox_checkin_day1.setModel(new DefaultComboBoxModel(new String[] {"Day", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}));
        comboBox_checkin_day1.setBounds(0, 32, 70, 25);
        panel_checkin.add(comboBox_checkin_day1);
        JLabel lblTo = new JLabel("To");
        lblTo.setBounds(5, 75, 60, 15);
        panel_checkin.add(lblTo);
        JLabel label_checkin_numberofcheckins = new JLabel("Number of checkins");
        label_checkin_numberofcheckins.setBounds(5, 145, 130, 15);
        panel_checkin.add(label_checkin_numberofcheckins);
        comboBox_checkin_numberofcheckin = new JComboBox();
        comboBox_checkin_numberofcheckin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setBusinessQueryString();
            }
        });
        comboBox_checkin_numberofcheckin.setModel(new DefaultComboBoxModel(new String[] {"=", ">", "<"}));
        comboBox_checkin_numberofcheckin.setBounds(5, 175, 130, 25);
        panel_checkin.add(comboBox_checkin_numberofcheckin);
        JLabel label_checkin_value = new JLabel("Value");
        label_checkin_value.setBounds(5, 220, 60, 15);
        panel_checkin.add(label_checkin_value);
        textField_checkin_value = new JTextField();
        textField_checkin_value.setColumns(10);
        textField_checkin_value.setBounds(55, 215, 80, 25);
        panel_checkin.add(textField_checkin_value);
        textField_checkin_value.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                setBusinessQueryString();
            }
            public void removeUpdate(DocumentEvent e) {
                setBusinessQueryString();
            }
            public void insertUpdate(DocumentEvent e) {
                setBusinessQueryString();
            }
        });
        comboBox_checkin_day2 = new JComboBox();
        comboBox_checkin_day2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setBusinessQueryString();
            }
        });
        comboBox_checkin_day2.setModel(new DefaultComboBoxModel(new String[] {"Day", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}));
        comboBox_checkin_day2.setBounds(0, 102, 70, 25);
        panel_checkin.add(comboBox_checkin_day2);
        comboBox_checkin_hour1 = new JComboBox();
        comboBox_checkin_hour1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setBusinessQueryString();
            }
        });
        comboBox_checkin_hour1.setModel(new DefaultComboBoxModel(new String[] {"Hour", "00:00 - 01:00", "01:00 - 02:00", "02:00 - 03:00", "03:00 - 04:00", "04:00 - 05:00", "05:00 - 06:00", "06:00 - 07:00", "07:00 - 08:00", "08:00 - 09:00", "09:00 - 10:00", "10:00 - 11:00", "11:00 - 12:00", "12:00 - 13:00", "13:00 - 14:00", "14:00 - 15:00", "15:00 - 16:00", "16:00 - 17:00", "17:00 - 18:00", "18:00 - 19:00", "19:00 - 20:00", "20:00 - 21:00", "21:00 - 22:00", "22:00 - 23:00", "23:00 - 00:00"}));
        comboBox_checkin_hour1.setBounds(75, 31, 70, 25);
        panel_checkin.add(comboBox_checkin_hour1);
        comboBox_checkin_hour2 = new JComboBox();
        comboBox_checkin_hour2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setBusinessQueryString();
            }
        });
        comboBox_checkin_hour2.setModel(new DefaultComboBoxModel(new String[] {"Hour", "00:00 - 01:00", "01:00 - 02:00", "02:00 - 03:00", "03:00 - 04:00", "04:00 - 05:00", "05:00 - 06:00", "06:00 - 07:00", "07:00 - 08:00", "08:00 - 09:00", "09:00 - 10:00", "10:00 - 11:00", "11:00 - 12:00", "12:00 - 13:00", "13:00 - 14:00", "14:00 - 15:00", "15:00 - 16:00", "16:00 - 17:00", "17:00 - 18:00", "18:00 - 19:00", "19:00 - 20:00", "20:00 - 21:00", "21:00 - 22:00", "22:00 - 23:00", "23:00 - 00:00"}));
        comboBox_checkin_hour2.setBounds(75, 100, 70, 25);
        panel_checkin.add(comboBox_checkin_hour2);
        JPanel panel_review = new JPanel();
        panel_review.setBounds(460, 80, 150, 310);
        panel_review.setLayout(null);
        frame.getContentPane().add(panel_review);
        JLabel label_review_from = new JLabel("From");
        label_review_from.setBounds(5, 20, 60, 15);
        panel_review.add(label_review_from);
        JLabel lbl_review_to = new JLabel("To");
        lbl_review_to.setBounds(5, 60, 60, 15);
        panel_review.add(lbl_review_to);
        JSeparator separator = new JSeparator();
        separator.setBackground(Color.GRAY);
        separator.setBounds(0, 100, 150, 5);
        panel_review.add(separator);
        JSeparator separator_3 = new JSeparator();
        separator_3.setBackground(Color.GRAY);
        separator_3.setBounds(0, 210, 150, 5);
        panel_review.add(separator_3);
        JLabel label_review_star = new JLabel("Star:");
        label_review_star.setHorizontalAlignment(SwingConstants.LEFT);
        label_review_star.setBounds(5, 130, 60, 15);
        panel_review.add(label_review_star);
        JLabel label_review_value = new JLabel("Value:");
        label_review_value.setHorizontalAlignment(SwingConstants.LEFT);
        label_review_value.setBounds(5, 160, 60, 15);
        panel_review.add(label_review_value);
        JLabel label = new JLabel("Votes:");
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setBounds(5, 240, 60, 15);
        panel_review.add(label);
        JLabel label_1 = new JLabel("Value:");
        label_1.setHorizontalAlignment(SwingConstants.LEFT);
        label_1.setBounds(5, 270, 60, 15);
        panel_review.add(label_1);
        textField_review_value1 = new JTextField();
        textField_review_value1.setBounds(55, 157, 90, 25);
        panel_review.add(textField_review_value1);
        textField_review_value1.setColumns(10);
        textField_review_value1.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                setBusinessQueryString();
            }
            public void removeUpdate(DocumentEvent e) {
                setBusinessQueryString();
            }
            public void insertUpdate(DocumentEvent e) {
                setBusinessQueryString();
            }
        });
        textField_review_value2 = new JTextField();
        textField_review_value2.setColumns(10);
        textField_review_value2.setBounds(55, 267, 90, 25);
        panel_review.add(textField_review_value2);
        textField_review_value2.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                setBusinessQueryString();
            }
            public void removeUpdate(DocumentEvent e) {
                setBusinessQueryString();
            }
            public void insertUpdate(DocumentEvent e) {
                setBusinessQueryString();
            }
        });
        comboBox_review_star = new JComboBox();
        comboBox_review_star.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //setBusinessQueryString();
            }
        });
        comboBox_review_star.setModel(new DefaultComboBoxModel(new String[] {"=", ">", "<"}));
        comboBox_review_star.setBounds(55, 125, 90, 25);
        panel_review.add(comboBox_review_star);

        comboBox_review_votes = new JComboBox();
        comboBox_review_votes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setBusinessQueryString();
            }
        });
        comboBox_review_votes.setModel(new DefaultComboBoxModel(new String[] {"=", ">", "<"}));
        comboBox_review_votes.setBounds(55, 235, 90, 25);
        panel_review.add(comboBox_review_votes);
        datePicker_review_from = new JXDatePicker();
        datePicker_review_from.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setBusinessQueryString();
            }
        });
        datePicker_review_from.getEditor().setEditable(false);
        datePicker_review_from.setFormats(new String[] {"yyyy-MM-dd"});
        datePicker_review_from.setBounds(41, 30, 111, 23);
        panel_review.add(datePicker_review_from);
        datePicker_review_to = new JXDatePicker();
        datePicker_review_to.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setBusinessQueryString();
            }
        });
        datePicker_review_to.getEditor().setEditable(false);
        datePicker_review_to.setFormats(new String[] {"yyyy-MM-dd"});
        datePicker_review_to.setBounds(41, 56, 111, 23);
        panel_review.add(datePicker_review_to);
        JPanel panel_users = new JPanel();
        panel_users.setBounds(10, 420, 300, 170);
        panel_users.setBackground(SystemColor.window);
        frame.getContentPane().add(panel_users);
        panel_users.setLayout(null);
        JLabel lbl_user_membersince = new JLabel("Member Since");
        lbl_user_membersince.setBounds(5, 10, 80, 15);
        panel_users.add(lbl_user_membersince);
        JLabel lbl_user_reviewcount = new JLabel("Review Count");
        lbl_user_reviewcount.setBounds(5, 40, 80, 15);
        panel_users.add(lbl_user_reviewcount);
        JLabel lbl_user_numberof = new JLabel("Number of Friends");
        lbl_user_numberof.setBounds(5, 70, 80, 15);
        panel_users.add(lbl_user_numberof);
        JLabel lbl_user_averagestars = new JLabel("Avg stars");
        lbl_user_averagestars.setBounds(5, 100, 80, 15);
        panel_users.add(lbl_user_averagestars);
        JLabel lbl_user_select= new JLabel("Select");
        lbl_user_select.setBounds(5, 130, 80, 15);
        panel_users.add(lbl_user_select);
        comboBox_user_reviewcount = new JComboBox();
        comboBox_user_reviewcount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setUserQueryString();
            }
        });
        comboBox_user_reviewcount.setModel(new DefaultComboBoxModel(new String[] {"=", ">", "<"}));
        comboBox_user_reviewcount.setBounds(100, 40, 80, 15);
        panel_users.add(comboBox_user_reviewcount);
        comboBox_user_numberoffriend = new JComboBox();
        comboBox_user_numberoffriend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setUserQueryString();
            }
        });
        comboBox_user_numberoffriend.setModel(new DefaultComboBoxModel(new String[] {"=", ">", "<"}));
        comboBox_user_numberoffriend.setBounds(100, 70, 80, 15);
        panel_users.add(comboBox_user_numberoffriend);
        comboBox_user_avgstars = new JComboBox();
        comboBox_user_avgstars.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setUserQueryString();
            }
        });
        comboBox_user_avgstars.setModel(new DefaultComboBoxModel(new String[] {"=", ">", "<"}));
        comboBox_user_avgstars.setBounds(100, 100, 80, 15);
        panel_users.add(comboBox_user_avgstars);
        JLabel lbl_user_value1 = new JLabel("Value");
        lbl_user_value1.setBounds(185, 40, 40, 15);
        panel_users.add(lbl_user_value1);
        JLabel label_user_value2 = new JLabel("Value");
        label_user_value2.setBounds(185, 70, 40, 15);
        panel_users.add(label_user_value2);
        JLabel label_user_value3 = new JLabel("Value");
        label_user_value3.setBounds(185, 100, 40, 15);
        panel_users.add(label_user_value3);
        textField_user_value1 = new JTextField();
        textField_user_value1.setBounds(225, 40, 70, 15);
        panel_users.add(textField_user_value1);
        textField_user_value1.setColumns(10);
        textField_user_value1.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                setUserQueryString();
            }
            public void removeUpdate(DocumentEvent e) {
                setUserQueryString();
            }
            public void insertUpdate(DocumentEvent e) {
                setUserQueryString();
            }
        });
        textField_user_value2 = new JTextField();
        textField_user_value2.setColumns(10);
        textField_user_value2.setBounds(225, 70, 70, 15);
        panel_users.add(textField_user_value2);
        textField_user_value2.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                setUserQueryString();
            }
            public void removeUpdate(DocumentEvent e) {
                setUserQueryString();
            }
            public void insertUpdate(DocumentEvent e) {
                setUserQueryString();
            }
        });
        textField_user_value3 = new JTextField();
        textField_user_value3.setColumns(10);
        textField_user_value3.setBounds(225, 100, 70, 15);
        panel_users.add(textField_user_value3);
        textField_user_value3.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                setUserQueryString();
            }
            public void removeUpdate(DocumentEvent e) {
                setUserQueryString();
            }
            public void insertUpdate(DocumentEvent e) {
                setUserQueryString();
            }
        });
        comboBox_select_andor = new JComboBox();
        comboBox_select_andor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setUserQueryString();
            }
        });
        comboBox_select_andor.setModel(new DefaultComboBoxModel(new String[] {"AND", "OR"}));
        comboBox_select_andor.setBounds(100, 130, 190, 20);
        panel_users.add(comboBox_select_andor);
        datePicker_user_membersince = new JXDatePicker();
        datePicker_user_membersince.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setUserQueryString();
            }
        });
        datePicker_user_membersince.getEditor().setEditable(false);
        datePicker_user_membersince.setFormats(new String[] {"yyyy-MM-dd"});
        datePicker_user_membersince.setBounds(95, 6, 200, 23);
        panel_users.add(datePicker_user_membersince);
        JLabel lbl_category = new JLabel("Category");
        lbl_category.setBounds(10, 40, 150, 30);
        lbl_category.setBackground(Color.ORANGE);
        frame.getContentPane().add(lbl_category);
        JLabel lblSubcategory = new JLabel("Subcategory");
        lblSubcategory.setBounds(160, 40, 150, 30);
        frame.getContentPane().add(lblSubcategory);
        JLabel lblCheckin = new JLabel("Checkin");
        lblCheckin.setBounds(310, 40, 150, 30);
        frame.getContentPane().add(lblCheckin);
        JLabel lblReview = new JLabel("Review");
        lblReview.setBounds(460, 40, 150, 30);
        frame.getContentPane().add(lblReview);
        JLabel lblBusiness = new JLabel("Business");
        lblBusiness.setBounds(10, 10, 300, 30);
        lblBusiness.setBackground(Color.PINK);
        frame.getContentPane().add(lblBusiness);
        JLabel lblUsers = new JLabel("Users");
        lblUsers.setBounds(10, 390, 300, 30);
        lblUsers.setBackground(Color.MAGENTA);
        frame.getContentPane().add(lblUsers);
        JLabel lblResult = new JLabel("Result");
        lblResult.setBounds(620, 40, 150, 30);
        frame.getContentPane().add(lblResult);
        JButton btn_query = new JButton("Business Query");
        btn_query.setBounds(392, 602, 120, 50);
        btn_query.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement pst = connection.prepareStatement(queryString);
                    ResultSet rs = pst.executeQuery();
                    table_result.setModel(DbUtils.resultSetToTableModel(rs));
                    pst.close();
                    rs.close();

                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        });
        frame.getContentPane().add(btn_query);
        textArea_query = new JTextArea();
        scrollPane_query = new JScrollPane(textArea_query);
        scrollPane_query.setBounds(320, 420, 290, 170);
        frame.getContentPane().add(scrollPane_query);
        JScrollPane scrollPane_result = new JScrollPane();
        scrollPane_result.setBounds(620, 80, 554, 510);
        frame.getContentPane().add(scrollPane_result);
        table_result = new JTable();
        scrollPane_result.setViewportView(table_result);
        scrollPane_subcategory = new JScrollPane(panel_subcategory);
        scrollPane_subcategory.setBounds(160, 80, 150, 310);
        frame.getContentPane().add(scrollPane_subcategory);
        table_result.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                try {
                    int row = table_result.getSelectedRow();
                    String business_id = (table_result.getModel().getValueAt(row, 0)).toString();
                    String query = "select review.review_id, review.review_date, review.stars, review.votes from review, business \nWHERE business.business_id  = '"+ business_id + "'" +" AND " + "review.business_id = '" + business_id +"'";
                    JOptionPane.showMessageDialog(null,  query);
                    Statement pst = connection.createStatement();
                    pst.executeQuery(query);
                    ResultSet rs = pst.getResultSet();
                    String result = null;
                    if (rs != null) {
                        while(rs.next()) {
                            result += rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getDouble(3) + "  " + rs.getInt(4) +"\n";
                        }
                    } else {
                        result = "no reviews to meet your search!";
                    }
                    JOptionPane.showMessageDialog(null,  result);
                    pst.close();
                    rs.close();
                } catch(Exception e2) {
                    e2.printStackTrace();
                }

            }
        });
    }
    public static boolean isNumeric(String str)
    {
        if(str.equals("") || str == null)
            return false;
        try {
            Integer d = Integer.parseInt(str);
        }
        catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
    private void setBusinessQueryString() {
        searchBusiness = true;
        int criteriaCount = 0;
        queryString = "SELECT * FROM Business\n";
        if((comboBox_checkin_day1.getSelectedIndex() > 0 && comboBox_checkin_hour1.getSelectedIndex() > 0) ||
                (comboBox_checkin_day2.getSelectedIndex() > 0 && comboBox_checkin_hour2.getSelectedIndex() > 0) ||
                isNumeric(textField_checkin_value.getText())) {
            queryString += "JOIN checkin ON Business.business_id = checkin.business_id " + "\n";
        }
        if(datePicker_review_from.getEditor().getValue() != null ||
                datePicker_review_to.getEditor().getValue() != null ||
                isNumeric(textField_review_value1.getText()) ||
                isNumeric(textField_review_value2.getText())) {
            queryString += "JOIN Review ON Business.business_id = Review.business_id " + "\n";
        }
        for(int i = 0; i < categories.size(); i++) {
            if(categories.get(i).isSelected()) {
                queryString += "JOIN CATEGORY ON Business.business_id = CATEGORY.business_id " + "\n";
                break;
            }
        }
        for(int i = 0; i < categories.size(); i++) {
            if(categories.get(i).isSelected()) {
                if(criteriaCount > 0) {
                    queryString += " OR\n ";
                } else {
                    queryString += " WHERE\n ";
                }
                queryString += "category.name = '" + categories.get(i).getText() + "'" + "\n";
                criteriaCount++;
            }
        }
        if(comboBox_checkin_day1.getSelectedIndex() > 0 && comboBox_checkin_day2.getSelectedIndex() > 0 &&
                comboBox_checkin_hour1.getSelectedIndex() > 0 && comboBox_checkin_hour2.getSelectedIndex() > 0) {
            if(criteriaCount > 0) {
                queryString += " AND \n";
            } else {
                queryString += " WHERE \n";
            }
            int from_day = comboBox_checkin_day1.getSelectedIndex() - 1;
            int to_day = comboBox_checkin_day2.getSelectedIndex() - 1;
            int from_hour = comboBox_checkin_hour1.getSelectedIndex() - 1;
            int to_hour = comboBox_checkin_hour2.getSelectedIndex() - 1;
            queryString += "(checkin.time_slot >= " + "'" + from_hour + "-"  + from_day + "'" + " AND checkin.time_slot <= "+ "'" + to_hour + "-"  + to_day + "'" +")" +"\n";
            criteriaCount++;
        }
        if(isNumeric(textField_checkin_value.getText())) {
            if(criteriaCount > 0)
                queryString += " AND \n";
            else
                queryString += " WHERE \n";
            queryString += " checkin.count " + comboBox_checkin_numberofcheckin.getSelectedItem().toString() + textField_checkin_value.getText() +"\n";
            criteriaCount++;
        }
        if(datePicker_review_from.getEditor().getValue() != null &&
                datePicker_review_to.getEditor().getValue() != null) {
            if(criteriaCount > 0) {
                queryString += " AND \n";
            } else {
                queryString += " WHERE \n";
            }
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            queryString += "(review_date >= '" + formater.format(datePicker_review_from.getDate()) + "' AND "
                    + "review_date <= '" + formater.format(datePicker_review_to.getDate()) + "')";
            criteriaCount++;
        } else if (datePicker_review_from.getEditor().getValue() != null) {
            if(criteriaCount > 0) {
                queryString += " AND \n";
            }  else {
                queryString += " WHERE \n";
            }
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            queryString += "review_date >= '" + formater.format(datePicker_review_from.getDate()) + "'" +"\n";
            criteriaCount++;
        } else if (datePicker_review_to.getEditor().getValue() != null) {
            if(criteriaCount > 0) {
                queryString += " AND \n";
            }  else {
                queryString += " WHERE \n";
            }
            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
            queryString += "review_date <= '" + formater.format(datePicker_review_to.getDate()) + "'" +"\n";
            criteriaCount++;
        }
        if(isNumeric(textField_review_value1.getText())) {
            if(criteriaCount > 0) {
                queryString += " AND \n ";
            } else {
                queryString += " WHERE \n";
            }
            queryString += "review.stars " + comboBox_review_star.getSelectedItem().toString() + textField_review_value1.getText() + "\n";
            criteriaCount++;
        }
        if(isNumeric(textField_review_value2.getText())) {
            if(criteriaCount > 0) {
                queryString += " AND \n ";
            } else {
                queryString += " WHERE \n";
            }
            queryString += "review.votes " + comboBox_review_votes.getSelectedItem().toString() + textField_review_value2.getText() + "\n";
            criteriaCount++;
        }
        textArea_query.setText(queryString);
    }
    private void setUserQueryString() {

        int criteriaCount = 0;
        String selector = comboBox_select_andor.getSelectedItem().toString();
        searchBusiness = false;
        queryString = "SELECT * FROM YELP_USER " + "\n";
        if(datePicker_user_membersince.getEditor().getValue() != null) {
            SimpleDateFormat formater = new SimpleDateFormat("YYYY-MM");
            queryString += "WHERE yelping_since >= '" + formater.format(datePicker_user_membersince.getDate()) + "'" +"\n";
            criteriaCount++;
        }
        if(isNumeric(textField_user_value1.getText())) {
            if(criteriaCount > 0)
                queryString += " " + selector + " " + "\n";
            else
                queryString += "WHERE " + "\n";

            queryString += " review_count " + comboBox_user_reviewcount.getSelectedItem().toString() + textField_user_value1.getText() ;
            criteriaCount++;
        }
        if(isNumeric(textField_user_value2.getText())) {
            if(criteriaCount > 0)
                queryString += " " + selector + " " + "\n";
            else
                queryString += "WHERE " + "\n";

            queryString += " friend_count " + comboBox_user_numberoffriend.getSelectedItem().toString() + textField_user_value2.getText() ;
            criteriaCount++;
        }
        if(isNumeric(textField_user_value3.getText())) {
            if(criteriaCount > 0)
                queryString += " " + selector + " " + "\n";
            else
                queryString += "WHERE " + "\n";

            queryString += " average_stars " + comboBox_user_avgstars.getSelectedItem().toString() + textField_user_value3.getText() ;
            criteriaCount++;
        }
        textArea_query.setText(queryString);
    }
}

