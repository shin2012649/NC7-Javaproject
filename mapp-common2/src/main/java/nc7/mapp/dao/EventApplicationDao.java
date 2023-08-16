package nc7.mapp.dao;

import java.util.List;
import nc7.mapp.vo.EventApplication;

public interface EventApplicationDao {
    void insert(EventApplication eventApplication);
    EventApplication findByUserAndEvent(int userNo, int eventNo);
    List<EventApplication> findByUser(int userNo);
    List<EventApplication> findByEvent(int eventNo);
    List<EventApplication> findAll();
    int update(EventApplication eventApplication);
    int delete(int userNo, int eventNo);
}
