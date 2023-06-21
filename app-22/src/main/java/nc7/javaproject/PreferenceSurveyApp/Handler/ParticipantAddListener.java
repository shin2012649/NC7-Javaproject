package nc7.javaproject.PreferenceSurveyApp.Handler;
import nc7.javaproject.util.BreadcrumbPrompt;
import nc7.javaproject.util.List;
import nc7.javaproject.vo.Participant;


public class ParticipantAddListener extends AbstractParticipantListener {
  
  public ParticipantAddListener(List list){
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Participant p = new Participant();
    p.setName(prompt.inputString("참여자 이름? "));
    p.setAge(Integer.parseInt(prompt.inputString("참여자 나이? ")));
    p.setMovieAttendance(prompt.inputString("영화 A 재관람 의사(Y/N)? "));
    p.setGender(inputGender((char)0,prompt));
    p.setMovieRating(Integer.parseInt(prompt.inputString("영화 A에 대한 평점(1-5)? ")));
    p.setAdditionalInfo(prompt.inputString("추가 정보? "));
    
    this.list.add(p);
    }
  }
    