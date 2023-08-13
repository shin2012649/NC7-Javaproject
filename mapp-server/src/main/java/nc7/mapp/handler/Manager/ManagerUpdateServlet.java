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

@WebServlet("/manager/update")
public class ManagerUpdateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ManagerDao managerDao;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userNo = Integer.parseInt(request.getParameter("userNo"));

        Manager manager = new Manager();
        manager.setUsersNo(userNo);
        manager.setDepartment(request.getParameter("department"));
        manager.setJobTitle(request.getParameter("jobTitle"));
        // 다른 필드도 업데이트에 따라 설정해주세요

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>관리자 정보 변경</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>관리자 정보 변경</h1>");
        try {
            if (managerDao.update(manager) == 0) {
                out.println("<p>관리자 정보 변경 권한이 없거나 관리자가 없습니다.</p>");
            } else {
                out.println("<p>관리자 정보를 변경했습니다!</p>");
            }
        } catch (Exception e) {
            out.println("<p>관리자 정보 변경 실패입니다!</p>");
            e.printStackTrace();
        }
        out.println("</body>");
        out.println("</html>");
    }
}
