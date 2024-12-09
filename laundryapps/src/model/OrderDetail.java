package model;

public class OrderDetail {
	String id_order_detail, id_order, id_layanan;
	double jumlah, total;
	public String getId_order_detail() {
		return id_order_detail;
	}
	public void setId_order_detail(String id_order_detail) {
		this.id_order_detail = id_order_detail;
	}
	public String getId_order() {
		return id_order;
	}
	public void setId_order(String id_order) {
		this.id_order = id_order;
	}
	public String getId_layanan() {
		return id_layanan;
	}
	public void setId_layanan(String id_layanan) {
		this.id_layanan = id_layanan;
	}
	public double getJumlah() {
		return jumlah;
	}
	public void setJumlah(double jumlah2) {
		this.jumlah = jumlah2;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
	
	
	
	
}
