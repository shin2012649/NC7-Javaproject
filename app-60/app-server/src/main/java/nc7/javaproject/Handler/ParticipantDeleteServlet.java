package nc7.javaproject.Handler;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/participant/delete")
public class ParticipantDeleteServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      if (InitServlet.participantDao.delete(Integer.parseInt(request.getParameter("no"))) == 0) {
        throw new Exception("해당 번호의 참여자가 없습니다.");
      } else {
        response.sendRedirect("/participant/list");
      }
      InitServlet.sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback(); 
      throw new RuntimeException(e);
    }
  }

}


