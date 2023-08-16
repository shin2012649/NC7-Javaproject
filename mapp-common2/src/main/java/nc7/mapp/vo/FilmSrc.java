package nc7.mapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class FilmSrc implements Serializable {
    private static final long serialVersionUID = 1L;

    private int filmsNo;
    private int srcNo;

    public FilmSrc() {
    }

    public FilmSrc(int filmsNo, int srcNo) {
        this.filmsNo = filmsNo;
        this.srcNo = srcNo;
    }

    public int getFilmsNo() {
        return filmsNo;
    }

    public void setFilmsNo(int filmsNo) {
        this.filmsNo = filmsNo;
    }

    public int getSrcNo() {
        return srcNo;
    }

    public void setSrcNo(int srcNo) {
        this.srcNo = srcNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmSrc filmSrc = (FilmSrc) o;
        return filmsNo == filmSrc.filmsNo && srcNo == filmSrc.srcNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmsNo, srcNo);
    }

    @Override
    public String toString() {
        return "FilmSrc{" +
                "filmsNo=" + filmsNo +
                ", srcNo=" + srcNo +
                '}';
    }
}
