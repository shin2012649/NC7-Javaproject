package nc7.mapp.handler.Comment;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.vo.User;
import nc7.mapp.vo.Comment;

@WebServlet("/comment/update")
public class CommentUpdateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            response.sendRedirect("/login");
            return;
        }

        int commentsNo = Integer.parseInt(request.getParameter("commentsNo"));
        String contents = request.getParameter("contents");

        Comment updatedComment = new Comment();
        updatedComment.setCommentsNo(commentsNo);
        updatedComment.setContents(contents);

        // Perform database update using MyBatis here
        // You need to call the appropriate MyBatis mapper method to update the comment

        // Redirect back to the comment detail page
        response.sendRedirect("/comment/detail?commentsNo=" + commentsNo);
    }
}
