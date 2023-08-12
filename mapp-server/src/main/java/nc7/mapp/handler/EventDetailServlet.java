package nc7.mapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.vo.Event;

@WebServlet("/event/detail")
public class EventDetailServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Event event = InitServlet.eventDao.findBy(
        Integer.parseInt(request.getParameter("eventId")));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>이벤트 상세 정보</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>이벤트 상세 정보</h1>");

    if (event == null) {
      out.println("<p>해당 이벤트가 없습니다!</p>");

    } else {
      out.println("<table border='1'>");
      out.printf("<tr><th style='width:120px;'>이벤트 번호</th>"
          + " <td style='width:300px;'>%d</td></tr>\n", event.getEventId());
      out.printf("<tr><th>이벤트 이름</th>"
          + " <td>%s</td></tr>\n", event.getName());
      out.printf("<tr><th>응모 기간</th>"
          + " <td>%s</td></tr>\n", event.getEntryPeriod());
      out.printf("<tr><th>발표 일자</th>"
          + " <td>%s</td></tr>\n", event.getAnnouncementDate());
      out.printf("<tr><th>당첨 인원</th> <td>%s</td></tr>\n", event.getWinnersCount());
      out.printf("<tr><th>상영 일자</th> <td>%s</td></tr>\n", event.getScreeningDate());
      out.printf("<tr><th>상영 장소</th> <td>%s</td></tr>\n", event.getScreeningLocation());
      out.printf("<tr><th>안내 사항</th> <td>%s</td></tr>\n", event.getNotice());
      out.println("</table>");
      
      out.println("<div>");
      out.println("<button>변경</button>");
      out.println("<button type='reset'>초기화</button>");
      out.printf("<a href='/event/delete?eventId=%d'>삭제</a>\n",event.getEventId());

      out.println("<div>");
      out.printf("<a href='/event/list?eventId=%d'>목록</a>",event.getEventId());
      out.println("</div>");
    }

    out.println("</body>");
    out.println("</html>");

  }
}
