package nc7.mapp.handler.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.handler.InitServlet;
import nc7.mapp.vo.User;

@WebServlet("/user/list")
public class UserListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> userList = InitServlet.userDao.findAll();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>사용자 목록</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>사용자 목록</h1>");

        if (userList.isEmpty()) {
            out.println("<p>등록된 사용자가 없습니다.</p>");
        } else {
            out.println("<table border='1'>");
            out.println("<tr><th>번호</th><th>이름</th><th>이메일</th><th>생년월일</th></tr>");
            for (User user : userList) {
                out.printf("<tr><td>%d</td><td>%s</td><td>%s</td><td>%tY-%1$tm-%1$td</td></tr>\n",
                        user.getUsersNo(), user.getName(), user.getEmail(), user.getDateOfBirth());
            }
            out.println("</table>");
        }

        out.println("<div>");
        out.println("<a href='/auth/form.html'>로그인</a>");
        out.println("<a href='/'>홈으로</a>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}
