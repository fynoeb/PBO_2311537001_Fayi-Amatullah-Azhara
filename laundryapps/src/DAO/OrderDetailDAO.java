package DAO;

import java.util.List;
import model.OrderDetail;

public interface OrderDetailDAO {
	void save(OrderDetail orderdetail);
	public List<OrderDetail> show();
	public void delete (String id);
	public void update (OrderDetail orderdetail);
	public int total (String id_order);
	public List<OrderDetail> showById(String id);
	public boolean exists(String id_order, String id_layanan);
}
