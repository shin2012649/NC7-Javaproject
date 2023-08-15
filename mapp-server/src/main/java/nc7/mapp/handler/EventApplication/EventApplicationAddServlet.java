package nc7.mapp.handler.EventApplication;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.vo.EventApplication;
import nc7.mapp.vo.User;

@WebServlet("/eventapplication/add")
public class EventApplicationAddServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null || !loginUser.isManager()) {
            // Redirect to login page or handle unauthorized access
            response.sendRedirect("/login"); // Adjust the actual login page URL
            return;
        }

        int eventNo = Integer.parseInt(request.getParameter("eventNo"));
        int userNo = loginUser.getUsersNo();
        LocalDateTime createdDate = LocalDateTime.now();
        String state = "APPLIED";

        @SuppressWarnings("unused")
        EventApplication eventApplication = new EventApplication(userNo, eventNo, Date.valueOf(createdDate.toLocalDate()), state);

        response.sendRedirect("/event/detail?eventNo=" + eventNo);
    }
}
