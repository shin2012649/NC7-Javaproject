package nc7.mapp.handler.Manager;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/manager/form")
public class ManagerFormServlet extends HttpServlet {

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
    out.println("<title>관리자 등록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>관리자 등록</h1>");
    out.println("<form action='/manager/add' method='post'>");
    out.println("사용자 번호 <input type='number' name='userNo'><br>");
    out.println("부서 <input type='text' name='department'><br>");
    out.println("직책 <input type='text' name='jobTitle'><br>");
    // 다른 필드에 대한 입력 필드도 추가해주세요
    out.println("<button>등록</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}
