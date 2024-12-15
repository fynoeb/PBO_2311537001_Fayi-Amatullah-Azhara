package pert8;

public class SingletonApp {

	public static void main(String[] args) {
		OrderService orderService = new OrderService();
		orderService.save("001");
		
		OrderDetailService orderDetailService = new OrderDetailService();
		orderDetailService.save("001", "Buku A");
		orderDetailService.save("002", "Buku B");
		orderDetailService.save("003", "Buku C");

	}

}
