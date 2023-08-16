package nc7.mapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class FilmGenre implements Serializable {
    private static final long serialVersionUID = 1L;

    private int filmsGenresNo;
    private int genresNo;
    private int filmsNo;

    public FilmGenre() {
    }

    public FilmGenre(int filmsGenresNo, int genresNo, int filmsNo) {
        this.filmsGenresNo = filmsGenresNo;
        this.genresNo = genresNo;
        this.filmsNo = filmsNo;
    }

    public int getFilmsGenresNo() {
        return filmsGenresNo;
    }

    public void setFilmsGenresNo(int filmsGenresNo) {
        this.filmsGenresNo = filmsGenresNo;
    }

    public int getGenresNo() {
        return genresNo;
    }

    public void setGenresNo(int genresNo) {
        this.genresNo = genresNo;
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
        FilmGenre filmGenre = (FilmGenre) o;
        return filmsGenresNo == filmGenre.filmsGenresNo && genresNo == filmGenre.genresNo && filmsNo == filmGenre.filmsNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmsGenresNo, genresNo, filmsNo);
    }

    @Override
    public String toString() {
        return "FilmGenre{" +
                "filmsGenresNo=" + filmsGenresNo +
                ", genresNo=" + genresNo +
                ", filmsNo=" + filmsNo +
                '}';
    }
}
