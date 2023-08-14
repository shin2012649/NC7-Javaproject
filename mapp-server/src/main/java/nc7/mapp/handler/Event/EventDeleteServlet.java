package nc7.mapp.handler.Event;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.vo.User;

@WebServlet("/event/delete")
public class EventDeleteServlet extends HttpServlet {

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

        @SuppressWarnings("unused")
        int eventNo = Integer.parseInt(request.getParameter("eventNo"));

        // Perform event deletion using MyBatis here
        // You need to call the appropriate MyBatis mapper method to delete the event
        
        // Redirect to the event list page
        response.sendRedirect("/event/list");
    }
}
