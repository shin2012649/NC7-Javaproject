package nc7.mapp.dao;

import java.util.List;
import nc7.mapp.vo.FilmSrc;

public interface FilmSrcDao {
    void insert(FilmSrc filmSrc);
    List<FilmSrc> findByFilmNo(int filmNo);
    int deleteByFilmNo(int filmNo);
}
