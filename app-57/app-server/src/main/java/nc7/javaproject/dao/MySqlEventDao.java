package nc7.javaproject.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.javaproject.vo.Event;

public class MySqlEventDao implements EventDao {

  SqlSessionFactory sqlSessionFactory;

  public MySqlEventDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(Event event) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("nc7.javaproject.dao.EventDao.insert", event);
  }

  @Override
  public List<Event> findAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectList("nc7.javaproject.dao.EventDao.findAll");
  }

  @Override
  public Event findBy(int eventId) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectOne("nc7.javaproject.dao.EventDao.findBy", eventId);
  }

  @Override
  public int update(Event event) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("nc7.javaproject.dao.EventDao.update", event);
  }

  @Override
  public int delete(int event) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("nc7.javaproject.dao.EventDao.delete", event);
  }

}
