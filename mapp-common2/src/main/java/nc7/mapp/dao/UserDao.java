package nc7.mapp.dao;

import java.util.List;
import nc7.mapp.vo.User;

public interface UserDao {
    void insert(User user);
    List<User> findAll();
    User findBy(int usersNo);
    User findByEmailAndPassword(User user);
    User findByEmail(String email); // 추가
    int updatePassword(User user);
    int update(User user);
    int delete(int usersNo);
}
