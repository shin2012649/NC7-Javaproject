package nc7.javaproject.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.javaproject.vo.Participant;


public class MySQLParticipantDao implements ParticipantDao {
  
  SqlSessionFactory sqlSessionFactory;

  
  public MySQLParticipantDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(Participant participant) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("nc7.javaproject.dao.ParticipantDao.insert", participant);
  }

  @Override
  public List<Participant> findAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectList("nc7.javaproject.dao.ParticipantDao.findAll");
   }

  @Override
  public Participant findBy(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectOne("nc7.javaproject.dao.ParticipantDao.findBy", no);
      }
  
  @Override
  public Participant findByNameAndPassword(Participant participant) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectOne("nc7.javaproject.dao.ParticipantDao.findByNameAndPassword", participant);
      }
      
      

  @Override
  public int update(Participant participant) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("nc7.javaproject.dao.ParticipantDao.update", participant);
  }


  @Override
  public int delete(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("nc7.javaproject.dao.ParticipantDao.delete", no);
  }

}