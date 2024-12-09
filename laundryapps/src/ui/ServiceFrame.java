package ui;

import java.awt.EventQueue;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import DAO.ServiceRepo;
import model.Service;
import table.TableService;

public class ServiceFrame extends JFrame {

    private JPanel contentPane;
    private JTextField txtHarga;
    private JTable table;
    private JComboBox<String> comboJenis;
    private JComboBox<String> comboSatuan;
    private ServiceRepo serviceRepo;
    private String selectedId = null;

    private final double CUCIPERKG_PRICE = 5000;
    private final double SETRIKAPERHELAI_PRICE = 2000;
    private final double CUCISTRIKA_PRICE = 6000;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ServiceFrame frame = new ServiceFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void reset() {
        comboJenis.setSelectedIndex(0);
        comboSatuan.setModel(new DefaultComboBoxModel(new String[] {"Aktif", "Tidak Aktif"}));
        comboSatuan.setSelectedIndex(0);
        txtHarga.setText("");
        selectedId = null;
    }

    public void loadTable() {
        List<Service> services = serviceRepo.show();
        TableService model = new TableService(services);
        table.setModel(model);
    }

    public ServiceFrame() {
        serviceRepo = new ServiceRepo();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblJenis = new JLabel("Jenis");
        lblJenis.setBounds(10, 20, 80, 25);
        contentPane.add(lblJenis);

        comboJenis = new JComboBox<>(new String[]{"Cuci", "Setrika", "Cuci+Setrika"});
        comboJenis.setBounds(100, 20, 200, 25);
        comboJenis.addActionListener(e -> calculateHarga());
        contentPane.add(comboJenis);

        JLabel lblSatuan = new JLabel("Status");
        lblSatuan.setBounds(10, 60, 80, 25);
        contentPane.add(lblSatuan);

        comboSatuan = new JComboBox<>(new String[]{"Aktif", "Tidak Aktif"});
        comboSatuan.setBounds(100, 60, 100, 25);
        comboSatuan.addActionListener(e -> calculateHarga());
        contentPane.add(comboSatuan);

        JLabel lblHarga = new JLabel("Harga");
        lblHarga.setBounds(10, 100, 80, 25);
        contentPane.add(lblHarga);

        txtHarga = new JTextField();
        txtHarga.setBounds(100, 100, 200, 25);
        contentPane.add(txtHarga);

        JButton btnSave = new JButton("Save");
        btnSave.setBounds(50, 140, 80, 30);
        btnSave.addActionListener(e -> saveService());
        contentPane.add(btnSave);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(140, 140, 80, 30);
        btnUpdate.addActionListener(e -> updateService());
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(230, 140, 80, 30);
        btnDelete.addActionListener(e -> deleteService());
        contentPane.add(btnDelete);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(320, 140, 80, 30);
        btnCancel.addActionListener(e -> reset());
        contentPane.add(btnCancel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 180, 460, 280);
        contentPane.add(scrollPane);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                selectedId = table.getModel().getValueAt(row, 0).toString();
                comboJenis.setSelectedItem(table.getModel().getValueAt(row, 1).toString());
                comboSatuan.setSelectedItem(table.getModel().getValueAt(row, 2).toString());
                txtHarga.setText(table.getModel().getValueAt(row, 3).toString());
            }
        });
        scrollPane.setViewportView(table);

        loadTable();
    }

    private void calculateHarga() {
        String jenis = comboJenis.getSelectedItem().toString();
        String satuan = comboSatuan.getSelectedItem().toString();

        double harga = 0;
        switch (jenis) {
            case "Cuci":
                if (satuan.equals("Kg")) {
                    harga = CUCIPERKG_PRICE;
                }
                break;
            case "Setrika":
                if (satuan.equals("Helai")) {
                    harga = SETRIKAPERHELAI_PRICE;
                }
                break;
            case "Cuci+Setrika":
                if (satuan.equals("Kg")) {
                    harga = CUCISTRIKA_PRICE;
                }
                break;
        }
        txtHarga.setText(String.valueOf(harga));
    }

    private void saveService() {
        Service service = new Service();
        service.setJenis(comboJenis.getSelectedItem().toString());
        service.setStatus(comboSatuan.getSelectedItem().toString());
        double harga = Double.parseDouble(txtHarga.getText());
        service.setHarga(harga);
        serviceRepo.save(service);
        reset();
        loadTable();
        JOptionPane.showMessageDialog(null, "Service saved successfully!");
    }

    private void updateService() {
        if (selectedId != null) {
            Service service = new Service();
            service.setId(selectedId);
            service.setJenis(comboJenis.getSelectedItem().toString());
            service.setStatus(comboSatuan.getSelectedItem().toString());
            double harga = Double.parseDouble(txtHarga.getText());
            service.setHarga(harga);
            serviceRepo.update(service);
            reset();
            loadTable();
            JOptionPane.showMessageDialog(null, "Service updated successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Select a service first!");
        }
    }

    private void deleteService() {
        if (selectedId != null) {
            serviceRepo.delete(selectedId);
            reset();
            loadTable();
            JOptionPane.showMessageDialog(null, "Service deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Select a service first!");
        }
    }
}
