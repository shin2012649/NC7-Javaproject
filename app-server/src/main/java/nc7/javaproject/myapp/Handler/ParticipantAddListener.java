package nc7.javaproject.myapp.Handler;
import java.io.IOException;
import nc7.javaproject.dao.ParticipantDao;
import nc7.javaproject.util.BreadcrumbPrompt;
import nc7.javaproject.vo.Participant;


public class ParticipantAddListener implements ParticipantActionListener {

  ParticipantDao participantDao;

  public ParticipantAddListener(ParticipantDao participantDao){
    this.participantDao = participantDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Participant p = new Participant();

    p.setName(prompt.inputString("참여자 이름? "));
    p.setAge(Integer.parseInt(prompt.inputString("참여자 나이? ")));
    p.setMovieAttendance(prompt.inputString("영화 A 재관람 의사(Y/N)? "));
    p.setGender(ParticipantActionListener.inputGender((char)0,prompt));
    p.setMovieRating(Integer.parseInt(prompt.inputString("영화 A에 대한 평점(1-5)? ")));
    p.setAdditionalInfo(prompt.inputString("추가 정보? "));
    p.setPassword(prompt.inputString("비밀번호?"));

    participantDao.insert(p);
    }
  }
