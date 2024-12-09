package table;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import model.OrderDetail;

public class TableOrderDetail extends AbstractTableModel {
	List<OrderDetail> ls;
	private String[] columnNames = {"id_order_detail", "id_order", "id_layanan", "jumlah", "total"};
	public TableOrderDetail(List<OrderDetail> ls) {
		this.ls = ls;
	}
	
	@Override
	public int getRowCount() {
		return ls.size();
	}
	
	@Override
	public int getColumnCount() {
		return 5;
	}
	
	@Override
	public String getColumnName(int colomn) {
		return columnNames[colomn];
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return ls.get(rowIndex).getId_order_detail();
		case 1:
			return ls.get(rowIndex).getId_order();
		case 2:
			return ls.get(rowIndex).getId_layanan();
		case 3:
			return ls.get(rowIndex).getJumlah();
		case 4:
			return ls.get(rowIndex).getTotal();
		default:
			return null;
		}
	}
	
	
}
