package nc7.mapp.dao;

import java.util.List;
import nc7.mapp.vo.Comment;

public interface CommentDao {
    void insert(Comment comment);
    List<Comment> findAll(int filmNo);
    Comment findBy(int commentsNo);
    int update(Comment comment);
    int delete(Comment comment);
}
