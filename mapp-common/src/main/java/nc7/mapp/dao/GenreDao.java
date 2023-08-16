package nc7.mapp.dao;

import java.util.List;
import nc7.mapp.vo.Genre;

public interface GenreDao {
    void insert(Genre genre);
    List<Genre> findAll();
    Genre findByNo(int genreNo);
    int update(Genre genre);
    int delete(int genreNo);
}
