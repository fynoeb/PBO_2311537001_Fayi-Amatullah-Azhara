package pert7;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import error.ValidationException;

public class tugasPert7 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<String> dataList = new ArrayList<>();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tugasPert7 frame = new tugasPert7();
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
	public tugasPert7() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtInput = new JTextArea();
		txtInput.setBounds(91, 76, 271, 40);
		contentPane.add(txtInput);
		
		JLabel lblMasukkanData = new JLabel("Masukkan Data");
		lblMasukkanData.setFont(new Font("HP Simplified Jpan", Font.PLAIN, 16));
		lblMasukkanData.setBounds(91, 53, 127, 13);
		contentPane.add(lblMasukkanData);
		
		JTextPane txtOutput = new JTextPane();
		txtOutput.setText("Data: ");
		txtOutput.setFont(new Font("HP Simplified Jpan", Font.PLAIN, 18));
		txtOutput.setBounds(91, 126, 271, 127);
		contentPane.add(txtOutput);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            String inputData = txtInput.getText();
		            if (inputData.isEmpty()) {
		                throw new ValidationException("Data tidak boleh kosong!");
		            }
		            String[] inputs = inputData.split(",");
		            for (String input : inputs) {
		                String trimmedInput = input.trim();
		                if (!trimmedInput.isEmpty()) {
		                    dataList.add(trimmedInput); // Add each trimmed input to the list
		                }
		            }
		            txtOutput.setText("Data: " + String.join(", ", dataList));
		            txtInput.setText(""); // Clear input field after saving
		        } catch (ValidationException ex) {
		            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		btnSimpan.setBounds(372, 78, 85, 38);
		contentPane.add(btnSimpan);
		
		JLabel lblHasilCek = new JLabel("");
		lblHasilCek.setFont(new Font("HP Simplified Jpan", Font.PLAIN, 16));
		lblHasilCek.setBounds(91, 320, 127, 13);
		contentPane.add(lblHasilCek);
		
		JTextArea txtCek = new JTextArea();
		txtCek.setBounds(228, 263, 134, 40);
		contentPane.add(txtCek);
		
		JLabel lblCekDataKe = new JLabel("Cek Data Ke-");
		lblCekDataKe.setFont(new Font("HP Simplified Jpan", Font.PLAIN, 16));
		lblCekDataKe.setBounds(143, 278, 127, 13);
		contentPane.add(lblCekDataKe);
		
		JButton btnCek = new JButton("Cek");
		btnCek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                    String cekInput = txtCek.getText();
                    if (cekInput.isEmpty()) {
                        throw new ValidationException("Masukkan angka yang valid!");
                    }
                    int index = Integer.parseInt(cekInput) - 1; 
                    if (index < 0 || index >= dataList.size()) {
                        throw new ValidationException("Index tidak valid!");
                    }
                    String result = dataList.get(index); 
                    lblHasilCek.setText("index = " + index + " Data = " + result);
                } catch (ValidationException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Masukkan angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);

                }
			}
		});
		btnCek.setBounds(372, 265, 85, 38);
		contentPane.add(btnCek);
		
		
	}
}
