package nc7.mapp.handler.EventApplication;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.vo.User;

@WebServlet("/eventapplication/update")
public class EventApplicationUpdateServlet extends HttpServlet {

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
        String newState = request.getParameter("newState");

        // TODO: Update event application state using MyBatis
        // EventApplicationDao.updateState(userNo, eventNo, newState);
        // You need to call the appropriate MyBatis mapper method to update the state
        
        // For demonstration purposes, let's assume the state is updated successfully
        // and redirect back to the event application list
        response.sendRedirect("/eventapplication/list");
    }
}
