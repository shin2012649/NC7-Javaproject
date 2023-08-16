package nc7.mapp.dao;

import java.util.List;
import nc7.mapp.vo.FilmGenre;

public interface FilmGenreDao {
    void insert(FilmGenre filmGenre);
    List<FilmGenre> findByFilmNo(int filmNo);
    List<FilmGenre> findByGenreNo(int genreNo);
    int deleteByFilmNo(int filmNo);
    int deleteByGenreNo(int genreNo);
}
