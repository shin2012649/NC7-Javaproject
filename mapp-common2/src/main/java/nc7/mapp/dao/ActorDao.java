package nc7.mapp.dao;

import java.util.List;
import nc7.mapp.vo.Actor;

public interface ActorDao {
    void insert(Actor actor);
    List<Actor> findAll();
    Actor findByActorsNo(int actorsNo);
    int update(Actor actor);
    int delete(int actorsNo);
}
