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
import javax.swing.JComboBox;
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
    private JTextField txtQuantity;
    private JTextField txtHarga;
    private JTable table;
    private JComboBox<String> comboJenis;
    private JComboBox<String> comboSatuan;
    private JComboBox<String> comboStatus; 
    private ServiceRepo serviceRepo;
    private String selectedId = null;

   
    private final double PAKAIAN_PRICE_PER_KG = 5000;
    private final double SEPREI_PRICE_PER_HELAI = 35000;
    private final double JAS_PRICE_PER_HELAI = 20000;
    private final double GAUN_PRICE_PER_HELAI = 50000;
    private final double HANDUK_PRICE_PER_HELAI = 10000; 

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
     * Reset the input fields.
     */
    public void reset() {
        comboJenis.setSelectedIndex(0);
        txtQuantity.setText("");
        comboSatuan.removeAllItems();
        comboSatuan.addItem("Kg");
        txtHarga.setText("");
        comboStatus.setSelectedIndex(0); 
        selectedId = null;
    }

    /**
     * Load data from the database into the table.
     */
    public void loadTable() {
        List<Service> ls = serviceRepo.show(); // Mengambil data dari database
        TableService model = new TableService(ls); // Membuat model tabel dari list service
        table.setModel(model); // Mengatur model tabel untuk JTable
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

        comboJenis = new JComboBox<>(new String[]{"Pakaian", "Seprei", "Handuk", "Jas", "Gaun"});
        comboJenis.setBounds(100, 20, 200, 25);
        comboJenis.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateSatuanAndCalculateHarga();
            }
        });
        contentPane.add(comboJenis);

        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setBounds(10, 60, 80, 25);
        contentPane.add(lblQuantity);

        txtQuantity = new JTextField();
        txtQuantity.setBounds(100, 60, 100, 25);
        txtQuantity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateHarga();  
            }
        });
        contentPane.add(txtQuantity);
        txtQuantity.setColumns(10);

        comboSatuan = new JComboBox<>();
        comboSatuan.setBounds(210, 60, 100, 25);
        comboSatuan.addItem("Kg");
        contentPane.add(comboSatuan);

        JLabel lblStatus = new JLabel("Status");
        lblStatus.setBounds(10, 100, 80, 25);
        contentPane.add(lblStatus);

        // ComboBox for status
        comboStatus = new JComboBox<>(new String[]{"Dalam Antrian", "Sedang Diproses", "Selesai"});
        comboStatus.setBounds(100, 100, 200, 25);
        contentPane.add(comboStatus);

        JLabel lblHarga = new JLabel("Harga");
        lblHarga.setBounds(10, 140, 80, 25);
        contentPane.add(lblHarga);

        txtHarga = new JTextField();
        txtHarga.setBounds(100, 140, 200, 25);
        txtHarga.setEditable(false);
        contentPane.add(txtHarga);
        txtHarga.setColumns(10);

        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    Service service = new Service();
                    service.setJenis(comboJenis.getSelectedItem().toString());
                    service.setStatus(comboStatus.getSelectedItem().toString());
                    
                    int quantity = Integer.parseInt(txtQuantity.getText());
                    service.setQuantity(quantity);

                    double totalHarga = Double.parseDouble(txtHarga.getText());
                    service.setHarga(totalHarga);
                    
                    double hargaPerPcs = quantity > 0 ? totalHarga / quantity : 0; // Calculate harga per pcs
                    service.setHargaPcs(hargaPerPcs);

                    serviceRepo.save(service);
                    reset();
                    loadTable();
                    JOptionPane.showMessageDialog(null, "Service saved successfully!");
                }
            }
        });
        btnSave.setBounds(50, 180, 80, 30);
        contentPane.add(btnSave);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedId != null && validateInput()) {
                    Service service = new Service();
                    service.setId(selectedId);
                    service.setJenis(comboJenis.getSelectedItem().toString());
                    service.setStatus(comboStatus.getSelectedItem().toString());

                    int quantity = Integer.parseInt(txtQuantity.getText());
                    service.setQuantity(quantity);

                    double totalHarga = Double.parseDouble(txtHarga.getText());
                    service.setHarga(totalHarga);

                    double hargaPerPcs = quantity > 0 ? totalHarga / quantity : 0; // Calculate harga per pcs
                    service.setHargaPcs(hargaPerPcs);

                    serviceRepo.update(service);
                    reset();
                    loadTable();
                    JOptionPane.showMessageDialog(null, "Service updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Select a service first!");
                }
            }
        });
        btnUpdate.setBounds(140, 180, 80, 30);
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
        btnDelete.setBounds(230, 180, 80, 30);
        contentPane.add(btnDelete);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        btnCancel.setBounds(320, 180, 80, 30);
        contentPane.add(btnCancel);

        JPanel panel = new JPanel();
        panel.setBounds(10, 220, 460, 250);
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
                comboJenis.setSelectedItem(table.getModel().getValueAt(row, 1).toString());
                comboStatus.setSelectedItem(table.getModel().getValueAt(row, 2).toString());
                txtHarga.setText(table.getModel().getValueAt(row, 3).toString());
                txtQuantity.setText(table.getModel().getValueAt(row, 4).toString()); // Ambil quantity
            }
        });

        loadTable();
    }

    private void updateSatuanAndCalculateHarga() {
        String jenis = comboJenis.getSelectedItem().toString();
        comboSatuan.removeAllItems();
        
        if (jenis.equals("Pakaian")) {
            comboSatuan.addItem("Kg");
            comboSatuan.setSelectedItem("Kg"); 
        } else {
            comboSatuan.addItem("Helai");
            comboSatuan.setSelectedItem("Helai"); 
        }
        
        calculateHarga();  
    }

    private void calculateHarga() {
        String jenis = comboJenis.getSelectedItem().toString();
        String quantityStr = txtQuantity.getText();
        String satuan = comboSatuan.getSelectedItem().toString(); 

     
        if (quantityStr.isEmpty()) {
            txtHarga.setText("0");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid quantity!");
            return;
        }

        double harga = 0;

     
        switch (jenis) {
            case "Pakaian":
                if (satuan.equals("Kg")) {
                    harga = quantity * PAKAIAN_PRICE_PER_KG;
                }
                break;
            case "Seprei":
                if (satuan.equals("Helai")) {
                    harga = quantity * SEPREI_PRICE_PER_HELAI;
                }
                break;
            case "Handuk":
                if (satuan.equals("Helai")) {
                    harga = quantity * HANDUK_PRICE_PER_HELAI; 
                }
                break;
            case "Jas":
                if (satuan.equals("Helai")) {
                    harga = quantity * JAS_PRICE_PER_HELAI;
                }
                break;
            case "Gaun":
                if (satuan.equals("Helai")) {
                    harga = quantity * GAUN_PRICE_PER_HELAI;
                }
                break;
            default:
                break;
        }

        txtHarga.setText(String.valueOf(harga));
    }

    private boolean validateInput() {
 
        if (txtQuantity.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Quantity cannot be empty!");
            return false;
        }

 
        try {
            Integer.parseInt(txtQuantity.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Quantity must be a number!");
            return false;
        }

      
        if (txtHarga.getText().isEmpty() || txtHarga.getText().equals("0")) {
            JOptionPane.showMessageDialog(null, "Harga must be calculated correctly!");
            return false;
        }

        return true;
    }
}