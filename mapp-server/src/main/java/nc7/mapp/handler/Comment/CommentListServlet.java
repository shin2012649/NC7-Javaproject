package nc7.mapp.handler.Comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.vo.Comment;
import nc7.mapp.vo.User;

@WebServlet("/comment/list")
public class CommentListServlet extends HttpServlet {

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

        int filmNo = Integer.parseInt(request.getParameter("filmNo"));
        List<Comment> commentList = null; // Replace this with the code to retrieve the comment list using MyBatis
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>댓글 목록</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>댓글 목록</h1>");
        out.println("<div style='margin:5px;'>");
        out.printf("<a href='/comment/add?filmNo=%d'>새 댓글 추가</a>", filmNo);
        out.println("</div>");
        out.println("<table border='1'>");
        out.println("<thead>");
        out.println("  <tr><th>번호</th> <th>작성자</th> <th>작성일</th> <th>내용</th></tr>");
        out.println("</thead>");
        out.println("<tbody>");

        for (Comment comment : commentList) {
            out.printf("<tr>"
                + " <td>%d</td>"
                + " <td>%d</td>"
                + " <td>%s</td>"
                + " <td><a href='/comment/detail?commentsNo=%d'>상세보기</a></td>"
                + "</tr>\n",
                comment.getCommentsNo(),
                comment.getUsersNo(),
                comment.getAddAt(),
                comment.getCommentsNo()
            );
        }

        out.println("</tbody>");
        out.println("</table>");
        out.println("<a href='/film/list'>영화 목록</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
