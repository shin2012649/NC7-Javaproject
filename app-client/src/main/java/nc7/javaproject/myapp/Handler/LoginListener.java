package nc7.javaproject.myapp.Handler;
import nc7.javaproject.dao.ParticipantDao;
import nc7.javaproject.myapp.ClientApp;
import nc7.javaproject.util.BreadcrumbPrompt;
import nc7.javaproject.vo.Participant;


public class LoginListener implements ParticipantActionListener {

  ParticipantDao participantDao;

  public LoginListener(ParticipantDao participantDao){
    this.participantDao = participantDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    
    while(true) {
    Participant p = new Participant();
    p.setName(prompt.inputString("이름?"));
    p.setPassword(prompt.inputString("비밀번호?"));

    Participant loginUser = participantDao.findByNameAndPassword(p);
    if (loginUser == null) {
      System.out.println("회원 정보가 일치하지 않습니다.");
    } else {
      ClientApp.loginUser = loginUser;
      break;
    }
  }
}
}
