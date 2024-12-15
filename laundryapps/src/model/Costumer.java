package model;

public class Costumer {
	String id, nama, alamat, noHp;

	public Costumer(String id, String nama, String alamat, String nohp) {
		this.id = id;
		this.nama = nama;
		this.alamat = alamat;
		this.noHp = nohp;
	}

	public String getId() {
		return id;
	}

	public String getNama() {
		return nama;
	}

	public String getAlamat() {
		return alamat;
	}

	public String getNohp() {
		return noHp;
	}
}
