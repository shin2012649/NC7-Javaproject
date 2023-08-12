package nc7.mapp.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.vo.Event;
import nc7.mapp.vo.Participant;

@WebServlet("/event/delete")
public class EventDeleteServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Participant loginUser = (Participant) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }

    int eventId = Integer.parseInt(request.getParameter("eventId"));

    Event event = new Event();
    event.setEventId(eventId);

    try {
      if (InitServlet.eventDao.delete(eventId) == 0) {
        throw new Exception("해당 이벤트가 없거나 삭제 권한이 없습니다.");
      } else {
        response.sendRedirect("/event/list");
      }
      InitServlet.sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}
