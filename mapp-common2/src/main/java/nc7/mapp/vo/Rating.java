package nc7.mapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class Rating implements Serializable {
    private static final long serialVersionUID = 1L;

    private int usersNo;
    private int filmsNo;
    private double ratingScore;

    public Rating() {
    }

    public Rating(int usersNo, int filmsNo, double ratingScore) {
        this.usersNo = usersNo;
        this.filmsNo = filmsNo;
        this.ratingScore = ratingScore;
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

    public double getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(double ratingScore) {
        this.ratingScore = ratingScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating = (Rating) o;
        return usersNo == rating.usersNo && filmsNo == rating.filmsNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersNo, filmsNo);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "usersNo=" + usersNo +
                ", filmsNo=" + filmsNo +
                ", ratingScore=" + ratingScore +
                '}';
    }
}
