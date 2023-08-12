package nc7.mapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.vo.Event;
import nc7.mapp.vo.Participant;

@WebServlet("/event/update")
public class EventUpdateServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Participant loginUser = (Participant) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }

    int eventId = Integer.parseInt(request.getParameter("eventId"));

    Event event = new Event();
    event.setEventId(eventId);
    event.setName(request.getParameter("name"));
    event.setEntryPeriod(request.getParameter("entryPeriod"));
    event.setAnnouncementDateFromString(request.getParameter("announcementDate"));
    event.setWinnersCount(Integer.parseInt(request.getParameter("winnersCount")));
    event.setScreeningDateFromString(request.getParameter("screeningDate"));
    event.setScreeningLocation(request.getParameter("screeningLocation"));
    event.setNotice(request.getParameter("notice"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.printf("<meta http-equiv='refresh' content='1;url=/event/list'>\n");
    out.println("<title>이벤트</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>이벤트 변경</h1>");
    try {
      if (InitServlet.eventDao.update(event) == 0) {
        out.println("<p>이벤트가 없거나 변경 권한이 없습니다.</p>");
      } else {
        out.println("<p>변경했습니다!</p>");
      }
      InitServlet.sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      out.println("<p>이벤트 변경 실패입니다!</p>");
      e.printStackTrace();
    }
    out.println("</body>");
    out.println("</html>");
  }
}
