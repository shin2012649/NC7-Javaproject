package nc7.mapp.handler.Event;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.handler.InitServlet;
import nc7.mapp.vo.Event;

@WebServlet("/event/detail")
public class EventDetailServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Event event = InitServlet.eventDao.findByNo(Integer.parseInt(request.getParameter("eventNo")));

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>이벤트 상세 정보</title>");
        out.println("</head>");
        out.println("<body>");

        if (event == null) {
            out.println("<h1>해당 이벤트를 찾을 수 없습니다.</h1>");
        } else {
            out.println("<h1>이벤트 상세 정보</h1>");
            out.println("<p>이벤트 번호: " + event.getEventNo() + "</p>");
            out.println("<p>영화 번호: " + event.getFilmsNo() + "</p>");
            out.println("<p>상영 일자: " + event.getScreeningDate() + "</p>");
            out.println("<p>상영 장소: " + event.getScreeningLocation() + "</p>");
            out.println("<p>응모 기간 시작일: " + event.getEntryPeriodStart() + "</p>");
            out.println("<p>응모 기간 종료일: " + event.getEntryPeriodEnd() + "</p>");
            out.println("<p>발표 일자: " + event.getAnnouncementDate() + "</p>");
            out.println("<p>당첨자 수: " + event.getWinnersCount() + "</p>");
            out.println("<p>공지사항: " + event.getNotice() + "</p>");

            // 필요한 이벤트 정보들을 출력해주시면 됩니다.
            // 예: out.println("<p>XXX: " + event.getXXX() + "</p>");
            // ...
        }

        out.println("</body>");
        out.println("</html>");
    }
}
