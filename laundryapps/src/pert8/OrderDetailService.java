package pert8;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailService {
	private Connection connection;
	
	public OrderDetailService() {
		connection = DatabaseHelper.getConnection();
	}
	
	public void save(String orderId, String product) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement("INSERT INTO orderservice (id, product) VALUES (" + orderId + ", " + product + ");");
			st.executeUpdate();
			System.out.println("OrderDetailService Inserted Successfully. Data Inserted = " + orderId + " , " + product);
			
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
