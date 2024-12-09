package ui;

import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import DAO.CostumerRepo;
import model.Costumer;
import table.TabelCostumer;

public class CostumerFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNama;
    private JTextField txtAlamat;
    private JTextField txtNoHp;
    private JTable table;
    private CostumerRepo costumerRepo;
    private String selectedId = null;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CostumerFrame frame = new CostumerFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Reset the input fields
     */
    public void reset() {
        txtNama.setText("");
        txtAlamat.setText("");
        txtNoHp.setText("");
        selectedId = null;
    }

    /**
     * Load data from the database into the table
     */
    public void loadTable() {
        List<Costumer> ls = costumerRepo.show();
        TabelCostumer model = new TabelCostumer(ls);
        table.setModel(model);
    }

    /**
     * Create the frame.
     */
    public CostumerFrame() {
        costumerRepo = new CostumerRepo();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNama = new JLabel("Nama");
        lblNama.setBounds(10, 20, 80, 25);
        contentPane.add(lblNama);

        txtNama = new JTextField();
        txtNama.setBounds(100, 20, 200, 25);
        contentPane.add(txtNama);
        txtNama.setColumns(10);

        JLabel lblAlamat = new JLabel("Alamat");
        lblAlamat.setBounds(10, 60, 80, 25);
        contentPane.add(lblAlamat);

        txtAlamat = new JTextField();
        txtAlamat.setBounds(100, 60, 200, 25);
        contentPane.add(txtAlamat);
        txtAlamat.setColumns(10);

        JLabel lblNoHp = new JLabel("No HP");
        lblNoHp.setBounds(10, 100, 80, 25);
        contentPane.add(lblNoHp);

        txtNoHp = new JTextField();
        txtNoHp.setBounds(100, 100, 200, 25);
        contentPane.add(txtNoHp);
        txtNoHp.setColumns(10);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    Costumer costumer = new Costumer();
                    costumer.setNama(txtNama.getText());
                    costumer.setAlamat(txtAlamat.getText());
                    costumer.setNoHp(txtNoHp.getText());
                    costumerRepo.save(costumer);
                    reset();
                    loadTable();
                    JOptionPane.showMessageDialog(null, "Costumer saved successfully!");
                }
            }
        });
        btnSave.setBounds(50, 150, 80, 30);
        contentPane.add(btnSave);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedId != null && validateInput()) {
                    Costumer costumer = new Costumer();
                    costumer.setId(selectedId);
                    costumer.setNama(txtNama.getText());
                    costumer.setAlamat(txtAlamat.getText());
                    costumer.setNoHp(txtNoHp.getText());
                    costumerRepo.update(costumer);
                    reset();
                    loadTable();
                    JOptionPane.showMessageDialog(null, "Costumer updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Select a costumer first!");
                }
            }
        });
        btnUpdate.setBounds(140, 150, 80, 30);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedId != null) {
                    costumerRepo.delete(Integer.parseInt(selectedId));
                    reset();
                    loadTable();
                    JOptionPane.showMessageDialog(null, "Costumer deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Select a costumer first!");
                }
            }
        });
        btnDelete.setBounds(230, 150, 80, 30);
        contentPane.add(btnDelete);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        btnCancel.setBounds(320, 150, 80, 30);
        contentPane.add(btnCancel);

        JPanel panel = new JPanel();
        panel.setBounds(10, 200, 460, 250);
        contentPane.add(panel);
        panel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 460, 250);
        panel.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                selectedId = table.getModel().getValueAt(row, 0).toString();
                txtNama.setText(table.getModel().getValueAt(row, 1).toString());
                txtAlamat.setText(table.getModel().getValueAt(row, 2).toString());
                txtNoHp.setText(table.getModel().getValueAt(row, 3).toString());
            }
        });

        loadTable();
    }

    /**
     * Validate user input
     */
    private boolean validateInput() {
        if (txtNama.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nama cannot be empty!");
            return false;
        }
        if (txtAlamat.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Alamat cannot be empty!");
            return false;
        }
        if (txtNoHp.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No HP cannot be empty!");
            return false;
        }
        return true;
    }
}
