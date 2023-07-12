package nc7.javaproject.myapp.Handler;
import java.util.List;
import nc7.javaproject.dao.ParticipantDao;
import nc7.javaproject.util.ActionListener;
import nc7.javaproject.util.BreadcrumbPrompt;
import nc7.javaproject.vo.Participant;


public class ParticipantListListener implements ActionListener {

  ParticipantDao participantDao;

  public ParticipantListListener(ParticipantDao participantDao){
    this.participantDao = participantDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("-----------------------------------------------");
    System.out.println("번호, 이름 나이, 관람여부, 성별, 평점, 추가정보");
    System.out.println("-----------------------------------------------");

 // 목록에서 데이터를 대신 꺼내주는 객체를 얻는다.
    List<Participant> list  = participantDao.list();
    for (Participant p : list) {
      System.out.printf("%d, %s, %d, %s, %c, %d, %s\n",
      p.getNo(), p.getName(), p.getAge(), p.getMovieAttendance(), p.getGender(), p.getMovieRating(), p.getAdditionalInfo());
      System.out.println("-----------------------------------------------");
    }
  }
}


