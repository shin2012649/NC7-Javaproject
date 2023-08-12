package nc7.mapp.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.mapp.vo.EventApplication;

import java.util.List;

public class MySQLEventApplicationDao implements EventApplicationDao {

    private SqlSessionFactory sqlSessionFactory;

    public MySQLEventApplicationDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(EventApplication eventApplication) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.insert("nc7.mapp.dao.EventApplicationDao.insert", eventApplication);
        }
    }

    @Override
    public EventApplication findByUserAndEvent(int userNo, int eventNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            EventApplication searchParams = new EventApplication();
            searchParams.setUsersNo(userNo);
            searchParams.setEventNo(eventNo);
            return sqlSession.selectOne("nc7.mapp.dao.EventApplicationDao.findByUserAndEvent", searchParams);
        }
    }

    @Override
    public List<EventApplication> findByUser(int userNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.EventApplicationDao.findByUser", userNo);
        }
    }

    @Override
    public List<EventApplication> findByEvent(int eventNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.EventApplicationDao.findByEvent", eventNo);
        }
    }

    @Override
    public List<EventApplication> findAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.EventApplicationDao.findAll");
        }
    }

    @Override
    public int update(EventApplication eventApplication) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.update("nc7.mapp.dao.EventApplicationDao.update", eventApplication);
        }
    }

    @Override
    public int delete(int userNo, int eventNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            EventApplication searchParams = new EventApplication();
            searchParams.setUsersNo(userNo);
            searchParams.setEventNo(eventNo);
            return sqlSession.delete("nc7.mapp.dao.EventApplicationDao.delete", searchParams);
        }
    }
}
