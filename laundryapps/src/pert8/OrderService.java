package pert8;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderService {
	private Connection connection;
	
	public OrderService() {
		connection = DatabaseHelper.getConnection();
	}
	public void save(String orderId) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement("INSERT INTO orderservice (id) VALUES (" + orderId + ");");
			st.executeUpdate();
			System.out.println("OrderDetail Inserted Successfully. Data Inserted = " + orderId);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
