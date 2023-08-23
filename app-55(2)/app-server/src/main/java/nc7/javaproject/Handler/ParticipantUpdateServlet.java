package nc7.javaproject.handler;
import java.io.PrintWriter;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.javaproject.dao.ParticipantDao;
import nc7.javaproject.vo.Participant;
import nc7.util.Component;
import nc7.util.HttpServletRequest;
import nc7.util.HttpServletResponse;
import nc7.util.Servlet;

@Component("/participant/update")
public class ParticipantUpdateServlet implements Servlet{

  ParticipantDao participantDao;
  SqlSessionFactory sqlSessionFactory;

  public ParticipantUpdateServlet(ParticipantDao participantDao,SqlSessionFactory sqlSessionFactory){
    this.participantDao = participantDao;
    this.sqlSessionFactory = sqlSessionFactory; 
  }


  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
   
    Participant participant = new Participant();
    participant.setName(request.getParameter("name"));
    participant.setAge(Integer.parseInt(request.getParameter("age")));
    participant.setMovieAttendance(request.getParameter("movieAttendance"));
    participant.setGender(request.getParameter("gender").charAt(0));
    participant.setMovieRating(Integer.parseInt(request.getParameter("movieRating")));
    participant.setAdditionalInfo(request.getParameter("additionalInfo "));
    participant.setPassword(request.getParameter("password"));
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='1;url=/participant/list'>");
    out.println("<title>참여자</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>참여자 변경</h1>");
    
    try {
      if (participantDao.update(participant) == 0) {
        out.println("<p>참여자가 없습니다.</p>");
      } else {
        sqlSessionFactory.openSession(false).commit();
        out.println("<p>변경했습니다!</p>");
      }
    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      out.println("<p>변경 실패입니다!</p>");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");
  }

}


