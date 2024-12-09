package table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Costumer;
import model.Order;

public class TableOrder extends AbstractTableModel {
	List<Order> ls;
	private String[] columnNames = {"ID", "Nama", "Tanggal", "Tanggal Pengembalian", "Status", 
			"Total Harga", "Pembayaran", "Status Pembayaran"};
	public TableOrder(List<Order> ls) {
		this.ls = ls;
	}
	
	@Override
	public int getRowCount() {
		return ls.size();
	}
	
	
	@Override
	public int getColumnCount() {
		return 8;
	}
	
	@Override
	public String getColumnName(int colomn) {
		return columnNames[colomn];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return ls.get(rowIndex).getId_order();
		case 1:
			return ls.get(rowIndex).getNama();
		case 2:
			return ls.get(rowIndex).getTanggal();
		case 3:
			return ls.get(rowIndex).getTanggal_kembali();
		case 4:
			return ls.get(rowIndex).getStatus();
		case 5:
			return ls.get(rowIndex).getTotal_harga();
		case 6:
			return ls.get(rowIndex).getPembayaran();
		case 7:
			return ls.get(rowIndex).getStatus_bayar();
		default:
			return null;
		}
	}
	public Order getCostumerAt(int rowIndex) {

        return ls.get(rowIndex);

    }
}
