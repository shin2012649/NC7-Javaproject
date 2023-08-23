package nc7.javaproject.handler;
import java.io.PrintWriter;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.javaproject.dao.ParticipantDao;
import nc7.javaproject.vo.Participant;
import nc7.util.Component;
import nc7.util.HttpServletRequest;
import nc7.util.HttpServletResponse;
import nc7.util.Servlet;

@Component("/participant/add")
public class ParticipantAddServlet implements Servlet {

  ParticipantDao participantDao;
  SqlSessionFactory sqlSessionFactory;

  public ParticipantAddServlet(ParticipantDao participantDao,  SqlSessionFactory sqlSessionFactory){
    this.participantDao = participantDao;
    this.sqlSessionFactory = sqlSessionFactory; 
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Participant p = new Participant();

    p.setName(request.getParameter("name"));
    p.setAge(Integer.parseInt(request.getParameter("age")));
    p.setMovieAttendance(request.getParameter("movieAttendance"));
    p.setGender(request.getParameter("gender").charAt(0));
    p.setMovieRating(Integer.parseInt(request.getParameter("movieRating")));
    p.setAdditionalInfo(request.getParameter("additionalInfo"));
    p.setPassword(request.getParameter("password?"));

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
    out.println("<h1>시사회 참여자 등록</h1>");

    try {
      participantDao.insert(p);
      sqlSessionFactory.openSession(false).commit();
      out.println("<p>등록 성공입니다!</p>");

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      out.println("<p>등록 실패입니다!</p>");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");
  }
}
