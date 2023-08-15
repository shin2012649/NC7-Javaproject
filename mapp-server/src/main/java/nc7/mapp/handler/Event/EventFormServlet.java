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
import nc7.mapp.vo.User;

@WebServlet("/event/form")
public class EventFormServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check user role/permission
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null || !loginUser.isManager()) {
            // Redirect to login page or handle unauthorized access
            response.sendRedirect("/login"); // Adjust the actual login page URL
            return;
        }

        int eventNo = Integer.parseInt(request.getParameter("eventNo"));
        Event event = null;

        // If eventNo is provided, retrieve existing event
        if (eventNo > 0) {
            event = InitServlet.eventDao.findByNo(eventNo);
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>이벤트 홈</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>이벤트 홈</h1>");

        if (event != null) {
            out.println("<form action='/event/update' method='post'>");
            out.println("<input type='hidden' name='eventNo' value='" + event.getEventNo() + "'>");
        } else {
            out.println("<form action='/event/create' method='post'>");
        }

        out.println("상영 장소: <input type='text' name='screeningLocation' value='" + (event != null ? event.getScreeningLocation() : "") + "'><br>");
        out.println("응모 기간 시작일: <input type='date' name='entryPeriodStart' value='" + (event != null ? event.getEntryPeriodStart() : "") + "'><br>");
        out.println("응모 기간 종료일: <input type='date' name='entryPeriodEnd' value='" + (event != null ? event.getEntryPeriodEnd() : "") + "'><br>");
        out.println("공지사항: <textarea name='notice'>" + (event != null ? event.getNotice() : "") + "</textarea><br>");
        out.println("<input type='submit' value='제출'>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");
    }
}
