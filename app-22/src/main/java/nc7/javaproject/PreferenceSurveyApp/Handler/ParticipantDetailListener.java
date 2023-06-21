package nc7.javaproject.PreferenceSurveyApp.Handler;
import nc7.javaproject.util.BreadcrumbPrompt;
import nc7.javaproject.util.List;
import nc7.javaproject.vo.Participant;


public class ParticipantDetailListener extends AbstractParticipantListener{


  
  public ParticipantDetailListener(List list){
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int participantNo = prompt.inputInt("조회할 참여자 번호? ");
    Participant p = this.findBy(participantNo);
      if (p == null) {
        System.out.println("해당 번호의 참여자를 찾을 수 없습니다.");
        return;
      }
        System.out.printf("참여자 이름: %s\n", p.getName());
        System.out.printf("참여자 나이: %d\n", p.getAge());
        System.out.printf("영화  재관람 의사: %s\n", p.getMovieAttendance());
        System.out.printf("성별: %s\n", toGenderString(p.getGender()));
        System.out.printf("영화 A에 대한 평가(1-5): %d\n", p.getMovieRating());
        System.out.printf("추가 정보: %s\n", p.getAdditionalInfo());
    }
  }
    


  