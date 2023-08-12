package nc7.mapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class CommentLike implements Serializable {
    private static final long serialVersionUID = 1L;

    private int commentsNo;
    private int usersNo;

    public CommentLike() {
    }

    public CommentLike(int commentsNo, int usersNo) {
        this.commentsNo = commentsNo;
        this.usersNo = usersNo;
    }

    public int getCommentsNo() {
        return commentsNo;
    }

    public void setCommentsNo(int commentsNo) {
        this.commentsNo = commentsNo;
    }

    public int getUsersNo() {
        return usersNo;
    }

    public void setUsersNo(int usersNo) {
        this.usersNo = usersNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentLike that = (CommentLike) o;
        return commentsNo == that.commentsNo &&
                usersNo == that.usersNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentsNo, usersNo);
    }

    @Override
    public String toString() {
        return "CommentLike{" +
                "commentsNo=" + commentsNo +
                ", usersNo=" + usersNo +
                '}';
    }
}
