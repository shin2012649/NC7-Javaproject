package nc7.javaproject.Handler;
import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.javaproject.dao.ParticipantDao;
import nc7.util.ActionListener;
import nc7.util.BreadcrumbPrompt;
import nc7.util.Component;

@Component("/participant/delete")
public class ParticipantDeleteListener implements ActionListener {

  ParticipantDao participantDao;
  SqlSessionFactory sqlSessionFactory;

  public ParticipantDeleteListener(ParticipantDao participantDao, SqlSessionFactory sqlSessionFactory){
    this.participantDao = participantDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }


  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    try {
      if (participantDao.delete(prompt.inputInt("번호? ")) == 0) {
        prompt.println("해당 번호의 회원이 없습니다!");
      }
      prompt.println("삭제했습니다!");
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }

}


