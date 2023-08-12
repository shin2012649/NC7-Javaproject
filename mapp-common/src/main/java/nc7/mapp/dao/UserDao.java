package nc7.mapp.dao;

import java.util.List;
import nc7.mapp.vo.User;

public interface UserDao {
    void insert(User user);
    List<User> findAll();
    User findByUsersNo(int usersNo);
    User findByEmail(String email);
    int update(User user);
    int delete(int usersNo);
}
