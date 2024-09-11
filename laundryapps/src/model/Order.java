package model;

import java.time.LocalDate;

public class Order {
	String id, id_costumer, id_service, id_user, status, status_pembayaran;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId_costumer() {
		return id_costumer;
	}
	public void setId_costumer(String id_costumer) {
		this.id_costumer = id_costumer;
	}
	public String getId_service() {
		return id_service;
	}
	public void setId_service(String id_service) {
		this.id_service = id_service;
	}
	public String getId_user() {
		return id_user;
	}
	public void setId_user(String id_user) {
		this.id_user = id_user;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus_pembayaran() {
		return status_pembayaran;
	}
	public void setStatus_pembayaran(String status_pembayaran) {
		this.status_pembayaran = status_pembayaran;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public LocalDate getTanggal() {
		return tanggal;
	}
	public void setTanggal(LocalDate tanggal) {
		this.tanggal = tanggal;
	}
	public LocalDate getTanggal_selesai() {
		return tanggal_selesai;
	}
	public void setTanggal_selesai(LocalDate tanggal_selesai) {
		this.tanggal_selesai = tanggal_selesai;
	}
	Double total;
	LocalDate tanggal, tanggal_selesai;

}
