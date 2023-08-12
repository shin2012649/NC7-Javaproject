package nc7.mapp.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Film implements Serializable {
    private static final long serialVersionUID = 1L;

    private int filmsNo;
    private String title;
    private String filmsImageUrl;
    private int gradeNo;
    private String descriptions;
    private int runningTime;
    private Date releasedDate;

    public Film() {
    }

    public Film(int filmsNo, String title, String filmsImageUrl, int gradeNo, String descriptions, int runningTime, Date releasedDate) {
        this.filmsNo = filmsNo;
        this.title = title;
        this.filmsImageUrl = filmsImageUrl;
        this.gradeNo = gradeNo;
        this.descriptions = descriptions;
        this.runningTime = runningTime;
        this.releasedDate = releasedDate;
    }

    public int getFilmsNo() {
        return filmsNo;
    }

    public void setFilmsNo(int filmsNo) {
        this.filmsNo = filmsNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilmsImageUrl() {
        return filmsImageUrl;
    }

    public void setFilmsImageUrl(String filmsImageUrl) {
        this.filmsImageUrl = filmsImageUrl;
    }

    public int getGradeNo() {
        return gradeNo;
    }

    public void setGradeNo(int gradeNo) {
        this.gradeNo = gradeNo;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public Date getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(Date releasedDate) {
        this.releasedDate = releasedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return filmsNo == film.filmsNo &&
                gradeNo == film.gradeNo &&
                runningTime == film.runningTime &&
                Objects.equals(title, film.title) &&
                Objects.equals(filmsImageUrl, film.filmsImageUrl) &&
                Objects.equals(descriptions, film.descriptions) &&
                Objects.equals(releasedDate, film.releasedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmsNo, title, filmsImageUrl, gradeNo, descriptions, runningTime, releasedDate);
    }

    @Override
    public String toString() {
        return "Film{" +
                "filmsNo=" + filmsNo +
                ", title='" + title + '\'' +
                ", filmsImageUrl='" + filmsImageUrl + '\'' +
                ", gradeNo=" + gradeNo +
                ", descriptions='" + descriptions + '\'' +
                ", runningTime=" + runningTime +
                ", releasedDate=" + releasedDate +
                '}';
    }
}
