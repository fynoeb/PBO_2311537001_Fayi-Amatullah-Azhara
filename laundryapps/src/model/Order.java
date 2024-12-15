package model;

public class Order {
	String id_order, nama, tanggal, tanggal_kembali, status, pembayaran, status_bayar;
	int total_harga, id_costumer;
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	
	public String getId_order() {
		return id_order;
	}
	public void setId_order(String id_order) {
		this.id_order = id_order;
	}
	public String getTanggal() {
		return tanggal;
	}
	public void setTanggal(String tanggal) {
		this.tanggal = tanggal;
	}
	public String getTanggal_kembali() {
		return tanggal_kembali;
	}
	public void setTanggal_kembali(String tanggal_kembali) {
		this.tanggal_kembali = tanggal_kembali;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPembayaran() {
		return pembayaran;
	}
	public void setPembayaran(String pembayaran) {
		this.pembayaran = pembayaran;
	}
	public String getStatus_bayar() {
		return status_bayar;
	}
	public void setStatus_bayar(String status_bayar) {
		this.status_bayar = status_bayar;
	}
	public int getTotal_harga() {
		return total_harga;
	}
	public void setTotal_harga(int total_harga) {
		this.total_harga = total_harga;
	}
	public int getId_costumer() {
		return id_costumer;
	}
	public void setId_costumer(int id_costumer) {
		this.id_costumer = id_costumer;
	}
	
	
	
	
	
	
}
