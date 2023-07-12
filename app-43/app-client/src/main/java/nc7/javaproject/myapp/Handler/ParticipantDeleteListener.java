package nc7.javaproject.myapp.Handler;
import nc7.javaproject.dao.ParticipantDao;
import nc7.javaproject.util.ActionListener;
import nc7.javaproject.util.BreadcrumbPrompt;


public class ParticipantDeleteListener implements ActionListener {

  ParticipantDao participantDao;

  public ParticipantDeleteListener(ParticipantDao participantDao){
    this.participantDao = participantDao;
  }


  @Override
  public void service(BreadcrumbPrompt prompt) {
    if (participantDao.delete(prompt.inputInt("번호? ")) == 0) {
      System.out.println("해당 번호의 회원이 없습니다!");
    }
  }

  }


