package DAO;

import java.util.List;

import model.Costumer;
import model.Order;


public interface OrderDAO {
	public List<Order> show();
	public void delete (String id);
	void save(Order order);
}
