package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Laundry Apps");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblNewLabel.setBounds(63, 67, 133, 34);
		contentPane.add(lblNewLabel);
		
		JButton btnPesanan = new JButton("PESANAN");
		btnPesanan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderFrame order = new OrderFrame();
				order.setVisible(true);
				OrderFrame.loadTable();
				
			}
		});
		btnPesanan.setBounds(63, 114, 173, 96);
		contentPane.add(btnPesanan);
		
		JButton btnLayanan = new JButton("LAYANAN");
		btnLayanan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServiceFrame service = new ServiceFrame();
				service.setVisible(true);
				service.loadTable();
				
			}
		});
		btnLayanan.setBounds(248, 114, 173, 96);
		contentPane.add(btnLayanan);
		
		JButton btnPelanggan = new JButton("PELANGGAN");
		btnPelanggan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CostumerFrame cust = new CostumerFrame();
				cust.setVisible(true);
				cust.loadTable();
				
			}
		});
		btnPelanggan.setBounds(433, 114, 173, 96);
		contentPane.add(btnPelanggan);
		
		JButton btnPengguna = new JButton("PENGGUNA");
		btnPengguna.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserFrame user = new UserFrame();
				user.setVisible(true);
				user.loadTable();
				
			}
		});
		btnPengguna.setBounds(63, 239, 173, 96);
		contentPane.add(btnPengguna);
		
		JButton btnLaporan = new JButton("LAPORAN");
		btnLaporan.setBounds(248, 239, 173, 96);
		contentPane.add(btnLaporan);
		
		JButton btnProfile = new JButton("PROFILE");
		btnProfile.setBounds(433, 239, 173, 96);
		contentPane.add(btnProfile);
		
		JButton btnKeluar = new JButton("KELUAR");
		btnKeluar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame login = new LoginFrame();
				login.setVisible(true);
				dispose();
			}
		});
		btnKeluar.setBackground(new Color(242, 140, 247));
		btnKeluar.setBounds(63, 362, 543, 81);
		contentPane.add(btnKeluar);
	}
}
