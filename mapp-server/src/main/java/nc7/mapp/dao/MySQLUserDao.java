package nc7.mapp.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.mapp.vo.User;

public class MySQLUserDao implements UserDao {

    private SqlSessionFactory sqlSessionFactory;

    public MySQLUserDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(User user) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.insert("nc7.mapp.dao.UserDao.insert", user);
        }
    }

    @Override
    public User findBy(int usersNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectOne("nc7.mapp.dao.UserDao.findByNo", usersNo);
        }
    }

    @Override
    public User findByEmailAndPassword(User user) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectOne("nc7.mapp.dao.UserDao.findByEmailAndPassword", user);
        }
    }

    @Override
    public int update(User user) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.update("nc7.mapp.dao.UserDao.update", user);
        }
    }

    @Override
    public int delete(int usersNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.delete("nc7.mapp.dao.UserDao.delete", usersNo);
        }
    }

    @Override
    public List<User> findAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.UserDao.findAll");
        }
    }
}
