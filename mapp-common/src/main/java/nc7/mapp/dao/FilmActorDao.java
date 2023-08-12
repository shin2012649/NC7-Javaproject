package nc7.mapp.dao;

import java.util.List;
import nc7.mapp.vo.FilmActor;

public interface FilmActorDao {
    void insert(FilmActor filmActor);
    List<FilmActor> findByFilmNo(int filmNo);
    int update(FilmActor filmActor);
    int delete(int filmNo, int actorNo);
}
