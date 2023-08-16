package nc7.mapp.dao;

import java.util.List;
import nc7.mapp.vo.FilmCountry;

public interface FilmCountryDao {
    void insert(FilmCountry filmCountry);
    List<FilmCountry> findByFilmNo(int filmNo);
    int delete(int filmNo, String countryNo);
}
