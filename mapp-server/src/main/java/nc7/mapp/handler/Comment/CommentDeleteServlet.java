package nc7.mapp.handler.Comment;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.vo.User;

@WebServlet("/comment/delete")
public class CommentDeleteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            response.sendRedirect("/login");
            return;
        }

        @SuppressWarnings("unused")
        int commentsNo = Integer.parseInt(request.getParameter("commentsNo"));
        
        // Perform database deletion using MyBatis here
        // You need to call the appropriate MyBatis mapper method to delete the comment

        response.sendRedirect("/comment/list");
    }
}
