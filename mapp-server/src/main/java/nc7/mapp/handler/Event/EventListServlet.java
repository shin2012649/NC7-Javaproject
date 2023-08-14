package nc7.mapp.handler.Event;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.handler.InitServlet;
import nc7.mapp.vo.Event;

@WebServlet("/event/list")
public class EventListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Event> eventList = InitServlet.eventDao.findAll();

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

        if (eventList.isEmpty()) {
            out.println("<p>등록된 이벤트가 없습니다.</p>");
        } else {
            out.println("<ul>");

            for (Event event : eventList) {
                out.println("<li>");
                out.println("이벤트 번호: " + event.getEventNo() + "<br>");
                out.println("영화 번호: " + event.getFilmsNo() + "<br>");
                out.println("상영 일자: " + event.getScreeningDate() + "<br>");
                out.println("상영 장소: " + event.getScreeningLocation() + "<br>");
                out.println("<a href='/event/detail?eventNo=" + event.getEventNo() + "'>상세 보기</a>");
                out.println("</li><br>");
            }

            out.println("</ul>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
