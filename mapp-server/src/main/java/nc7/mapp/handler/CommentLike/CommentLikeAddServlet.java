package nc7.mapp.handler.CommentLike;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.vo.User;

@WebServlet("/commentlike/add")
public class CommentLikeAddServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            response.sendRedirect("/login");
            return;
        }

        int commentNo = Integer.parseInt(request.getParameter("commentNo"));

        // Perform database insertion for comment like using MyBatis here
        // You need to call the appropriate MyBatis mapper method to insert the comment like

        response.sendRedirect("/comment/detail?commentsNo=" + commentNo);
    }
}
