package cse310_lab02;

import java.awt.EventQueue;

import javax.swing.*;

import java.sql.*;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;


public class seeList extends JFrame {

	private final JPanel contentPane;
	private final JTable table;
	private Connection connect = null;
	private final JButton btnShow;
	private static seeList seelist_frame;

	/**
	 * Launch the application.
     * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
                    try {
                        seelist_frame = new seeList();
                        seelist_frame.setVisible(true);
                    } catch (Exception e) {
                    }
                });
	}

	/**
	 * Create the frame.
	 */
	public seeList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 851, 531);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		connect = dbConnector.connector();		//connecting with database.
		
		JLabel lblInThisSection = new JLabel("In this section you will be able to see students list of a section.");
		lblInThisSection.setFont(new Font("Trajan Pro", Font.BOLD, 20));
		lblInThisSection.setBounds(37, 33, 769, 62);
		contentPane.add(lblInThisSection);
		
		JComboBox secList = new JComboBox();
		secList.setModel(new DefaultComboBoxModel(new String[] {"Section-01", "Section-02", "Section-03"}));
		secList.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		secList.setBounds(47, 95, 159, 34);
		contentPane.add(secList);
		
		DefaultTableModel model = new DefaultTableModel();
		table = new JTable(model);
		table.setBackground(SystemColor.inactiveCaption);
		table.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		table.setBounds(223, 106, 539, 306);
		contentPane.add(table);
		
		btnShow = new JButton("Show");
		btnShow.addActionListener((ActionEvent e) -> {
                    String section = (String) secList.getSelectedItem();
                    section = section.toLowerCase();
                    String n="", m="";
                    try {
                        PreparedStatement qurey = connect.prepareStatement("SELECT name, student_id FROM user_info where section= ?");
                        qurey.setString(1, section);
                        model.addColumn("Student ID");
                        model.addColumn("Name");
                        model.addRow(new Object []{"Student ID", "Name"});
                        ResultSet rs = qurey.executeQuery();
                        while (rs.next()) {
                            n = rs.getString("student_id");
                            m = rs.getString("name");
                            System.out.println(n+":::"+m);
                            model.addRow(new Object []{n,m});
                        }
                    }catch(Exception ex) {
                        JOptionPane.showMessageDialog(null, "Something went wrong.");
                    }
                });
		btnShow.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		btnShow.setBounds(47, 151, 159, 50);
		contentPane.add(btnShow);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener((ActionEvent arg0) -> {
                    seelist_frame.dispose();
                    startWindow.main(null);
                });
		btnBack.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		btnBack.setBounds(24, 437, 89, 23);
		contentPane.add(btnBack);
	}
}
