package model;

public class Service {
    private String id;
    private String jenis;
    private String status;
    private double harga;
    private int quantity; // Menambahkan atribut quantity
    private double hargaPcs; // Menambahkan atribut harga per quantity

    // Getter dan Setter untuk semua atribut
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getJenis() {
        return jenis;
    }
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public double getHarga() {
        return harga;
    }
    public void setHarga(double harga) {
        this.harga = harga;
    }
    public int getQuantity() { // Getter untuk quantity
        return quantity;
    }
    public void setQuantity(int quantity) { // Setter untuk quantity
        this.quantity = quantity;
    }
    public double getHargaPcs() { // Getter untuk harga per quantity
        return hargaPcs;
    }
    public void setHargaPcs(double hargaPcs) { // Setter untuk harga per quantity
        this.hargaPcs = hargaPcs;
    }
}
