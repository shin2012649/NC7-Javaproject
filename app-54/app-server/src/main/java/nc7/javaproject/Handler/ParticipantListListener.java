package nc7.javaproject.handler;
import java.util.List;
import nc7.javaproject.dao.ParticipantDao;
import nc7.javaproject.vo.Participant;
import nc7.util.ActionListener;
import nc7.util.BreadcrumbPrompt;
import nc7.util.Component;

@Component("/participant/list")
public class ParticipantListListener implements ActionListener {

  ParticipantDao participantDao;

  public ParticipantListListener(ParticipantDao participantDao){
    this.participantDao = participantDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    prompt.println("-----------------------------------------------------");
    prompt.println("번호, 이름 나이, 관람여부, 성별, 평점, 추가정보");
    prompt.println("-----------------------------------------------------");

    List<Participant> list  = participantDao.findAll();
    for (Participant p : list) {
      prompt.printf("%d, %s, %d, %s, %c, %d, %s\n",
      p.getNo(), p.getName(), p.getAge(), p.getMovieAttendance(), p.getGender(), p.getMovieRating(), p.getAdditionalInfo());
      prompt.println("-----------------------------------------------------");
    }
  }
}


