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

@WebServlet("/event/add")
public class EventAddServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Participant loginUser = (Participant) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }

    Event event = new Event();
    event.setName(request.getParameter("name"));
    event.setEntryPeriod(request.getParameter("entry_period"));
    event.setAnnouncementDateFromString("2023-08-01 10:00:00");
    event.setWinnersCount(Integer.parseInt(request.getParameter("winners_count")));
    event.setScreeningDateFromString("2023-09-05 18:00:00");
    event.setScreeningLocation(request.getParameter("screening_location"));
    event.setNotice(request.getParameter("notice"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='1;url=/event/list'>");
    out.println("<title>이벤트 등록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>이벤트 등록</h1>");
    try {
      InitServlet.eventDao.insert(event);
      InitServlet.sqlSessionFactory.openSession(false).commit();
      out.println("<p>등록 성공입니다!</p>");
    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      out.println("<p>등록 실패입니다!</p>");
      e.printStackTrace();
    }
    out.println("</body>");
    out.println("</html>");
  }
}
