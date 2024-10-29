package table;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Service;

public class TableService extends AbstractTableModel {
    private List<Service> services;
    private String[] columnNames = {"ID", "Jenis", "Status", "Harga", "Quantity", "Harga per Unit"};

    public TableService(List<Service> services) {
        this.services = services;
    }

    @Override
    public int getRowCount() {
        return services.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Service service = services.get(rowIndex);
        switch (columnIndex) {
            case 0: return service.getId();
            case 1: return service.getJenis();
            case 2: return service.getStatus();
            case 3: return service.getHarga();
            case 4: return service.getQuantity(); // Quantity
            case 5: return service.getHargaPcs(); // Harga per unit
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
