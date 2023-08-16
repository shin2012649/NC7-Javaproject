package nc7.mapp.dao;

import java.util.List;
import nc7.mapp.vo.FilmStaff;

public interface FilmStaffDao {
    void insert(FilmStaff filmStaff);
    List<FilmStaff> findByFilmNo(int filmNo);
    int deleteByFilmNo(int filmNo);
}
