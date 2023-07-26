package nc7.javaproject.myapp.Handler;
import java.io.IOException;
import nc7.javaproject.dao.ParticipantDao;
import nc7.javaproject.util.BreadcrumbPrompt;
import nc7.javaproject.util.DataSource;
import nc7.javaproject.vo.Participant;


public class ParticipantUpdateListener implements ParticipantActionListener{

  ParticipantDao participantDao;
  DataSource ds;

  public ParticipantUpdateListener(ParticipantDao participantDao,DataSource ds){
    this.participantDao = participantDao;
    this.ds = ds;
  }


  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException{
    int participantNo = prompt.inputInt("변경할 참여자 번호? ");

    Participant p =  participantDao.findBy(participantNo);
    if (p == null){
      prompt.println("해당 번호의 참여자를 찾을 수 없습니다.");
      return;
    } else {
      prompt.println("참여자 정보를 변경하였습니다.");
    }
      p.setName(prompt.inputString("변경할 참여자 이름? "));
      p.setAge(Integer.parseInt(prompt.inputString("변경할 참여자 나이? ")));
      p.setMovieAttendance(prompt.inputString("변경할 영화 재관람 의사(Y/N)? "));
      p.setGender(ParticipantActionListener.inputGender(p.getGender(), prompt));
      p.setMovieRating(Integer.parseInt(prompt.inputString("변경할 영화 A에 대한 평가(1-5)? ")));
      p.setAdditionalInfo(prompt.inputString("변경할 추가 정보? "));
      p.setPassword(prompt.inputString("새암호? "));

      try {
      participantDao.update(p);
      ds.getConnection().commit();
      
    }catch(Exception e) {
      try {ds.getConnection().rollback();} catch (Exception e2) {}
      throw new RuntimeException();
    }
  }
}


