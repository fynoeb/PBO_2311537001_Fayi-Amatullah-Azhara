package table;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.Costumer;

public class TabelCostumer extends AbstractTableModel {
    private List<Costumer> list;
    private final String[] columns = {"ID", "Nama", "Alamat", "Nomor HP"};

    public TabelCostumer(List<Costumer> list) {
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
        Costumer costumer = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return costumer.getId();
            case 1:
                return costumer.getNama();
            case 2:
                return costumer.getAlamat();
            case 3:
                return costumer.getNohp();
            default:
                return null;
        }
    }

    public void setList(List<Costumer> list) {
        this.list = list;
        fireTableDataChanged();
    }
    
    public Costumer getCostumerAt(int rowIndex) {

        return list.get(rowIndex);

    }
}
