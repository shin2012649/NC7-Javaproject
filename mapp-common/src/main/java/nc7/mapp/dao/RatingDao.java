package nc7.mapp.dao;

import java.util.List;
import nc7.mapp.vo.Rating;

public interface RatingDao {
    void insert(Rating rating);
    List<Rating> findAll();
    Rating findByUserAndFilm(int userNo, int filmNo);
    int update(Rating rating);
    int delete(int userNo, int filmNo);
}
