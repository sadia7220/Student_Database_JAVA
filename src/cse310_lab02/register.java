package cse310_lab02;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;

import java.sql.*;
import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class register extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JLabel lblStudentId;
	private JTextField sid;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private JTextField email;
	private JTextField password;
	private JButton btnRegister;
	private Connection connect = null;
	private static register register_frame;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					register_frame = new register();
					register_frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 851, 531);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		connect = dbConnector.connector();		//connecting with database.
		
		JLabel lblWellcomToRegistation = new JLabel("Wellcome to registation section.");
		lblWellcomToRegistation.setFont(new Font("Trajan Pro", Font.BOLD, 20));
		lblWellcomToRegistation.setBounds(220, 26, 430, 57);
		contentPane.add(lblWellcomToRegistation);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		lblName.setBounds(84, 106, 88, 30);
		contentPane.add(lblName);
		
		name = new JTextField();
		name.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		name.setBounds(249, 107, 307, 30);
		contentPane.add(name);
		name.setColumns(10);
		
		lblStudentId = new JLabel("Student ID: ");
		lblStudentId.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		lblStudentId.setBounds(84, 170, 141, 30);
		contentPane.add(lblStudentId);
		
		sid = new JTextField();
		sid.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		sid.setBounds(249, 170, 307, 30);
		contentPane.add(sid);
		sid.setColumns(10);
		
		lblEmail = new JLabel("Email: ");
		lblEmail.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		lblEmail.setBounds(84, 235, 100, 30);
		contentPane.add(lblEmail);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		lblPassword.setBounds(84, 292, 124, 30);
		contentPane.add(lblPassword);
		
		email = new JTextField();
		email.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		email.setBounds(249, 235, 307, 30);
		contentPane.add(email);
		email.setColumns(10);
		
		password = new JTextField();
		password.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		password.setBounds(249, 291, 307, 30);
		contentPane.add(password);
		password.setColumns(10);
		
		btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					PreparedStatement query = connect.prepareStatement("INSERT INTO user_info (NAME, STUDENT_ID, EMAIL, PASSWORD) VALUES(?,?,?,?)");		//preparing query for daynamic values.
					query.setString(1, name.getText());		//setting values in the query.
					query.setInt(2, Integer.parseInt(sid.getText()));		//setting values in the query.
					query.setString(3, email.getText());		//setting values in the query.
					query.setString(4, password.getText());		//setting values in the query.
					int rowCount = query.executeUpdate();		//executing the query and getting affected row number.
					if(rowCount == 1) {
						register_frame.dispose();
						login.main(null);
					}
				}catch(Exception ex){
					JOptionPane.showMessageDialog(null, "Something went worng while registation. Please try again.");
				}
			}
		});
		btnRegister.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		btnRegister.setBounds(384, 378, 200, 50);
		contentPane.add(btnRegister);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register_frame.dispose();
				startWindow.main(null);
			}
		});
		btnBack.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		btnBack.setBounds(48, 394, 107, 34);
		contentPane.add(btnBack);
	}

}
