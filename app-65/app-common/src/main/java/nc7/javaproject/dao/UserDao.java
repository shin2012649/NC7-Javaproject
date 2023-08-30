package nc7.javaproject.dao;

import java.util.List;
import nc7.javaproject.vo.User;

public interface UserDao {
  void insert(User user);
  List<User> findAll();
  User findBy(int no);
  User findByEmailAndPassword(User u);
  int update(User user);
  int delete(int no);
}
