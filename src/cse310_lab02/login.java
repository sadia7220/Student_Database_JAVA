package cse310_lab02;

import java.awt.EventQueue;

import javax.swing.*;

import java.sql.*;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;


public class login extends JFrame {

	private final JPanel contentPane;
	private JTextField email;
	private JTextField password;
	private Connection connect = null;
	private static login login_frame;

	/**
	 * Launch the application.
     * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
                    try {
                        login_frame = new login();
                        login_frame.setVisible(true);
                    } catch (Exception e) {
                    }
                });
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 851, 531);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		connect = dbConnector.connector();		//connecting with database.
		
		JLabel lblWellcomeToLogin = new JLabel("Wellcome to Login section.");
		lblWellcomeToLogin.setFont(new Font("Trajan Pro", Font.BOLD, 20));
		lblWellcomeToLogin.setBounds(232, 30, 366, 78);
		contentPane.add(lblWellcomeToLogin);
		
		JLabel lblEmailId = new JLabel("Email id:");
		lblEmailId.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		lblEmailId.setBounds(190, 196, 118, 29);
		contentPane.add(lblEmailId);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		lblPassword.setBounds(190, 263, 118, 37);
		contentPane.add(lblPassword);
		
		email = new JTextField();
		email.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		email.setBounds(352, 196, 215, 29);
		contentPane.add(email);
		email.setColumns(10);
		
		password = new JTextField();
		password.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		password.setBounds(352, 263, 215, 29);
		contentPane.add(password);
		password.setColumns(10);
		
		JButton btnLogIn = new JButton("LOG IN");
		btnLogIn.addActionListener((ActionEvent e) -> {
                    String emailId = email.getText();
                    String pass = password.getText();
                    int result_count = 0;
                    try {
                        PreparedStatement qury = connect.prepareStatement("SELECT * FROM user_info WHERE email=? and password=?");
                        qury.setString(1, emailId);
                        qury.setString(2, pass);
                        ResultSet rs = qury.executeQuery();
                        if(rs.next()){
                            login_frame.dispose();
                            courses.main(null);
                        }else {
                            login_frame.dispose();
                            register.main(null);					}
                    }catch(Exception ex) {
                        JOptionPane.showMessageDialog(null, "Sorry, Something went wrong...");
                        System.out.println(ex);
                    }
                });
		btnLogIn.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		btnLogIn.setBounds(446, 362, 200, 50);
		contentPane.add(btnLogIn);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener((ActionEvent e) -> {
                    login_frame.dispose();
                    startWindow.main(null);
                });
		btnBack.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		btnBack.setBounds(89, 378, 118, 34);
		contentPane.add(btnBack);
	}

}
