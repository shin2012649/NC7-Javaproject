package nc7.mapp.handler.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.handler.InitServlet;
import nc7.mapp.vo.User;

@WebServlet("/user/add")
public class UserAddServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 사용자가 입력한 정보 가져오기
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String dateOfBirthStr = request.getParameter("dateOfBirth");

    // 날짜 형식 변환
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    Date dateOfBirth = null;
    try {
      dateOfBirth = dateFormatter.parse(dateOfBirthStr);
    } catch (Exception e) {
      e.printStackTrace();
    }

    // 사용자 정보 생성
    User user = new User();
    user.setName(name);
    user.setEmail(email);
    user.setPassword(password);
    user.setDateOfBirth(dateOfBirth);

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>사용자 등록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>사용자 등록</h1>");
    try {
      // 사용자 정보를 데이터베이스에 추가하는 로직
      InitServlet.userDao.insert(user);
      InitServlet.sqlSessionFactory.openSession(false).commit();
      out.println("<p>등록 성공입니다!</p>");

    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      out.println("<p>등록 실패입니다!</p>");
      e.printStackTrace();
    }
    out.println("</body>");
    out.println("</html>");
  }
}
