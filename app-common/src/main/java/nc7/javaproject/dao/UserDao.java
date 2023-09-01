package nc7.javaproject.dao;

import java.util.List;
import nc7.javaproject.vo.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
  void insert(User user);
  List<User> findAll();
  User findBy(int no);
  User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
  int update(User user);
  int delete(int no);
}
