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
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import DAO.ServiceRepo;
import model.Service;
import table.TableService;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

public class OrderDetailFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtHarga;
    private JTextField txtJumlah;
    private JTextField txtTotalOrder;
    private JTable tableDetail;
    private JTextField txtOrderId;
    private JTextField txtTanggal; // Should be Date
    private JTextField txtTglAmbil; // Should be Date
    private JTable tableServices;
    private ServiceRepo srv = new ServiceRepo();
    private JTextField txtSatuan;
    private JTextField txtTotal;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                OrderDetailFrame frame = new OrderDetailFrame();
                
                frame.setVisible(true);
                frame.loadTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        });
        
    }

    public OrderDetailFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 736, 721);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panelAkumulasi = new JPanel();
        panelAkumulasi.setBounds(10, 11, 281, 673);
        contentPane.add(panelAkumulasi);
        panelAkumulasi.setLayout(null);

        JLabel lblOrderId = new JLabel("Order ID");
        lblOrderId.setBounds(10, 24, 61, 22);
        panelAkumulasi.add(lblOrderId);

        txtOrderId = new JTextField();
        txtOrderId.setBounds(10, 57, 261, 32);
        panelAkumulasi.add(txtOrderId);
        txtOrderId.setColumns(10);

        JLabel lblPelanggan = new JLabel("Pelanggan");
        lblPelanggan.setBounds(10, 101, 101, 22);
        panelAkumulasi.add(lblPelanggan);

        JComboBox<String> cbPelanggan = new JComboBox<>(new DefaultComboBoxModel<>(new String[]{"Pilih"}));
        cbPelanggan.setBounds(10, 133, 261, 32);
        panelAkumulasi.add(cbPelanggan);

        JLabel lblTanggal = new JLabel("Tanggal");
        lblTanggal.setBounds(10, 176, 101, 22);
        panelAkumulasi.add(lblTanggal);

        txtTanggal = new JTextField();
        txtTanggal.setBounds(10, 207, 261, 32);
        panelAkumulasi.add(txtTanggal);

        JLabel lblTanggalPengambilan = new JLabel("Tanggal Pengambilan");
        lblTanggalPengambilan.setBounds(10, 255, 155, 22);
        panelAkumulasi.add(lblTanggalPengambilan);

        txtTglAmbil = new JTextField();
        txtTglAmbil.setBounds(10, 286, 261, 32);
        panelAkumulasi.add(txtTglAmbil);

        JLabel lblStatus = new JLabel("Status");
        lblStatus.setBounds(10, 332, 101, 22);
        panelAkumulasi.add(lblStatus);

        JComboBox<String> cbStatus = new JComboBox<>(new DefaultComboBoxModel<>(new String[]{"Dalam Antrian", "Sedang Proses", "Selesai"}));
        cbStatus.setBounds(10, 364, 261, 32);
        panelAkumulasi.add(cbStatus);

        JLabel lblTotal_1 = new JLabel("Total");
        lblTotal_1.setBounds(10, 409, 101, 22);
        panelAkumulasi.add(lblTotal_1);

        JLabel lblNilaiTotal = new JLabel("Rp10.000");
        lblNilaiTotal.setBounds(10, 433, 101, 22);
        panelAkumulasi.add(lblNilaiTotal);

        JLabel lblPembayaran = new JLabel("Pembayaran");
        lblPembayaran.setBounds(10, 462, 101, 22);
        panelAkumulasi.add(lblPembayaran);

        JComboBox<String> cbPembayaran = new JComboBox<>(new DefaultComboBoxModel<>(new String[]{"Cash", "QRIS"}));
        cbPembayaran.setBounds(10, 494, 261, 32);
        panelAkumulasi.add(cbPembayaran);

        JLabel lblStatusPembayaran = new JLabel("Status Pembayaran");
        lblStatusPembayaran.setBounds(10, 540, 155, 22);
        panelAkumulasi.add(lblStatusPembayaran);

        JComboBox<String> cbStatusPembayaran = new JComboBox<>(new DefaultComboBoxModel<>(new String[]{"Sudah bayar", "Belum bayar"}));
        cbStatusPembayaran.setBounds(10, 572, 261, 32);
        panelAkumulasi.add(cbStatusPembayaran);

        JButton btnSelesai = new JButton("Selesai");
        btnSelesai.setBounds(10, 640, 89, 22);
        panelAkumulasi.add(btnSelesai);

        JButton btnBatal_1 = new JButton("Batal");
        btnBatal_1.setBounds(182, 640, 89, 23);
        panelAkumulasi.add(btnBatal_1);

        JPanel panelDetail = new JPanel();
        panelDetail.setBounds(301, 405, 416, 279);
        contentPane.add(panelDetail);
        panelDetail.setLayout(null);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(0, 0, 416, 279);
        panelDetail.add(scrollPane_1);

        tableDetail = new JTable();
        scrollPane_1.setViewportView(tableDetail);

        JPanel panelCRUD = new JPanel();
        panelCRUD.setBounds(301, 208, 416, 186);
        contentPane.add(panelCRUD);
        panelCRUD.setLayout(null);

        JLabel lblHarga = new JLabel("Harga/Satuan");
        lblHarga.setBounds(0, 0, 93, 17);
        panelCRUD.add(lblHarga);

        txtHarga = new JTextField();
        txtHarga.setBounds(0, 28, 181, 33);
        panelCRUD.add(txtHarga);
        txtHarga.setColumns(10);

        JLabel lblJumlah = new JLabel("Jumlah");
        lblJumlah.setBounds(0, 73, 93, 17);
        panelCRUD.add(lblJumlah);

        txtJumlah = new JTextField();
        txtJumlah.setBounds(0, 104, 181, 33);
        panelCRUD.add(txtJumlah);

        txtTotalOrder = new JTextField();
        txtTotalOrder.setBounds(225, 104, 181, 33);
        panelCRUD.add(txtTotalOrder);

        JLabel lblTotal = new JLabel("Total");
        lblTotal.setBounds(225, 76, 93, 17);
        panelCRUD.add(lblTotal);

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveOrderDetail();
            }
        });
        btnSimpan.setBounds(0, 148, 89, 23);
        panelCRUD.add(btnSimpan);

        JButton btnBatal = new JButton("Batal");
        btnBatal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });
        btnBatal.setBounds(317, 148, 89, 23);
        panelCRUD.add(btnBatal);

        JButton btnHapus = new JButton("Hapus");
        btnHapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteOrderDetail();
            }
        });
        btnHapus.setBounds(218, 148, 89, 23);
        panelCRUD.add(btnHapus);

        JButton btnUbah = new JButton("Ubah");
        btnUbah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateOrderDetail();
            }
        });
        btnUbah.setBounds(104, 148, 89, 23);
        panelCRUD.add(btnUbah);

        JPanel panelLayanan = new JPanel();
        panelLayanan.setBounds(301, 11, 416, 186);
        contentPane.add(panelLayanan);
        panelLayanan.setLayout(null);

        JLabel lblLayanan = new JLabel("Layanan");
        lblLayanan.setBounds(0, 0, 61, 22);
        panelLayanan.add(lblLayanan);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 22, 416, 164);
        panelLayanan.add(scrollPane);

        tableServices = new JTable();
        scrollPane.setViewportView(tableServices);
        tableServices.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tableServices.getSelectedRow();
                if (selectedRow != -1) {
                    // Assuming columns: Order ID, Service Name, Status, Price, Quantity
                    String orderId = tableServices.getValueAt(selectedRow, 0).toString(); // Order ID
                    String serviceName = tableServices.getValueAt(selectedRow, 1).toString(); // Service Name
                    String status = tableServices.getValueAt(selectedRow, 2).toString(); // Status
                    String price = tableServices.getValueAt(selectedRow, 3).toString(); // Price per unit
                    String quantity = tableServices.getValueAt(selectedRow, 4).toString(); // Quantity
                    String harga_pcs = tableServices.getValueAt(selectedRow, 5).toString(); // Quantity
                    

                    // Populate the fields
                    txtOrderId.setText(orderId);
                    txtTotalOrder.setText(price);
                    lblNilaiTotal.setText(price); // Assuming lblNilaiTotal is a JLabel

                    // Set Harga per unit and Jumlah
                    txtHarga.setText(harga_pcs);
                    txtJumlah.setText(quantity);

                    // Set the status in the cbStatus combo box based on the status value
                    cbStatus.setSelectedItem(status);
                }
            }
        });


        loadTable();
    }

    public void loadTable () {
        List<Service> ls = srv.show();
        TableService tu = new TableService(ls);
        tableServices.setModel(tu);
    }

    private void saveOrderDetail() {
        // Validate inputs
        if (txtHarga.getText().isEmpty() || txtJumlah.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Harga and Jumlah cannot be empty!");
            return;
        }

        // Create a new order detail and save it (implement your own logic)
        // Example: OrderDetail orderDetail = new OrderDetail(...);
        // srv.saveOrderDetail(orderDetail);
        JOptionPane.showMessageDialog(this, "Order detail saved successfully!");
        loadTable();
    }

    private void deleteOrderDetail() {
        int selectedRow = tableDetail.getSelectedRow();
        if (selectedRow >= 0) {
            // Example: srv.deleteOrderDetail(selectedId);
            JOptionPane.showMessageDialog(this, "Order detail deleted successfully!");
            loadTable();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an order detail to delete!");
        }
    }

    private void updateOrderDetail() {
        int selectedRow = tableDetail.getSelectedRow();
        if (selectedRow >= 0) {
            // Example: OrderDetail orderDetail = new OrderDetail(...);
            // srv.updateOrderDetail(orderDetail);
            JOptionPane.showMessageDialog(this, "Order detail updated successfully!");
            loadTable();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an order detail to update!");
        }
    }

    private void resetFields() {
        txtHarga.setText("");
        txtJumlah.setText("");
        txtTotalOrder.setText("");
        // Reset other fields if necessary
    }
}