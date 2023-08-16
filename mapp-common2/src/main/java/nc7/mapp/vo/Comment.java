package nc7.mapp.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    private int commentsNo;
    private int usersNo;
    private int filmsNo;
    private String contents;
    private LocalDateTime addAt;
    private String state;

    public Comment() {
    }

    public Comment(int commentsNo, int usersNo, int filmsNo, String contents, LocalDateTime addAt, String state) {
        this.commentsNo = commentsNo;
        this.usersNo = usersNo;
        this.filmsNo = filmsNo;
        this.contents = contents;
        this.addAt = addAt;
        this.state = state;
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

    public int getFilmsNo() {
        return filmsNo;
    }

    public void setFilmsNo(int filmsNo) {
        this.filmsNo = filmsNo;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public LocalDateTime getAddAt() {
        return addAt;
    }

    public void setAddAt(LocalDateTime addAt) {
        this.addAt = addAt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return commentsNo == comment.commentsNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentsNo);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentsNo=" + commentsNo +
                ", usersNo=" + usersNo +
                ", filmsNo=" + filmsNo +
                ", contents='" + contents + '\'' +
                ", addAt=" + addAt +
                ", state='" + state + '\'' +
                '}';
    }
}
