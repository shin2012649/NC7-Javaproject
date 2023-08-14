package nc7.mapp.handler.CommentLike;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/commentlike/detail")
public class CommentLikeDetailServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        @SuppressWarnings("unused")
        int commentNo = Integer.parseInt(request.getParameter("commentNo"));
        // Replace the placeholder code with the actual code to retrieve comment like details using MyBatis

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>댓글 좋아요 상세 정보</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>댓글 좋아요 상세 정보</h1>");

        // Replace the placeholder code with the actual code to display comment like details

        out.println("</body>");
        out.println("</html>");
    }
}
