package nc7.mapp.handler.Comment;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.handler.InitServlet;
import nc7.mapp.vo.Comment;

@WebServlet("/comment/detail")
public class CommentDetailServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int commentsNo = Integer.parseInt(request.getParameter("commentsNo"));
        Comment comment = InitServlet.commentDao.findBy(commentsNo); 
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>댓글 상세 정보</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>댓글 상세 정보</h1>");

        if (comment == null) {
            out.println("<p>해당 번호의 댓글 정보가 없습니다!</p>");
        } else {
            out.println("<table border='1'>");
            out.printf("<tr><th style='width:120px;'>번호</th>"
                + " <td style='width:300px;'>%d</td></tr>\n", comment.getCommentsNo());
            out.printf("<tr><th>작성자</th>"
                + " <td>%d</td></tr>\n", comment.getUsersNo());
            out.printf("<tr><th>작성일</th>"
                + " <td>%s</td></tr>\n", comment.getAddAt());
            out.printf("<tr><th>내용</th>"
                + " <td>%s</td></tr>\n", comment.getContents());
            out.println("</table>");

            out.println("<div>");
            out.printf("<a href='/comment/update?commentsNo=%d'>수정</a>\n", comment.getCommentsNo());
            out.printf("<a href='/comment/delete?commentsNo=%d'>삭제</a>\n", comment.getCommentsNo());
            out.println("<a href='/comment/list'>목록</a>");
            out.println("</div>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}
