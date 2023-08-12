package nc7.mapp.dao;

import java.util.List;
import nc7.mapp.vo.Event;

public interface EventDao {
    void insert(Event event);
    Event findByNo(int eventNo);
    List<Event> findAll();
    int update(Event event);
    int delete(int eventNo);
}
