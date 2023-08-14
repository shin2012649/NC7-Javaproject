package nc7.mapp.handler.CommentLike;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.vo.CommentLike;
import nc7.mapp.vo.User;

@WebServlet("/commentlike/list")
public class CommentLikeListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("null")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            response.sendRedirect("/login");
            return;
        }

        int commentNo = Integer.parseInt(request.getParameter("commentNo"));
        List<CommentLike> commentLikeList = null; // Replace this with the code to retrieve comment like list using MyBatis

        // You may need to modify the logic here based on how you determine if the user has liked the comment
        boolean hasLikedComment = false; // Replace this with the actual check

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>댓글 좋아요 목록</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>댓글 좋아요 목록</h1>");
        out.println("<table border='1'>");
        out.println("<thead>");
        out.println("  <tr><th>사용자 번호</th></tr>");
        out.println("</thead>");
        out.println("<tbody>");

        for (CommentLike commentLike : commentLikeList) {
            out.printf("<tr><td>%d</td></tr>\n", commentLike.getUsersNo());
        }

        out.println("</tbody>");
        out.println("</table>");

        if (hasLikedComment) {
            out.println("<form action='/comment/like/delete' method='post'>");
            out.println("<input type='hidden' name='commentNo' value='" + commentNo + "'>");
            out.println("<button type='submit'>좋아요 취소</button>");
            out.println("</form>");
        } else {
            out.println("<form action='/comment/like/add' method='post'>");
            out.println("<input type='hidden' name='commentNo' value='" + commentNo + "'>");
            out.println("<button type='submit'>좋아요</button>");
            out.println("</form>");
        }

        out.println("<a href='/comment/detail?commentsNo=" + commentNo + "'>댓글 상세보기</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
