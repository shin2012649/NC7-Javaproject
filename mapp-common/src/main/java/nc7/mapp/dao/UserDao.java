package nc7.mapp.dao;

import java.util.List;
import nc7.mapp.vo.User;

public interface UserDao {
    void insert(User user);
    List<User> findAll();
    User findBy(int usersNo);
    User findByEmailAndPassword(User user);
    int update(User user);
    int delete(int usersNo);
}
