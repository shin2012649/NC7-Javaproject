package nc7.mapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class WatchList implements Serializable {
    private static final long serialVersionUID = 1L;

    private int usersNo;
    private int filmsNo;

    public WatchList() {
    }

    public WatchList(int usersNo, int filmsNo) {
        this.usersNo = usersNo;
        this.filmsNo = filmsNo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatchList watchList = (WatchList) o;
        return usersNo == watchList.usersNo && filmsNo == watchList.filmsNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersNo, filmsNo);
    }

    @Override
    public String toString() {
        return "WatchList{" +
                "usersNo=" + usersNo +
                ", filmsNo=" + filmsNo +
                '}';
    }
}

