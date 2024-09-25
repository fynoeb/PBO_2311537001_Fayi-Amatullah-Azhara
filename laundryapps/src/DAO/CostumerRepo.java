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
import model.Costumer;

public class CostumerRepo implements CostumerDAO {
    private Connection connection;

    public CostumerRepo() {
        this.connection = Database.getConnection();
        if (this.connection == null) {
            System.out.println("Koneksi gagal! Periksa konfigurasi database.");
        }
    }

    @Override
    public void save(Costumer costumer) {
        String insert = "INSERT INTO costumer (nama, alamat, noHp) VALUES (?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(insert)) {
            st.setString(1, costumer.getNama());
            st.setString(2, costumer.getAlamat());
            st.setString(3, costumer.getNoHp());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Costumer> show() {
        List<Costumer> costumers = new ArrayList<>();
        String select = "SELECT * FROM costumer";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(select)) {
            while (rs.next()) {
                Costumer costumer = new Costumer();
                costumer.setId(rs.getString("id"));
                costumer.setNama(rs.getString("nama"));
                costumer.setAlamat(rs.getString("alamat"));
                costumer.setNoHp(rs.getString("noHp"));
                costumers.add(costumer);
            }
        } catch (SQLException e) {
            Logger.getLogger(CostumerRepo.class.getName()).log(Level.SEVERE, null, e);
        }
        return costumers;
    }

    @Override
    public void update(Costumer costumer) {
        String update = "UPDATE costumer SET nama = ?, alamat = ?, noHp = ? WHERE id = ?";
        try (PreparedStatement st = connection.prepareStatement(update)) {
            st.setString(1, costumer.getNama());
            st.setString(2, costumer.getAlamat());
            st.setString(3, costumer.getNoHp());
            st.setString(4, costumer.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        String delete = "DELETE FROM costumer WHERE id = ?";
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
