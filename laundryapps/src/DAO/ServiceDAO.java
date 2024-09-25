package DAO;

import java.util.List;
import model.Service;

public interface ServiceDAO {
    void save(Service service);
    List<Service> show();
    void update(Service service);
    void delete(int id);
    void delete(String id);
}
