package nc7.javaproject.myapp.Handler;
import nc7.javaproject.myapp.dao.ParticipantDao;
import nc7.javaproject.util.BreadcrumbPrompt;
import nc7.javaproject.vo.Participant;


public class ParticipantUpdateListener implements ParticipantActionListener{

  ParticipantDao participantDao;
  
  public ParticipantUpdateListener(ParticipantDao participantDao){
    this.participantDao = participantDao;
  }

  
  @Override
  public void service(BreadcrumbPrompt prompt) {
    int participantNo = prompt.inputInt("변경할 참여자 번호? ");
    
    Participant p =  participantDao.findBy(participantNo);
    if (p == null){
      System.out.println("해당 번호의 참여자를 찾을 수 없습니다.");
      return;
    } else {
      System.out.println("참여자 정보를 변경하였습니다.");
    }
      p.setName(prompt.inputString("변경할 참여자 이름? "));
      p.setAge(Integer.parseInt(prompt.inputString("변경할 참여자 나이? ")));
      p.setMovieAttendance(prompt.inputString("변경할 영화 재관람 의사(Y/N)? "));
      p.setGender(ParticipantActionListener.inputGender(p.getGender(), prompt));
      p.setMovieRating(Integer.parseInt(prompt.inputString("변경할 영화 A에 대한 평가(1-5)? ")));
      p.setAdditionalInfo(prompt.inputString("변경할 추가 정보? "));
      
      participantDao.update(p);
    }
  }


