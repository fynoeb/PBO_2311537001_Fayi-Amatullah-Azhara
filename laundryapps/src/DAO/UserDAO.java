package DAO;

import java.util.List;
import model.User;

public interface UserDAO {
    void save(User user);
    List<User> show();
    void update(User user);
    void delete(int id);
	void delete(String id);
}