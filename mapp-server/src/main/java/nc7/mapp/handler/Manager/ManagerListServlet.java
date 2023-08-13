package nc7.mapp.handler.Manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.handler.InitServlet;
import nc7.mapp.vo.Manager;

@WebServlet("/manager/list")
public class ManagerListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Manager> managerList = InitServlet.managerDao.findAll();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>관리자 목록</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>관리자 목록</h1>");

        if (managerList.isEmpty()) {
            out.println("<p>등록된 관리자가 없습니다.</p>");
        } else {
            out.println("<table border='1'>");
            out.println("<tr><th>번호</th><th>부서</th><th>직책</th></tr>");
            for (Manager manager : managerList) {
                out.printf("<tr><td>%d</td><td>%s</td><td>%s</td></tr>\n",
                        manager.getUsersNo(), manager.getDepartment(), manager.getJobTitle());
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
