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

import DAO.ServiceRepo;
import model.Service;
import table.TableService;

public class ServiceFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtJenis;
    private JTextField txtStatus;
    private JTextField txtHarga;
    private JTable table;
    private ServiceRepo serviceRepo;
    private String selectedId = null;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ServiceFrame frame = new ServiceFrame();
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
        txtJenis.setText("");
        txtStatus.setText("");
        txtHarga.setText("");
        selectedId = null;
    }

    /**
     * Load data from the database into the table
     */
    public void loadTable() {
        List<Service> ls = serviceRepo.show();
        TableService model = new TableService(ls);
        table.setModel(model);
    }

    /**
     * Create the frame.
     */
    public ServiceFrame() {
        serviceRepo = new ServiceRepo();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblJenis = new JLabel("Jenis");
        lblJenis.setBounds(10, 20, 80, 25);
        contentPane.add(lblJenis);

        txtJenis = new JTextField();
        txtJenis.setBounds(100, 20, 200, 25);
        contentPane.add(txtJenis);
        txtJenis.setColumns(10);

        JLabel lblStatus = new JLabel("Status");
        lblStatus.setBounds(10, 60, 80, 25);
        contentPane.add(lblStatus);

        txtStatus = new JTextField();
        txtStatus.setBounds(100, 60, 200, 25);
        contentPane.add(txtStatus);
        txtStatus.setColumns(10);

        JLabel lblHarga = new JLabel("Harga");
        lblHarga.setBounds(10, 100, 80, 25);
        contentPane.add(lblHarga);

        txtHarga = new JTextField();
        txtHarga.setBounds(100, 100, 200, 25);
        contentPane.add(txtHarga);
        txtHarga.setColumns(10);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    Service service = new Service();
                    service.setJenis(txtJenis.getText());
                    service.setStatus(txtStatus.getText());
                    service.setHarga(Double.parseDouble(txtHarga.getText()));
                    serviceRepo.save(service);
                    reset();
                    loadTable();
                    JOptionPane.showMessageDialog(null, "Service saved successfully!");
                }
            }
        });
        btnSave.setBounds(50, 150, 80, 30);
        contentPane.add(btnSave);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedId != null && validateInput()) {
                    Service service = new Service();
                    service.setId(selectedId);
                    service.setJenis(txtJenis.getText());
                    service.setStatus(txtStatus.getText());
                    service.setHarga(Double.parseDouble(txtHarga.getText()));
                    serviceRepo.update(service);
                    reset();
                    loadTable();
                    JOptionPane.showMessageDialog(null, "Service updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Select a service first!");
                }
            }
        });
        btnUpdate.setBounds(140, 150, 80, 30);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedId != null) {
                    serviceRepo.delete(Integer.parseInt(selectedId));
                    reset();
                    loadTable();
                    JOptionPane.showMessageDialog(null, "Service deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Select a service first!");
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
                txtJenis.setText(table.getModel().getValueAt(row, 1).toString());
                txtStatus.setText(table.getModel().getValueAt(row, 2).toString());
                txtHarga.setText(table.getModel().getValueAt(row, 3).toString());
            }
        });

        loadTable();
    }

    /**
     * Validate user input
     */
    private boolean validateInput() {
        if (txtJenis.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Jenis cannot be empty!");
            return false;
        }
        if (txtStatus.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Status cannot be empty!");
            return false;
        }
        if (txtHarga.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Harga cannot be empty!");
            return false;
        }
        return true;
    }
}
