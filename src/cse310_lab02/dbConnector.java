package cse310_lab02;

/**
 * 
 */

/**
 * @author sadia
 *
 */
import java.sql.*;
import javax.swing.*;

public class dbConnector {
	public static Connection connector() {		//connector method for database.
		String url = "jdbc:mysql://localhost:3306/cse_310_lab02";  //database address.
		String userName = "root";
		String password;
            password = "";
		try{
			Connection connect = DriverManager.getConnection(url, userName, password);		//establishing connection with database.
			return connect;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Unable to connect Database..");		//popup massage if connection failed.
		}
		return null;
	}
}
