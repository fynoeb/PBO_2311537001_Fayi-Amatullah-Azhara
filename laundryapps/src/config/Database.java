package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Database {
    private static Connection koneksi;

    public static Connection getConnection() {
        if (koneksi == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");  
                koneksi = DriverManager.getConnection("jdbc:mysql://localhost:3306/laundry_apps?useSSL=false&serverTimezone=UTC", "root", "");
                System.out.println("Koneksi berhasil!");
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Driver tidak ditemukan: " + e.getMessage());
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Koneksi gagal: " + e.getMessage());
            }
        }
        return koneksi;
    }
}