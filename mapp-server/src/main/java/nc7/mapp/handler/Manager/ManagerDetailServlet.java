package nc7.mapp.handler.Manager;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.dao.ManagerDao;
import nc7.mapp.vo.Manager;

@WebServlet("/manager/detail")
public class ManagerDetailServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ManagerDao managerDao; // ManagerDao 인터페이스 변수


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userNo = Integer.parseInt(request.getParameter("userNo")); // 사용자 번호를 파라미터로 받아옴

        Manager manager = managerDao.findByUserNo(userNo); // 사용자 번호를 이용해 관리자 정보를 가져옴

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>관리자 상세 정보</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>관리자 상세 정보</h1>");

        if (manager == null) {
            out.println("<p>해당 관리자 정보가 없습니다!</p>");
        } else {
            out.println("<table border='1'>");
            out.printf("<tr><th>사용자 번호</th><td>%d</td></tr>\n", manager.getUsersNo());
            out.printf("<tr><th>부서</th><td>%s</td></tr>\n", manager.getDepartment());
            out.printf("<tr><th>직책</th><td>%s</td></tr>\n", manager.getJobTitle());
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
