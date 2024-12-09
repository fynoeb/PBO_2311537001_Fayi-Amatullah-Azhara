package ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.OrderDetailRepo;
import DAO.OrderRepo;
import DAO.ServiceRepo;
import model.Costumer;
import model.Order;
import model.OrderDetail;
import model.Service;
import table.TableOrderDetail;
import table.TableService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class OrderDetailFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtOrderID;
	private JTextField txtHargaKg;
	private JTextField txtJumlah;
	private JTextField txtTotal;
	private JDateChooser tanggal;
	private JDateChooser tanggal_kembali;
	private JComboBox boxStatus;
	private JLabel lblTotalHargaShow;
	private JLabel lblId_layanan;
	private JComboBox boxPembayaran;
	private JComboBox boxPembayaran_1;
	public String id_ord;
	public static String id_order;
	private JTable tableOrderDetail;
	public int total_harga;
	private JTextField txtCostumer;
	public String tgl;
	public String tgl_kbl;

	OrderRepo opo = new OrderRepo();
	List<Order> ls_opo;
	
	ServiceRepo srv = new ServiceRepo();
	List<Service> ls;
	public String id;
	private JTable tableService;
	
	OrderDetailRepo ord = new OrderDetailRepo();
	List<OrderDetail> ls_ord;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderDetailFrame frame = new OrderDetailFrame();
					frame.setVisible(true);
					frame.loadTable();
					frame.loadTableOrderDetail();
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void reset() {
		txtHargaKg.setText("");
		txtJumlah.setText("");
		txtTotal.setText("");
	}
	
	
	public void resetOrder() {
		txtOrderID.setText("");
		
	}
	public void setTanggal(Date date) {
        tanggal.setDate(date); 
    }
	public void setTanggal_kembali(Date date) {
        tanggal_kembali.setDate(date); 
    }
	
	public void setStatus(String status) {
		boxStatus.setSelectedItem(status);
	}
	public void setLblTotalHargaShow(String total) {
		lblTotalHargaShow.setText("Rp. " + total);
	}
	public void setBoxPembayaran(String pembayaran) {
		boxPembayaran.setSelectedItem(pembayaran);
	}
	public void setBoxPembayaran_1(String pembayaran) {
		boxPembayaran_1.setSelectedItem(pembayaran);
	}
	
	public static void setId_order(String orderId) {
	    id_order = orderId; 

	}
	public void loadTable() {
		ls = srv.show();
		TableService ts = new TableService(ls);
		tableService.setModel(ts);
		tableService.getTableHeader().setVisible(true);
	}
	
	public void loadTableOrderDetail() {
		ls_ord = ord.showById(id_order);
		TableOrderDetail tod = new TableOrderDetail(ls_ord);
		tableOrderDetail.setModel(tod);
		tableOrderDetail.getTableHeader().setVisible(true);
		updateTotalHarga();
		
	}
	
	public void updateTotalHarga() {
        int totalHarga = ord.total(id_order);
        total_harga = totalHarga;
    }
	
	public void setCostumer(Costumer costumer) {
	    txtCostumer.setText(costumer.getNama()); 
	}
	
	public void setOrderID(String id_order) {
	    txtOrderID.setText(id_order); 
	}
	
	public JTextField getTxtOrderId() {
        return txtOrderID; 
    }
	
	public void setTxtCostumer(String cust) {
		txtCostumer.setText(cust);;
	}
	
	public String setTanggal(String tanggal) {
		return tgl = tanggal;
	}
	
	public void resetAll() {
		id_ord = null;
		id_order = null;
		total_harga = 0;
		tgl = null;
		tgl_kbl = null;
		setTxtCostumer("");
		setTanggal("");
		setTanggal_kembali(null);
		setLblTotalHargaShow("");
	}
	
	/**
	 * Create the frame.
	 */
	public OrderDetailFrame() {
		
		setBackground(new Color(246, 246, 246));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(246, 246, 246));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelAkumulasi = new JPanel();
		panelAkumulasi.setBounds(20, 22, 170, 479);
		contentPane.add(panelAkumulasi);
		panelAkumulasi.setLayout(null);
		
		JLabel lblOrderId = new JLabel("Order ID");
		lblOrderId.setBounds(0, 0, 92, 25);
		panelAkumulasi.add(lblOrderId);
		lblOrderId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtOrderID = new JTextField();
		txtOrderID.setBounds(0, 26, 170, 19);
		panelAkumulasi.add(txtOrderID);
		txtOrderID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				id_order = txtOrderID.getText();
			}
		});
		txtOrderID.setColumns(10);
		txtOrderID.setEnabled(false);
		
		JLabel lblPelanggan = new JLabel("Pelanggan");
		lblPelanggan.setBounds(0, 53, 92, 25);
		panelAkumulasi.add(lblPelanggan);
		lblPelanggan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblTanggal = new JLabel("Tanggal");
		lblTanggal.setBounds(0, 111, 81, 25);
		panelAkumulasi.add(lblTanggal);
		lblTanggal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblTanggalPengambilan = new JLabel("Tanggal Pengambilan");
		lblTanggalPengambilan.setBounds(0, 166, 170, 25);
		panelAkumulasi.add(lblTanggalPengambilan);
		lblTanggalPengambilan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(0, 217, 81, 25);
		panelAkumulasi.add(lblStatus);
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		boxStatus = new JComboBox();
		boxStatus.setBounds(0, 244, 170, 21);
		panelAkumulasi.add(boxStatus);
		boxStatus.setModel(new DefaultComboBoxModel(new String[] {"Progress", "Selesai", "Diambil"}));
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(0, 275, 53, 25);
		panelAkumulasi.add(lblTotal);
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
		
		JLabel lblPembayaran = new JLabel("Pembayaran");
		lblPembayaran.setBounds(0, 334, 103, 25);
		panelAkumulasi.add(lblPembayaran);
		lblPembayaran.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		boxPembayaran = new JComboBox();
		boxPembayaran.setBounds(0, 358, 170, 21);
		panelAkumulasi.add(boxPembayaran);
		boxPembayaran.setModel(new DefaultComboBoxModel(new String[] {"Tunai", "Non-Tunai"}));
		
		JLabel lblStatusPembayaran = new JLabel("Status Pembayaran");
		lblStatusPembayaran.setBounds(0, 389, 148, 25);
		panelAkumulasi.add(lblStatusPembayaran);
		lblStatusPembayaran.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		boxPembayaran_1 = new JComboBox();
		boxPembayaran_1.setBounds(0, 414, 170, 21);
		panelAkumulasi.add(boxPembayaran_1);
		boxPembayaran_1.setModel(new DefaultComboBoxModel(new String[] {"Lunas", "Belum Lunas"}));
		
		lblTotalHargaShow = new JLabel("Rp. " + ord.total(txtOrderID.getText()));
		lblTotalHargaShow.setBounds(0, 299, 170, 25);
		panelAkumulasi.add(lblTotalHargaShow);
		lblTotalHargaShow.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTotalHargaShow.setText("Rp. " + total_harga);
		
		tanggal = new JDateChooser();
		tanggal.setBounds(0, 137, 170, 19);
		panelAkumulasi.add(tanggal);
		
		tanggal_kembali = new JDateChooser();
		tanggal_kembali.setBounds(0, 193, 170, 19);
		panelAkumulasi.add(tanggal_kembali);
		
		JButton btnSimpanOrder = new JButton("Simpan");
		btnSimpanOrder.setBounds(0, 445, 85, 34);
		panelAkumulasi.add(btnSimpanOrder);
		btnSimpanOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id_order != null) {
					Order order = new Order();
					order.setId_order(id_order);
					order.setNama(txtCostumer.getText());
					order.setTanggal(tgl);
					order.setTanggal_kembali(tgl_kbl);
					order.setStatus(boxStatus.getSelectedItem().toString());
					order.setTotal_harga(total_harga);
					order.setPembayaran(boxPembayaran.getSelectedItem().toString());
					order.setStatus_bayar(boxPembayaran_1.getSelectedItem().toString());
					opo.save(order);
					OrderFrame.loadTable();
					OrderDetailFrame.this.dispose();
					resetAll();
					
				}else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan disimpan");
				}
			}
		});
		btnSimpanOrder.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnBatalOrder = new JButton("Batal");
		btnBatalOrder.setBounds(85, 445, 85, 34);
		panelAkumulasi.add(btnBatalOrder);
		btnBatalOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBatalOrder.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtCostumer = new JTextField();
		txtCostumer.setBounds(0, 82, 170, 19);
		panelAkumulasi.add(txtCostumer);
		txtCostumer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DialogPelanggan dialog = new DialogPelanggan(OrderDetailFrame.this);
				dialog.setVisible(true);
				dialog.loadTable();
			}
		});
		txtCostumer.setColumns(10);
		tanggal_kembali.getDateEditor().addPropertyChangeListener("date", evt -> {
			if (tanggal_kembali.getDate() != null) {
				SimpleDateFormat sdf_tanggal_kembali = new SimpleDateFormat("yyy-MM-dd");
				tgl_kbl = sdf_tanggal_kembali.format(tanggal_kembali.getDate());
			}
		});
		
		
		tanggal.getDateEditor().addPropertyChangeListener("date", evt -> {
			if (tanggal.getDate() != null) {
			    SimpleDateFormat sdf_tanggal = new SimpleDateFormat("yyyy-MM-dd");
			    tgl = sdf_tanggal.format(tanggal.getDate());
			}
		});
		
		
		
		JPanel panelLayanan = new JPanel();
		panelLayanan.setBounds(220, 22, 556, 193);
		contentPane.add(panelLayanan);
		panelLayanan.setLayout(null);
		
		JLabel lblLayanan = new JLabel("Layanan");
		lblLayanan.setBounds(0, 0, 103, 25);
		panelLayanan.add(lblLayanan);
		lblLayanan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 28, 556, 165);
		panelLayanan.add(scrollPane);
		
		tableService = new JTable();
		tableService.getTableHeader().setVisible(true);
		tableService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = tableService.getSelectedRow();

		        if (selectedRow != -1) {
		            String idLayanan = tableService.getValueAt(selectedRow, 0).toString();
		            txtHargaKg.setText(tableService.getValueAt(selectedRow, 3).toString());
		            lblId_layanan.setText(idLayanan);
		            if (ord.exists(id_order, idLayanan)) {
		                JOptionPane.showMessageDialog(null, "Service sudah ada di order detail.");
		            } else { 
		            }
		        }
			}
		});
		scrollPane.setViewportView(tableService);
		
		JPanel panelCRUD = new JPanel();
		panelCRUD.setBounds(220, 223, 373, 147);
		contentPane.add(panelCRUD);
		panelCRUD.setLayout(null);
		
		JLabel lblHargakg = new JLabel("Harga/Kg");
		lblHargakg.setBounds(0, 0, 103, 25);
		panelCRUD.add(lblHargakg);
		lblHargakg.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtHargaKg = new JTextField();
		txtHargaKg.setBounds(0, 27, 170, 19);
		panelCRUD.add(txtHargaKg);
		txtHargaKg.setColumns(10);
		txtHargaKg.setEnabled(false);
		
		JLabel lblJumlah = new JLabel("Jumlah");
		lblJumlah.setBounds(0, 56, 103, 25);
		panelCRUD.add(lblJumlah);
		lblJumlah.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtJumlah = new JTextField();
		txtJumlah.setBounds(0, 80, 170, 19);
		panelCRUD.add(txtJumlah);
		txtJumlah.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String jumlahText = txtJumlah.getText();
		        String hargaKgText = txtHargaKg.getText();
		        if (!jumlahText.isEmpty() && !hargaKgText.isEmpty()) {
		            try {
		                double jumlah = Double.parseDouble(jumlahText);
		                double hargaKg = Double.parseDouble(hargaKgText);
		                double totalharga = jumlah * hargaKg;
		                System.out.println(totalharga);
		                txtTotal.setText(String.valueOf(totalharga));
		            } catch (NumberFormatException ex) {
		                txtTotal.setText("0"); 
		            }
		        } else {
		            txtTotal.setText("0"); 
		        }
			}
		});
		txtJumlah.setColumns(10);
		
		JLabel lblTotal_1 = new JLabel("Total");
		lblTotal_1.setBounds(180, 56, 103, 25);
		panelCRUD.add(lblTotal_1);
		lblTotal_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		txtTotal = new JTextField();
		txtTotal.setBounds(180, 80, 170, 19);
		panelCRUD.add(txtTotal);
		txtTotal.setColumns(10);
		txtTotal.setEnabled(false);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.setBounds(0, 113, 85, 34);
		panelCRUD.add(btnSimpan);
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id_order != null) {
					if (id_order != null) {
			            String idLayanan = lblId_layanan.getText();
			            if (ord.exists(id_order, idLayanan)) {
			                JOptionPane.showMessageDialog(null, "Layanan ini sudah ada dalam detail order.", "Error", JOptionPane.ERROR_MESSAGE);
			            } else {
			                String jumlahText = txtJumlah.getText();
			                String totalText = txtTotal.getText();

			                if (jumlahText.isEmpty()) {
			                    JOptionPane.showMessageDialog(null, "Jumlah tidak boleh kosong.", "Error", JOptionPane.ERROR_MESSAGE);
			                    return; 
			                }
			                
			                if (totalText.isEmpty()) {
			                    JOptionPane.showMessageDialog(null, "Total tidak boleh kosong.", "Error", JOptionPane.ERROR_MESSAGE);
			                    return;
			                }
			                try {
			                    double jumlah = Double.parseDouble(jumlahText);
			                    double total = Double.parseDouble(totalText);
			                    OrderDetail orderdetail = new OrderDetail();
			                    orderdetail.setId_order(id_order);
			                    orderdetail.setId_layanan(idLayanan);
			                    orderdetail.setJumlah(jumlah);
			                    orderdetail.setTotal(total);
			                    ord.save(orderdetail);
			                    reset();
			                    loadTableOrderDetail();
			                    lblTotalHargaShow.setText("Rp. " + total_harga);
			                } catch (NumberFormatException ex) {
			                    JOptionPane.showMessageDialog(null, "Jumlah dan total harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
			                }
			            }
			        } else {
			            JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan disimpan");
			        }
				}
			}
		});
		btnSimpan.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnUbah = new JButton("Ubah");
		btnUbah.setBounds(97, 113, 85, 34);
		panelCRUD.add(btnUbah);
		btnUbah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id_ord != null) {
					OrderDetail orderdetail = new OrderDetail();
					orderdetail.setId_order(id_order);
					orderdetail.setId_layanan(lblId_layanan.getText());
					orderdetail.setJumlah(Double.parseDouble(txtJumlah.getText()));
					orderdetail.setTotal(Double.parseDouble(txtTotal.getText()));
					orderdetail.setId_order_detail(id_ord);
					ord.update(orderdetail);
					reset();
					loadTableOrderDetail();
					lblTotalHargaShow.setText("Rp. " + total_harga);
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan diedit");
				}
			}
		});
		btnUbah.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.setBounds(193, 113, 85, 34);
		panelCRUD.add(btnHapus);
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id_ord != null) {
					ord.delete(id_ord);
					reset();
					loadTableOrderDetail();
					lblTotalHargaShow.setText("Rp. " + total_harga);
					
				}else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan dihapus");
				}
			}
		});
		btnHapus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnBatal = new JButton("Batal");
		btnBatal.setBounds(288, 113, 85, 34);
		panelCRUD.add(btnBatal);
		btnBatal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		btnBatal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		lblId_layanan = new JLabel("");
		lblId_layanan.setEnabled(false);
		lblId_layanan.setVisible(false);
		lblId_layanan.setBounds(400, 248, 326, 13);
		contentPane.add(lblId_layanan);
		
		JLabel lblId_order = new JLabel("");
		lblId_order.setEnabled(false);
		lblId_order.setVisible(false);
		lblId_order.setBounds(400, 248, 326, 13);
		contentPane.add(lblId_order);
		updateTotalHarga();
		
		JPanel panelDetail = new JPanel();
		panelDetail.setBounds(222, 383, 554, 170);
		contentPane.add(panelDetail);
		panelDetail.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 554, 170);
		panelDetail.add(scrollPane_1);
		
		tableOrderDetail = new JTable();
		tableOrderDetail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				id_ord = tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 0).toString();
				int selectedRow = tableOrderDetail.getSelectedRow();
				if (selectedRow != -1) {
					String idLayanan = tableOrderDetail.getValueAt(selectedRow, 2).toString();
					for (Service service : ls) {
						if (service.getId().equals(idLayanan)) {
							txtHargaKg.setText(String.valueOf(service.getHarga()));
							break;
						}
					}
				}
				
				txtJumlah.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 3).toString());
				txtTotal.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 4).toString());
				id_order = tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 1).toString();
				lblId_layanan.setText(tableOrderDetail.getValueAt(tableOrderDetail.getSelectedRow(), 2).toString());
				
				
			}
		});
		scrollPane_1.setViewportView(tableOrderDetail);
		
		
		
		
		
		
		
		
		
		
	}
	
}
