package nc7.mapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class FilmStaff implements Serializable {
    private static final long serialVersionUID = 1L;

    private int filmsStaffNo;
    private int filmsNo;
    private int staffNo;
    private String role;
    private String position;

    public FilmStaff() {
    }

    public FilmStaff(int filmsStaffNo, int filmsNo, int staffNo, String role, String position) {
        this.filmsStaffNo = filmsStaffNo;
        this.filmsNo = filmsNo;
        this.staffNo = staffNo;
        this.role = role;
        this.position = position;
    }

    public int getFilmsStaffNo() {
        return filmsStaffNo;
    }

    public void setFilmsStaffNo(int filmsStaffNo) {
        this.filmsStaffNo = filmsStaffNo;
    }

    public int getFilmsNo() {
        return filmsNo;
    }

    public void setFilmsNo(int filmsNo) {
        this.filmsNo = filmsNo;
    }

    public int getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(int staffNo) {
        this.staffNo = staffNo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmStaff filmStaff = (FilmStaff) o;
        return filmsStaffNo == filmStaff.filmsStaffNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmsStaffNo);
    }

    @Override
    public String toString() {
        return "FilmStaff{" +
                "filmsStaffNo=" + filmsStaffNo +
                ", filmsNo=" + filmsNo +
                ", staffNo=" + staffNo +
                ", role='" + role + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
