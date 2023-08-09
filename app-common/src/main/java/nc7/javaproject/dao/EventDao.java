package nc7.javaproject.dao;

import java.util.List;
import nc7.javaproject.vo.Event;

public interface EventDao {
  void insert(Event event);
  List<Event> findAll();
  Event findBy(int eventId);
  int update(Event event);
  int delete(int eventId);
}
