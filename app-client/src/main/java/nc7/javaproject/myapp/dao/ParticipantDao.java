package nc7.javaproject.myapp.dao;
import java.util.List;
import nc7.javaproject.vo.Participant;

public interface ParticipantDao {
  void insert(Participant participant);
  List<Participant> list();
  Participant findBy(int no);
  int update(Participant participant);
  int delete(int no);

}
