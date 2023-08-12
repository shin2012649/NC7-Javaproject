package nc7.mapp.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.mapp.vo.Comment;

public class MySQLCommentDao implements CommentDao {

    private SqlSessionFactory sqlSessionFactory;

    public MySQLCommentDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(Comment comment) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.insert("nc7.mapp.dao.CommentDao.insert", comment);
        }
    }

    @Override
    public List<Comment> findAll(int filmNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.CommentDao.findAll", filmNo);
        }
    }

    @Override
    public Comment findBy(int commentsNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectOne("nc7.mapp.dao.CommentDao.findBy", commentsNo);
        }
    }

    @Override
    public int update(Comment comment) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.update("nc7.mapp.dao.CommentDao.update", comment);
        }
    }

    @Override
    public int delete(Comment comment) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.delete("nc7.mapp.dao.CommentDao.delete", comment);
        }
    }
}
