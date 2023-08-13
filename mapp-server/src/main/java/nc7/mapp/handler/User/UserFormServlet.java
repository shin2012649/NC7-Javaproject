package nc7.mapp.handler.User;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/form")
public class UserFormServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

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
    out.println("<form action='/user/add' method='post'>");
    out.println("이름 <input type='text' name='name'><br>");
    out.println("이메일 <input type='text' name='email'><br>");
    out.println("비밀번호 <input type='password' name='password'><br>");
    out.println("생년월일 <input type='date' name='dateOfBirth'><br>");
    // 다른 필드에 대한 입력 필드도 추가해주세요
    out.println("<button>등록</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}
