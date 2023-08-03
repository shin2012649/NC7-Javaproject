package nc7.javaproject.Handler;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.javaproject.dao.ParticipantDao;
import nc7.util.Component;
import nc7.util.HttpServletRequest;
import nc7.util.HttpServletResponse;
import nc7.util.Servlet;

@Component("/participant/delete")
public class ParticipantDeleteServlet implements Servlet {

  ParticipantDao participantDao;
  SqlSessionFactory sqlSessionFactory;

  public ParticipantDeleteServlet(ParticipantDao participantDao, SqlSessionFactory sqlSessionFactory){
    this.participantDao = participantDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }


  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    try {
      if (participantDao.delete(Integer.parseInt(request.getParameter("no"))) == 0) {
        throw new Exception("해당 번호의 참여자가 없습니다.");
      } else {
        response.sendRedirect("/participant/list");
      }
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback(); 
      throw new RuntimeException(e);
    }
  }

}


