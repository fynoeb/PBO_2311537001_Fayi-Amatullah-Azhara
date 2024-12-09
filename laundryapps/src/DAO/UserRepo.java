package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import config.Database;
import model.User;

public class UserRepo implements UserDAO {
    private Connection connection;


    public UserRepo() {

        this.connection = Database.getConnection();  
        if (this.connection == null) {
            System.out.println("Koneksi gagal! Periksa konfigurasi database.");
        }
    }

    @Override
    public void save(User user) {
        String insert = "INSERT INTO user (name, username, password) VALUES (?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(insert)) {
            st.setString(1, user.getNama());
            st.setString(2, user.getUsername());
            st.setString(3, user.getPassword());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> show() {
        List<User> users = new ArrayList<>();
        String select = "SELECT * FROM user";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(select)) {
            while (rs.next()) {
                User user = new User(select, select);
                user.setId(rs.getString("id"));
                user.setNama(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            Logger.getLogger(UserRepo.class.getName()).log(Level.SEVERE, null, e);
        }
        return users;
    }

    @Override
    public void update(User user) {
        String update = "UPDATE user SET name = ?, username = ?, password = ? WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(update)) {
            st.setString(1, user.getNama());
            st.setString(2, user.getUsername());
            st.setString(3, user.getPassword());
            st.setString(4, user.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String delete = "DELETE FROM user WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(delete)) {
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        delete(String.valueOf(id));
    }
  }
