package nc7.javaproject.Handler;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.javaproject.vo.Event;

@WebServlet("/event/list")
public class EventListServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;
  SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>이벤트 목록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>이벤트 목록</h1>");
    out.println("<div style='margin:5px;'>");
    out.printf("<a href='/event/form'>새로운 이벤트 등록</a>\n");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr><th>번호</th> <th>이벤트명</th> <th>응모 기간</th> <th>발표 일자</th> <th>당첨 인원</th> <th>상영 일자</th> <th>상영 장소</th></tr>");
    out.println("</thead>");

    List<Event> list = InitServlet.eventDao.findAll();

    out.println("<tbody>");
    for (Event event : list) {
      out.printf("<tr>"
          + " <td>%d</td>"
          + " <td><a href='/event/detail?eventId=%d'>%s</a></td>"
          + " <td>%s</td>"
          + " <td>%s</td>"
          + " <td>%d</td>"
          + " <td>%s</td>"
          + " <td>%s</td></tr>\n",
          event.getEventId(),
          event.getEventId(), // 이 부분 수정
          (event.getName().length() > 0 ? event.getName() : "이벤트명 없음"),
          event.getEntryPeriod(),
          dateFormatter.format(event.getAnnouncementDate()),
          event.getWinnersCount(),
          dateFormatter.format(event.getScreeningDate()),
          event.getScreeningLocation()
      );
  }
    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");
    out.println("</body>");
    out.println("</html>");
  }
}
