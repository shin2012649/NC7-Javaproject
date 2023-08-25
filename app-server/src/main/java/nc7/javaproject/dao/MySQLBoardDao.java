package nc7.javaproject.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nc7.javaproject.vo.AttachedFile;
import nc7.javaproject.vo.Board;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


public class MySQLBoardDao implements BoardDao {

  SqlSessionFactory sqlSessionFactory;

  public MySQLBoardDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(Board board) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("nc7.javaproject.dao.BoardDao.insert", board);
  }

  @Override
  public List<Board> findAll(int category) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectList("nc7.javaproject.dao.BoardDao.findAll", category);
  }


  @Override
  public Board findBy(int category, int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);

    Map<String,Object> paramMap = new HashMap<>();
    paramMap.put("categoryNo", category);
    paramMap.put("boardNo", no);

    return sqlSession.selectOne("nc7.javaproject.dao.BoardDao.findBy", paramMap);
  }

  @Override
  public int update(Board board) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("nc7.javaproject.dao.BoardDao.update", board);
  }

  @Override
  public int updateCount(Board board) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("nc7.javaproject.dao.BoardDao.updateCount", board);
  }

  @Override
  public int delete(Board board) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("nc7.javaproject.dao.BoardDao.delete", board);
  }

  @Override
  public int insertFiles(Board board) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.insert("nc7.javaproject.dao.BoardDao.insertFiles", board);
  }

  @Override
  public AttachedFile findFileBy(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectOne("nc7.javaproject.dao.BoardDao.findFileBy", no);
  }

  @Override
  public int deleteFile(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("nc7.javaproject.dao.BoardDao.deleteFile", no);
  }

  @Override
  public int deleteFiles(int boardNo) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("nc7.javaproject.dao.BoardDao.deleteFiles", boardNo);
  }

}
