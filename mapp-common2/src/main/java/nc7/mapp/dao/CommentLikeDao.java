package nc7.mapp.dao;

import nc7.mapp.vo.CommentLike;

public interface CommentLikeDao {
    void insert(CommentLike commentLike);
    boolean exists(int commentsNo, int usersNo);
    int delete(int commentsNo, int usersNo);
}
