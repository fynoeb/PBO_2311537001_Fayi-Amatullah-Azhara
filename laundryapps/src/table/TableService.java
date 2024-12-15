package table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Service;

public class TableService extends AbstractTableModel{
	List<Service> ls;
	private String[] columnNames = {"ID", "Jenis", "Status", "Harga"};
	public TableService(List<Service> ls) {
		this.ls = ls;
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return ls.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 0:
			return ls.get(rowIndex).getId();
		case 1:
			return ls.get(rowIndex).getJenis();
		case 2:
			return ls.get(rowIndex).getStatus();
		case 3:
			return ls.get(rowIndex).getHarga();
		default:
			return null;
		}
	}
	
	public String getColumnName(int column) {
        return columnNames[column]; 
    }
	

}
