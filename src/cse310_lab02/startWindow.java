package cse310_lab02;

/**
 * 
 */

/**
 * @author sadia
 *
 */

import java.awt.EventQueue;

import javax.swing.*;

import java.sql.*;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionEvent;

public class startWindow {

	private JFrame frame;
	private Connection connect = null;

	/**
	 * Launch the application.
     * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
                    try {
                        startWindow window = new startWindow();
                        window.frame.setVisible(true);
                    } catch (Exception e) {
                    }
                });
	}

	/**
	 * Create the application.
	 */
	public startWindow() {
		initialize();
		connect = dbConnector.connector();		//connecting with database.
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Trajan Pro", Font.PLAIN, 16));
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setLayout(null);
		
		JLabel wellcome_label1 = new JLabel("Wellcome to the module. To register click the \"register\" button,");
		wellcome_label1.setFont(new Font("Trajan Pro", Font.BOLD, 20));
		wellcome_label1.setBounds(10, 30, 794, 130);
		frame.getContentPane().add(wellcome_label1);
		
		JLabel wellcome_label2 = new JLabel("for log in click the \"login\" button \n and if you are a faculty ");
		wellcome_label2.setFont(new Font("Trajan Pro", Font.BOLD, 20));
		wellcome_label2.setBounds(42, 113, 762, 65);
		frame.getContentPane().add(wellcome_label2);
		
		JLabel wellcome_label3 = new JLabel("then click the \"see list\" button.");
		wellcome_label3.setFont(new Font("Trajan Pro", Font.BOLD, 20));
		wellcome_label3.setBounds(186, 170, 411, 46);
		frame.getContentPane().add(wellcome_label3);
		
		JButton btnLogin = new JButton("LOG IN");
		btnLogin.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		btnLogin.addActionListener((ActionEvent arg0) -> {
                    frame.dispose();
                    login.main(null);
                });
		btnLogin.setBounds(580, 313, 200, 50);
		frame.getContentPane().add(btnLogin);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener((ActionEvent arg0) -> {
                    frame.dispose();
                    register.main(null);
                });
		btnRegister.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		btnRegister.setBounds(276, 313, 200, 50);
		frame.getContentPane().add(btnRegister);
		
		JButton btnSeeList = new JButton("SEE LIST");
		btnSeeList.addActionListener((ActionEvent e) -> {
                    frame.dispose();
                    seeList.main(null);
                });
		btnSeeList.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		btnSeeList.setBounds(10, 313, 200, 50);
		frame.getContentPane().add(btnSeeList);
		frame.setBounds(100, 100, 850, 527);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
