package nc7.mapp.handler.EventApplication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.vo.User;

@WebServlet("/eventapplication/delete")
public class EventApplicationDeleteServlet extends HttpServlet {

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

        int userNo = Integer.parseInt(request.getParameter("userNo"));
        int eventNo = Integer.parseInt(request.getParameter("eventNo"));

        // TODO: Delete event application using MyBatis
        // EventApplicationDao.delete(userNo, eventNo);
        // You need to call the appropriate MyBatis mapper method to delete the event application
        
        // For demonstration purposes, let's assume the event application is deleted successfully
        // and redirect back to the event application list
        response.sendRedirect("/eventapplication/list");
    }
}
