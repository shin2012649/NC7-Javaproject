package nc7.mapp.handler.Event;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.handler.InitServlet;
import nc7.mapp.vo.Event;
import nc7.mapp.vo.User;

@WebServlet("/event/update")
public class EventUpdateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check user role/permission
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null || !loginUser.isManager()) {
            // Redirect to login page or handle unauthorized access
            response.sendRedirect("/login"); // Adjust the actual login page URL
            return;
        }

        int eventNo = Integer.parseInt(request.getParameter("eventNo"));
        String screeningLocation = request.getParameter("screeningLocation");
        String entryPeriodStartStr = request.getParameter("entryPeriodStart");
        String entryPeriodEndStr = request.getParameter("entryPeriodEnd");
        LocalDateTime entryPeriodStart = LocalDateTime.parse(entryPeriodStartStr);
        LocalDateTime entryPeriodEnd = LocalDateTime.parse(entryPeriodEndStr);
        String notice = request.getParameter("notice");

        Event event = new Event();
        event.setEventNo(eventNo);
        event.setScreeningLocation(screeningLocation);
        event.setEntryPeriodStart(entryPeriodStart);
        event.setEntryPeriodEnd(entryPeriodEnd);
        event.setNotice(notice);

        InitServlet.eventDao.update(event);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>이벤트 업데이트 결과</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>이벤트 업데이트 결과</h1>");
        out.println("<p>이벤트 정보가 업데이트되었습니다.</p>");
        out.println("<p><a href='/event/detail?eventNo=" + eventNo + "'>이벤트 상세 보기</a></p>");
        out.println("</body>");
        out.println("</html>");
    }
}
