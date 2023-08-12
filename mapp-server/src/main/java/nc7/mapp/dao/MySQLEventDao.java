package nc7.mapp.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.mapp.vo.Event;

import java.util.List;

public class MySQLEventDao implements EventDao {

    private SqlSessionFactory sqlSessionFactory;

    public MySQLEventDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(Event event) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.insert("nc7.mapp.dao.EventDao.insert", event);
        }
    }

    @Override
    public Event findByNo(int eventNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectOne("nc7.mapp.dao.EventDao.findByNo", eventNo);
        }
    }

    @Override
    public List<Event> findAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.EventDao.findAll");
        }
    }

    @Override
    public int update(Event event) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.update("nc7.mapp.dao.EventDao.update", event);
        }
    }

    @Override
    public int delete(int eventNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.delete("nc7.mapp.dao.EventDao.delete", eventNo);
        }
    }
}
