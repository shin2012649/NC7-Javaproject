package nc7.mapp.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.mapp.vo.Manager;

import java.util.List;

public class MySQLManagerDao implements ManagerDao {

    private SqlSessionFactory sqlSessionFactory;

    public MySQLManagerDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(Manager manager) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.insert("nc7.mapp.dao.ManagerDao.insert", manager);
        }
    }

    @Override
    public List<Manager> findAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.ManagerDao.findAll");
        }
    }

    @Override
    public Manager findByUserNo(int userNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectOne("nc7.mapp.dao.ManagerDao.findByUserNo", userNo);
        }
    }

    @Override
    public int update(Manager manager) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.update("nc7.mapp.dao.ManagerDao.update", manager);
        }
    }

    @Override
    public int delete(int userNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.delete("nc7.mapp.dao.ManagerDao.delete", userNo);
        }
    }
}
