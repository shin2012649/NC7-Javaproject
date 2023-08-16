package nc7.mapp.dao;

import java.util.List;
import nc7.mapp.vo.Film;

public interface FilmDao {
    void insert(Film film);
    List<Film> findAll();
    Film findByNo(int filmNo);
    int update(Film film);
    int delete(int filmNo);
}
