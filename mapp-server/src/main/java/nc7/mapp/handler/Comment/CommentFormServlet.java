package nc7.mapp.handler.Comment;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.vo.User;

@WebServlet("/comment/form")
public class CommentFormServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            response.sendRedirect("/login");
            return;
        }

        int filmNo = Integer.parseInt(request.getParameter("filmNo")); // Assuming you pass filmNo as a parameter

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("<meta charset='UTF-8'>");
        response.getWriter().println("<title>댓글 등록</title>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<h1>댓글 등록</h1>");
        response.getWriter().println("<form action='/comment/add' method='post'>");
        response.getWriter().println("<input type='hidden' name='filmNo' value='" + filmNo + "'>"); // Pass filmNo as a hidden field
        response.getWriter().println("<label for='contents'>내용</label> <textarea id='contents' name='contents'></textarea><br>");
        response.getWriter().println("<button type='submit'>등록</button>");
        response.getWriter().println("</form>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}
