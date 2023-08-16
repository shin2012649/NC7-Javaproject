package nc7.mapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class Country implements Serializable {
    private static final long serialVersionUID = 1L;

    private String countriesNo;
    private String name;

    public Country() {
    }

    public Country(String countriesNo, String name) {
        this.countriesNo = countriesNo;
        this.name = name;
    }

    public String getCountriesNo() {
        return countriesNo;
    }

    public void setCountriesNo(String countriesNo) {
        this.countriesNo = countriesNo;
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
        Country country = (Country) o;
        return Objects.equals(countriesNo, country.countriesNo) &&
                Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countriesNo, name);
    }

    @Override
    public String toString() {
        return "Country{" +
                "countriesNo='" + countriesNo + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
