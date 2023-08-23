package nc7.javaproject.handler;
import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.javaproject.dao.ParticipantDao;
import nc7.javaproject.vo.Participant;
import nc7.util.BreadcrumbPrompt;
import nc7.util.Component;

@Component("/participant/add")
public class ParticipantAddListener implements ParticipantActionListener {

  ParticipantDao participantDao;
  SqlSessionFactory sqlSessionFactory;

  public ParticipantAddListener(ParticipantDao participantDao,  SqlSessionFactory sqlSessionFactory){
    this.participantDao = participantDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Participant p = new Participant();

    p.setName(prompt.inputString("참여자 이름? "));
    p.setAge(Integer.parseInt(prompt.inputString("참여자 나이? ")));
    p.setMovieAttendance(prompt.inputString("영화 A 재관람 의사(Y/N)? "));
    p.setGender(ParticipantActionListener.inputGender((char)0,prompt));
    p.setMovieRating(Integer.parseInt(prompt.inputString("영화 A에 대한 평점(1-5)? ")));
    p.setAdditionalInfo(prompt.inputString("추가 정보? "));
    p.setPassword(prompt.inputString("비밀번호?"));

    try {
      participantDao.insert(p);
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}
