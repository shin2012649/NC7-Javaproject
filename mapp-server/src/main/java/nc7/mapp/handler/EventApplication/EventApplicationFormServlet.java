package nc7.mapp.handler.EventApplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.vo.User;

@WebServlet("/eventapplication/form")
public class EventApplicationFormServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null || !loginUser.isManager()) {
            // Redirect to login page or handle unauthorized access
            response.sendRedirect("/login"); // Adjust the actual login page URL
            return;
        }

        // 이벤트 신청 폼 HTML을 출력
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>이벤트 신청</title>");
        out.println("</head>");
        out.println("<body>");

        // 여기서 이벤트 신청 폼의 HTML 코드를 작성합니다.
        out.println("<form action='/eventapplication/add' method='post'>");
        out.println("<label for='eventNo'>이벤트 번호:</label>");
        out.println("<input type='text' id='eventNo' name='eventNo'><br>");
        out.println("<label for='userNo'>사용자 번호:</label>");
        out.println("<input type='text' id='userNo' name='userNo' value='" + loginUser.getUsersNo() + "' readonly><br>");
        out.println("<label for='createdDate'>신청일자:</label>");
        out.println("<input type='text' id='createdDate' name='createdDate' value='" + LocalDateTime.now() + "' readonly><br>");
        out.println("<label for='state'>상태:</label>");
        out.println("<input type='text' id='state' name='state' value='APPLIED' readonly><br>");
        out.println("<input type='submit' value='이벤트 신청'>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");
    }
}
