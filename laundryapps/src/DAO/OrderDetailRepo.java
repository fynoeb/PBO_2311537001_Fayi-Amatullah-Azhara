package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import config.Database;
import model.Costumer;
import model.OrderDetail;


public class OrderDetailRepo implements OrderDetailDAO{
	private Connection connection;
	final String insert = "INSERT INTO order_detail (id_order, id_layanan, jumlah, total) VALUES (?,?,?,?);";
	final String select = "SELECT * FROM order_detail;" ;
	final String delete = "DELETE FROM order_detail WHERE id_order_detail = ?;";
	final String update = "UPDATE order_detail SET id_order=?, id_layanan=?, jumlah=?, total=? WHERE id_order_detail=?;";
	final String sum = "SELECT SUM(total) FROM order_detail WHERE id_order = ?";
	final String selectById = "SELECT * FROM order_detail WHERE id_order = ?;" ;
	
	public OrderDetailRepo() {
		connection = Database.getConnection();
		}
	
	@Override
	public void save(OrderDetail orderdetail) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(insert);
			st.setString(1, orderdetail.getId_order());
			st.setString(2, orderdetail.getId_layanan());
			st.setDouble(3, orderdetail.getJumlah());
			st.setDouble(4, orderdetail.getTotal());
			st.executeUpdate();
			
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
	
	public List<OrderDetail> show(){
		List<OrderDetail> ls = null;
		try {
			ls = new ArrayList<OrderDetail>();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(select);
			while(rs.next()) {
				OrderDetail orderdetail = new OrderDetail();
				orderdetail.setId_order_detail(rs.getString("id_order_detail"));
				orderdetail.setId_order(rs.getString("id_order"));
				orderdetail.setId_layanan(rs.getString("id_layanan"));
				orderdetail.setJumlah(rs.getInt("jumlah"));
				orderdetail.setTotal(rs.getInt("total"));
				ls.add(orderdetail);
			}
		}catch(SQLException e) {
			Logger.getLogger(CostumerDAO.class.getName()).log(Level.SEVERE, null, e);
		}
		return ls;
	}
	
	@Override
	public void update (OrderDetail orderdetail) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(update);
			st.setString(1, orderdetail.getId_order());
			st.setString(2, orderdetail.getId_layanan());
			st.setDouble(3, orderdetail.getJumlah());
			st.setDouble(4, orderdetail.getTotal());
			st.setString(5, orderdetail.getId_order_detail());
			st.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public void delete(String id) {
		PreparedStatement st = null;
		try {
			st = connection.prepareStatement(delete);
			st.setString(1, id);
			st.executeUpdate();
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
	@Override
	public int total(String id_order) {
		PreparedStatement st = null;
		ResultSet rs = null;
		int total = 0;
		try {
			st = connection.prepareStatement(sum);
			st.setString(1, id_order);
			rs = st.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				st.close();
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return total;
	}

	@Override
	public List<OrderDetail> showById(String id) {
		List<OrderDetail> ls = null;
		try {
			ls = new ArrayList<OrderDetail>();
			PreparedStatement st = connection.prepareStatement(selectById);
			st.setString(1, id);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				OrderDetail orderdetail = new OrderDetail();
				orderdetail.setId_order_detail(rs.getString("id_order_detail"));
				orderdetail.setId_order(rs.getString("id_order"));
				orderdetail.setId_layanan(rs.getString("id_layanan"));
				orderdetail.setJumlah(rs.getInt("jumlah"));
				orderdetail.setTotal(rs.getInt("total"));
				ls.add(orderdetail);
			}
		}catch(SQLException e) {
			Logger.getLogger(OrderDetailRepo.class.getName()).log(Level.SEVERE, null, e);
		}
		return ls;
	}

	@Override
	public boolean exists(String id_order, String id_layanan) {
		String query = "SELECT COUNT(*) FROM order_detail WHERE id_order = ? AND id_layanan = ?";
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    try {
	        st = connection.prepareStatement(query);
	        st.setString(1, id_order);
	        st.setString(2, id_layanan);
	        rs = st.executeQuery();
	        if (rs.next()) {
	            return rs.getInt(1) > 0; // Return true if count is greater than 0
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (st != null) st.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
		return false;
	}

}
