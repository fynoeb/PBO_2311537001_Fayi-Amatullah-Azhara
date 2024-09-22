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

import DAO.UserRepo;
import model.User;
import table.TableUser;

public class UserFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtName;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JTable table;
    private UserRepo usr;
    private String selectedId = null; 

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserFrame frame = new UserFrame();
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
        txtName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        selectedId = null;
    }

    /**
     * Load data from the database into the table
     */
    public void loadTable() {
        List<User> ls = usr.show();
        TableUser model = new TableUser(ls);
        table.setModel(model);
    }

    /**
     * Create the frame.
     */
    public UserFrame() {
        usr = new UserRepo();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setBounds(10, 20, 80, 25);
        contentPane.add(lblNewLabel);

        txtName = new JTextField();
        txtName.setBounds(100, 20, 200, 25);
        contentPane.add(txtName);
        txtName.setColumns(10);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(10, 60, 80, 25);
        contentPane.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(100, 60, 200, 25);
        contentPane.add(txtUsername);
        txtUsername.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(10, 100, 80, 25);
        contentPane.add(lblPassword);

        txtPassword = new JTextField();
        txtPassword.setBounds(100, 100, 200, 25);
        contentPane.add(txtPassword);
        txtPassword.setColumns(10);


        JButton btnSave = new JButton("Save");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    User user = new User();
                    user.setNama(txtName.getText());
                    user.setUsername(txtUsername.getText());
                    user.setPassword(txtPassword.getText());
                    usr.save(user);
                    reset();
                    loadTable();
                    JOptionPane.showMessageDialog(null, "User saved successfully!");
                }
            }
        });
        btnSave.setBounds(50, 150, 80, 30);
        contentPane.add(btnSave);


        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedId != null && validateInput()) {
                    User user = new User();
                    user.setId(selectedId);
                    user.setNama(txtName.getText());
                    user.setUsername(txtUsername.getText());
                    user.setPassword(txtPassword.getText());
                    usr.update(user);
                    reset();
                    loadTable();
                    JOptionPane.showMessageDialog(null, "User updated successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Select a user first!");
                }
            }
        });
        btnUpdate.setBounds(140, 150, 80, 30);
        contentPane.add(btnUpdate);


        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedId != null) {
                    usr.delete(Integer.parseInt(selectedId));
                    reset();
                    loadTable();
                    JOptionPane.showMessageDialog(null, "User deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Select a user first!");
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
        scrollPane.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        	}
        });
        scrollPane.setBounds(0, 0, 460, 250);
        panel.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        

                table.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        int row = table.getSelectedRow();
                        selectedId = table.getModel().getValueAt(row, 0).toString();
                        txtName.setText(table.getModel().getValueAt(row, 1).toString());
                        txtUsername.setText(table.getModel().getValueAt(row, 2).toString());
                        txtPassword.setText(table.getModel().getValueAt(row, 3).toString());
                    }
                });


        loadTable();
    }

    /**
     * Validate user input
     */
    private boolean validateInput() {
        if (txtName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name cannot be empty!");
            return false;
        }
        if (txtUsername.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty!");
            return false;
        }
        if (txtPassword.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password cannot be empty!");
            return false;
        }
        return true;
    }
}