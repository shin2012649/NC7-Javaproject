package nc7.mapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class Genre implements Serializable {
    private static final long serialVersionUID = 1L;

    private int genresNo;
    private String name;

    public Genre() {
    }

    public Genre(int genresNo, String name) {
        this.genresNo = genresNo;
        this.name = name;
    }

    public int getGenresNo() {
        return genresNo;
    }

    public void setGenresNo(int genresNo) {
        this.genresNo = genresNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return genresNo == genre.genresNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(genresNo);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genresNo=" + genresNo +
                ", name='" + name + '\'' +
                '}';
    }
}
