package nc7.javaproject.PreferenceSurveyApp.Handler;
import java.lang.reflect.Member;
import nc7.javaproject.util.BreadcrumbPrompt;
import nc7.javaproject.util.Iterator;
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

 // 목록에서 데이터를 대신 꺼내주는 객체를 얻는다.
    Iterator<Participant> iterator = list.iterator();
    while (iterator.hasNext()) {
      Participant p = iterator.next();
      System.out.printf("%d, %s, %d, %s, %s, %d, %s\n",
      p.getNo(), p.getName(), p.getAge(), p.getMovieAttendance(), p.getGender(),
      p.getMovieRating(), p.getAdditionalInfo());
      System.out.println("---------------------------------------");
    }
  }
}


