package nc7.javaproject.myapp.Handler;
import java.io.IOException;
import nc7.javaproject.dao.ParticipantDao;
import nc7.javaproject.util.ActionListener;
import nc7.javaproject.util.BreadcrumbPrompt;
import nc7.javaproject.vo.Participant;


public class ParticipantDetailListener implements ActionListener{

  ParticipantDao participantDao;

  public ParticipantDetailListener(ParticipantDao participantDao){
    this.participantDao = participantDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    int participantNo = prompt.inputInt("조회할 참여자 번호? ");
    Participant p = participantDao.findBy(participantNo);
      if (p == null) {
        prompt.println("해당 번호의 참여자를 찾을 수 없습니다.");
        return;
      }
      prompt.printf("참여자 이름: %s\n", p.getName());
      prompt.printf("참여자 나이: %d\n", p.getAge());
      prompt.printf("영화  재관람 의사: %s\n", p.getMovieAttendance());
      prompt.printf("성별: %s\n", p.getGender() == 'M' ? "남성" : "여성");
      prompt.printf("영화 A에 대한 평가(1-5): %d\n", p.getMovieRating());
      prompt.printf("추가 정보: %s\n", p.getAdditionalInfo());
    }
  }



