package table;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import model.User;

public class TableUser extends AbstractTableModel {
    private List<User> list;
    private final String[] columns = {"ID", "Name", "Username", "Password"};

    public TableUser(List<User> list) {
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
        User user = list.get(rowIndex);
        System.out.println("Data user di baris " + rowIndex + ": " + user.getNama());
        switch (columnIndex) {
            case 0:
                return user.getId();
            case 1:
                return user.getNama();
            case 2:
                return user.getUsername();
            case 3:
                return user.getPassword();
            default:
                return null;
        }
    }

    public void setList(List<User> list) {
        this.list = list;
        fireTableDataChanged();
    }
}