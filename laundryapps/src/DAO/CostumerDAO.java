package DAO;

import java.util.List;
import model.Costumer;

public interface CostumerDAO {
    void save(Costumer costumer);
    List<Costumer> show();
    void update(Costumer costumer);
    void delete(int id);
    void delete(String id);
}
