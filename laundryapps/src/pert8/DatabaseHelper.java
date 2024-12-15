package pert8;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class DatabaseHelper {
	private static Connection conn;
    private DatabaseHelper() {
    }
    
    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost/laundry_apps", "root", "");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }

        }

        return conn;

    }
	
}
