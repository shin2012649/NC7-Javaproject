package nc7.mapp.handler.CommentLike;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.vo.User;

@WebServlet("/commentlike/form")
public class CommentLikeFormServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            response.sendRedirect("/login");
            return;
        }

        int commentNo = Integer.parseInt(request.getParameter("commentNo"));

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>댓글 좋아요</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>댓글 좋아요</h1>");
        out.println("<form action='/comment/like/add' method='post'>");
        out.println("<input type='hidden' name='commentNo' value='" + commentNo + "'>");
        out.println("<button type='submit'>좋아요</button>");
        out.println("</form>");
        out.println("<a href='/comment/detail?commentsNo=" + commentNo + "'>댓글 상세보기</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
