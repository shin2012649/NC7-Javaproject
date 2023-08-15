package nc7.mapp.handler.EventApplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.vo.EventApplication;
import nc7.mapp.vo.User;

@WebServlet("/eventapplication/list")
public class EventApplicationListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null || !loginUser.isManager()) {
            // Redirect to login page or handle unauthorized access
            response.sendRedirect("/login"); // Adjust the actual login page URL
            return;
        }

        // TODO: Retrieve event application list using MyBatis
        // List<EventApplication> eventApplications = EventApplicationDao.findAll();
        // You need to call the appropriate MyBatis mapper method to retrieve the list
        
        // For demonstration purposes, let's create a sample list of event applications
        List<EventApplication> eventApplications = getSampleEventApplications();

        // Display the list of event applications
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>이벤트 신청 목록</title>");
        out.println("</head>");
        out.println("<body>");

        out.println("<h1>이벤트 신청 목록</h1>");
        out.println("<table border='1'>");
        out.println("<tr>");
        out.println("<th>사용자 번호</th>");
        out.println("<th>이벤트 번호</th>");
        out.println("<th>신청일자</th>");
        out.println("<th>상태</th>");
        out.println("</tr>");

        for (EventApplication eventApplication : eventApplications) {
            out.println("<tr>");
            out.println("<td>" + eventApplication.getUsersNo() + "</td>");
            out.println("<td>" + eventApplication.getEventNo() + "</td>");
            out.println("<td>" + eventApplication.getCreatedDate() + "</td>");
            out.println("<td>" + eventApplication.getState() + "</td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

    // For demonstration purposes, create a sample list of event applications
    private List<EventApplication> getSampleEventApplications() {
        // TODO: Replace with actual retrieval of event applications from database
        // This is just a placeholder example
        List<EventApplication> eventApplications = List.of(
            new EventApplication(1, 101, null, "APPLIED"),
            new EventApplication(2, 101, null, "APPROVED"),
            new EventApplication(3, 102, null, "APPLIED")
        );
        return eventApplications;
    }
}
