package nc7.mapp.handler.Manager;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.handler.InitServlet;
import nc7.mapp.vo.Manager;
import nc7.mapp.vo.User;

@WebServlet("/manager/add")
public class ManagerAddServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 사용자가 입력한 정보 가져오기
    int userNo = Integer.parseInt(request.getParameter("userNo"));
    String department = request.getParameter("department");
    String jobTitle = request.getParameter("jobTitle");

    // Manager 정보 생성
    Manager manager = new Manager();
    manager.setUsersNo(userNo);
    manager.setDepartment(department);
    manager.setJobTitle(jobTitle);

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>관리자 등록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>관리자 등록</h1>");
    try {
      // Manager 정보를 데이터베이스에 추가하는 로직
      InitServlet.managerDao.insert(manager);
      InitServlet.sqlSessionFactory.openSession(false).commit();

      // 사용자의 권한을 Manager로 설정하는 로직
      User user = InitServlet.userDao.findBy(userNo);
      user.setIsManager(true);
      InitServlet.userDao.update(user);
      InitServlet.sqlSessionFactory.openSession(false).commit();

      out.println("<p>관리자 등록 및 권한 부여 성공입니다!</p>");

    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      out.println("<p>등록 실패입니다!</p>");
      e.printStackTrace();
    }
    out.println("</body>");
    out.println("</html>");
  }
}
