package nc7.javaproject.dao;

import java.util.List;

import nc7.javaproject.vo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


public class MySQLUserDao implements UserDao {

  SqlSessionFactory sqlSessionFactory;

  public MySQLUserDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(User user) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("nc7.javaproject.dao.UserDao.insert", user);
  }

  @Override
  public List<User> findAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectList("nc7.javaproject.dao.UserDao.findAll");
  }

  @Override
  public User findBy(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectOne("nc7.javaproject.dao.UserDao.findBy", no);
  }

  @Override
  public User findByEmailAndPassword(User user) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectOne("nc7.javaproject.dao.UserDao.findByEmailAndPassword", user);
  }

  @Override
  public int update(User user) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("nc7.javaproject.dao.UserDao.update", user);
  }

  @Override
  public int delete(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("nc7.javaproject.dao.UserDao.delete", no);
  }

}
