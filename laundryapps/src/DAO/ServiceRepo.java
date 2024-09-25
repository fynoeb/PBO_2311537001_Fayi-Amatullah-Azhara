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
import model.Service;

public class ServiceRepo implements ServiceDAO {
    private Connection connection;

    public ServiceRepo() {
        this.connection = Database.getConnection();  
        if (this.connection == null) {
            System.out.println("Koneksi gagal! Periksa konfigurasi database.");
        }
    }

    @Override
    public void save(Service service) {
        String insert = "INSERT INTO service (jenis, status, harga) VALUES (?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(insert)) {
            st.setString(1, service.getJenis());
            st.setString(2, service.getStatus());
            st.setDouble(3, service.getHarga());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Service> show() {
        List<Service> services = new ArrayList<>();
        String select = "SELECT * FROM service";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(select)) {
            while (rs.next()) {
                Service service = new Service();
                service.setId(rs.getString("id"));
                service.setJenis(rs.getString("jenis"));
                service.setStatus(rs.getString("status"));
                service.setHarga(rs.getDouble("harga"));
                services.add(service);
            }
        } catch (SQLException e) {
            Logger.getLogger(ServiceRepo.class.getName()).log(Level.SEVERE, null, e);
        }
        return services;
    }

    @Override
    public void update(Service service) {
        String update = "UPDATE service SET jenis = ?, status = ?, harga = ? WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(update)) {
            st.setString(1, service.getJenis());
            st.setString(2, service.getStatus());
            st.setDouble(3, service.getHarga());
            st.setString(4, service.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String delete = "DELETE FROM service WHERE id = ?";
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
