package nc7.javaproject.PreferenceSurveyApp.Handler;
import nc7.javaproject.util.BreadcrumbPrompt;
import nc7.javaproject.util.List;
import nc7.javaproject.vo.Participant;


public class ParticipantListListener extends AbstractParticipantListener {

  public ParticipantListListener( List<Participant> list){
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름 나이, 관람여부, 성별, 평점, 추가정보");
    System.out.println("---------------------------------------");

    for (int i = 0; i < this.list.size(); i++) {
      Participant p = (Participant) this.list.get(i);
      System.out.printf("%d, %s, %d, %s, %s, %d, %s\n",
      p.getNo(), p.getName(), p.getAge(), p.getMovieAttendance(), p.getGender(),
      p.getMovieRating(), p.getAdditionalInfo());
      System.out.println("---------------------------------------");
    }
  }
}


