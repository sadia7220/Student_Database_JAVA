package cse310_lab02;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.sql.SQLException;


public class courses extends JFrame {

	private JPanel contentPane;
	private JTable secList;
	private Connection connect = null;
	private static courses courses;
	
	/**
	 * Launch the application.
     * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
                    try {
                        courses = new courses();
                        courses.setVisible(true);
                    } catch (Exception e) {
                    }
                });
	}

	/**
	 * Create the frame.
	 */
	public courses() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 851, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		connect = dbConnector.connector();		//connecting with database.
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 146, 743, 157);
		contentPane.add(scrollPane);
		
		DefaultTableModel model = new DefaultTableModel();
		secList = new JTable(model);
		//printing section state in the table.
		String n="", m="", o="";
		int x=0;
		try {
				model.addColumn("Section No");
				model.addColumn("Class Time");
				model.addColumn("Seat Status");
				PreparedStatement qurey = connect.prepareStatement("SELECT COUNT(name) num FROM user_info WHERE section= 'section-01'");  //for sec-01
				ResultSet rs = qurey.executeQuery();
				n = "1.Section-01";
				m = "Sunday:�12:30�pm���1:00�pm";
				rs.next();
				x = 5- rs.getInt("num");
				o = x+" Seats�Remaining";
				model.addRow(new Object []{n,m,o});
				PreparedStatement qurey1 = connect.prepareStatement("SELECT COUNT(name) num FROM user_info WHERE section= 'section-02'");  //for sec-02
				ResultSet rs1 = qurey1.executeQuery();
				n = "2.Section-02";
				m = "�Sunday:�2:30�pm���4:00�pm";
				rs1.next();
				x = 5- rs1.getInt("num");
				o = x+" Seats�Remaining";
				model.addRow(new Object []{n,m,o});
				PreparedStatement qurey2 = connect.prepareStatement("SELECT COUNT(name) num FROM user_info WHERE section= 'section-03'");  //for sec-03
				ResultSet rs2 = qurey2.executeQuery();
					n = "2.Section-02";
					m = "�Sunday:�2:30�pm���4:00�pm";
					rs2.next();
					x = 5- rs2.getInt("num");
					o = x+" Seats�Remaining";
					model.addRow(new Object []{n,m,o});	
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, "Something went wrong.");
		}
		
		secList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		scrollPane.setViewportView(secList);
		secList.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		
		JLabel lblAllTheSection = new JLabel("All the section and their details are given bellow...");
		lblAllTheSection.setFont(new Font("Trajan Pro", Font.BOLD, 20));
		lblAllTheSection.setBounds(84, 33, 652, 39);
		contentPane.add(lblAllTheSection);
		
		JButton btnAddCourse = new JButton("Add Course");
		btnAddCourse.addActionListener((ActionEvent arg0) -> {
                    int rowNo = secList.getSelectedRow();
                    System.out.println(rowNo);
                    try {
                        boolean b= false;
                        switch (rowNo) {
                            case 0:
                                {
                                    PreparedStatement qurey = connect.prepareStatement("SELECT COUNT(name) num FROM user_info WHERE section= 'section-01'");  //for sec-01
                                    ResultSet rs = qurey.executeQuery();
                                    rs.next();
                                    int x1 = 5 - rs.getInt("num");
                                    b = x1 > 0;
                                    break;
                                }
                            case 1:
                                {
                                    PreparedStatement qurey = connect.prepareStatement("SELECT COUNT(name) num FROM user_info WHERE section= 'section-02'");  //for sec-01
                                    ResultSet rs = qurey.executeQuery();
                                    rs.next();
                                    int x2 = 5- rs.getInt("num");
                                    b = x2 > 0;
                                    break;
                                }
                            case 2:
                                {
                                    PreparedStatement qurey = connect.prepareStatement("SELECT COUNT(name) num FROM user_info WHERE section= 'section-03'");  //for sec-01
                                    ResultSet rs = qurey.executeQuery();
                                    rs.next();
                                    int x3 = 5- rs.getInt("num");
                                    b = x3 > 0;
break;
                                }
                            default:
                                break;
                        }
                        if(b) {
                            JOptionPane.showMessageDialog(null, "Successful");
                            courses.dispose();
                            startWindow.main(null);
                        } else {
                            JOptionPane.showMessageDialog(null, "Section full. Please select another section.");
                        }
                    }catch(SQLException | HeadlessException ex) {
                        JOptionPane.showMessageDialog(null, "Something went wrong");
                    }
                });
		btnAddCourse.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		btnAddCourse.setBounds(527, 362, 179, 50);
		contentPane.add(btnAddCourse);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener((ActionEvent arg0) -> {
                    courses.dispose();
                    login.main(null);
                });
		btnBack.setFont(new Font("Trajan Pro", Font.BOLD, 18));
		btnBack.setBounds(48, 385, 125, 27);
		contentPane.add(btnBack);
	}
}
