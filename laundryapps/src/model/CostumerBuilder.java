package model;

public class CostumerBuilder {
	private String id, nama, alamat, nohp;
	
	public CostumerBuilder() {
		// TODO Auto-generated constructor stub
	}
	
	public CostumerBuilder setId(String id) {
		this.id = id;
		return this;
	}
	
	public CostumerBuilder setNama(String Nama) {
		this.nama = Nama;
		return this;
	}
	
	public CostumerBuilder setAlamat(String alamat) {
		this.alamat = alamat;
		return this;
	}
	
	public CostumerBuilder setNohp(String nohp) {
		this.nohp = nohp;
		return this;
	}
	
	public Costumer build() {
		return new Costumer(id, nama, alamat, nohp);
	}

}
