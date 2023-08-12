package nc7.mapp.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.mapp.vo.CommentLike;

public class MySQLCommentLikeDao implements CommentLikeDao {

    private SqlSessionFactory sqlSessionFactory;

    public MySQLCommentLikeDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(CommentLike commentLike) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.insert("nc7.mapp.dao.CommentLikeDao.insert", commentLike);
        }
    }

    @Override
    public boolean exists(int commentsNo, int usersNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            CommentLike commentLike = new CommentLike();
            commentLike.setCommentsNo(commentsNo);
            commentLike.setUsersNo(usersNo);
            return sqlSession.selectOne("nc7.mapp.dao.CommentLikeDao.exists", commentLike) != null;
        }
    }

    @Override
    public int delete(int commentsNo, int usersNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            CommentLike commentLike = new CommentLike();
            commentLike.setCommentsNo(commentsNo);
            commentLike.setUsersNo(usersNo);
            return sqlSession.delete("nc7.mapp.dao.CommentLikeDao.delete", commentLike);
        }
    }
}
