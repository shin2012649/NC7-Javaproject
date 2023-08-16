package nc7.mapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class FilmCountry implements Serializable {
    private static final long serialVersionUID = 1L;

    private int filmsNo;
    private String countriesNo;

    public FilmCountry() {
    }

    public FilmCountry(int filmsNo, String countriesNo) {
        this.filmsNo = filmsNo;
        this.countriesNo = countriesNo;
    }

    public int getFilmsNo() {
        return filmsNo;
    }

    public void setFilmsNo(int filmsNo) {
        this.filmsNo = filmsNo;
    }

    public String getCountriesNo() {
        return countriesNo;
    }

    public void setCountriesNo(String countriesNo) {
        this.countriesNo = countriesNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmCountry that = (FilmCountry) o;
        return filmsNo == that.filmsNo && Objects.equals(countriesNo, that.countriesNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmsNo, countriesNo);
    }

    @Override
    public String toString() {
        return "FilmCountry{" +
                "filmsNo=" + filmsNo +
                ", countriesNo='" + countriesNo + '\'' +
                '}';
    }
}
