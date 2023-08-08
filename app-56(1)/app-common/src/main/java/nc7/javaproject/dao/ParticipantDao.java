package nc7.javaproject.dao;

import java.util.List;
import nc7.javaproject.vo.Participant;

public interface ParticipantDao {
  void insert(Participant participant);
  List<Participant> findAll();
  Participant findBy(int no);
  Participant findByNameAndPassword(Participant p);
  int update(Participant participant);
  int delete(int no);

}
