package table;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Service;

public class TableService extends AbstractTableModel {
    private List<Service> list;
    private final String[] columns = {"ID", "Jenis", "Status", "Harga"};

    public TableService(List<Service> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Service service = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return service.getId();
            case 1:
                return service.getJenis();
            case 2:
                return service.getStatus();
            case 3:
                return service.getHarga();
            default:
                return null;
        }
    }

    public void setList(List<Service> list) {
        this.list = list;
        fireTableDataChanged();
    }
}
