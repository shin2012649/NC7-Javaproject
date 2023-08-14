package nc7.mapp.handler.Comment;

import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.vo.Comment;
import nc7.mapp.vo.User;

@WebServlet("/comment/add")
public class CommentAddServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            // Redirect to login page or handle unauthorized access
            response.sendRedirect("/login"); // Adjust the actual login page URL
            return;
        }

        int filmNo = Integer.parseInt(request.getParameter("filmNo"));
        String contents = request.getParameter("contents");

        Comment comment = new Comment();
        comment.setUsersNo(loginUser.getUsersNo()); // Assuming you have a method to get the user's ID or number
        comment.setFilmsNo(filmNo);
        comment.setContents(contents);
        comment.setAddAt(LocalDateTime.now());
        comment.setState("active"); // You may want to set the initial state here

        // Perform database insertion using MyBatis here
        // You need to call the appropriate MyBatis mapper method to insert the comment

        // Redirect to the film details page or comment list page
        response.sendRedirect("/film/detail?filmNo=" + filmNo);
    }
}
