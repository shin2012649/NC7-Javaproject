package nc7.javaproject.PreferenceSurveyApp.dao;

import java.util.ArrayList;
import java.util.List;
import nc7.javaproject.util.JsonDataHelper;
import nc7.javaproject.vo.Participant;

public class ParticipantListDao implements ParticipantDao {
  
  String filename;
  ArrayList<Participant> list = new ArrayList<>();
  
  public ParticipantListDao(String filename) {
    this.filename = filename;
    JsonDataHelper.loadJson(filename, list, Participant.class);
  }

  @Override
  public void insert(Participant participant) {
 // 데이터 입력할 때 해당 데이터의 식별 번호는 DAO에서 관리한다.
    participant.setNo(Participant.userId++);
    this.list.add(participant);

    // 데이터를 등록할 때 마다 즉시 파일에 저장한다.
    JsonDataHelper.saveJson(filename, list);
  }

  @Override
  public List<Participant> list() {
    // TODO Auto-generated method stub
    return this.list;
  }

  @Override
  public Participant findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Participant p = this.list.get(i);
      if (p.getNo() == no) {
        return p;
      }
    }
    return null;
  }

  @Override
  public int update(Participant participant) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == participant.getNo()) {
        list.set(i, participant);
        JsonDataHelper.saveJson(filename, list);
        return 1;
      }
    }
    return 0;
  }

  @Override
  public int delete(int no) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == no) {
        list.remove(i);
        JsonDataHelper.saveJson(filename, list);
        return 1;
      }
    }
    return 0;
  }
}
