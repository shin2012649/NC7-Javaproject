package nc7.mapp.dao;

import java.util.List;
import nc7.mapp.vo.Rating;

public interface RatingDao {
    void insert(Rating rating);
    List<Rating> findAll();
    List<Rating> findByUserId(int userId);
    List<Rating> findByFilmId(int filmId);
    int update(Rating rating);
    int delete(int userNo, int filmNo);
}
