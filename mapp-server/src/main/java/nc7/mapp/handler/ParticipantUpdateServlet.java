package nc7.mapp.handler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.vo.Participant;

@WebServlet("/participant/update")
public class ParticipantUpdateServlet extends HttpServlet{

  private static final long serialVersionUID = 1L;
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
   
    Participant participant = new Participant();
    participant.setNo(Integer.parseInt(request.getParameter("no")));
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
      if (InitServlet.participantDao.update(participant) == 0) {
        out.println("<p>참여자가 없습니다.</p>");
      } else {
        InitServlet.sqlSessionFactory.openSession(false).commit();
        out.println("<p>변경했습니다!</p>");
      }
    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      out.println("<p>변경 실패입니다!</p>");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");
  }

}


