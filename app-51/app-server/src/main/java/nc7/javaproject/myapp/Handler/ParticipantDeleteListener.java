package nc7.javaproject.myapp.Handler;
import java.io.IOException;
import nc7.javaproject.dao.ParticipantDao;
import nc7.javaproject.util.ActionListener;
import nc7.javaproject.util.BreadcrumbPrompt;
import nc7.javaproject.util.DataSource;


public class ParticipantDeleteListener implements ActionListener {

  ParticipantDao participantDao;
  DataSource ds;

  public ParticipantDeleteListener(ParticipantDao participantDao, DataSource ds){
    this.participantDao = participantDao;
    this.ds = ds;
  }


  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    try {
    if (participantDao.delete(prompt.inputInt("번호? ")) == 0) {
      prompt.println("해당 번호의 회원이 없습니다!");
    }
    prompt.println("삭제했습니다!");
    ds.getConnection().commit();
    
    } catch(Exception e) {
    try {ds.getConnection().rollback();} catch(Exception e2) {}
    throw new RuntimeException(e);
  }
  }
}


