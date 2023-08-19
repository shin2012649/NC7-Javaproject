package nc7.javaproject.Handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.javaproject.vo.Participant;

@WebServlet("/index.html")
public class HomeServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>영화평가공유서비스</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>MyApp3</h1>");
    out.println("<ul>");
    out.println("  <li><a href='/participant/list'>회원</a></li>");
    out.println("  <li><a href='/board/list?category=1'>게시판</a></li>");
    out.println("  <li><a href='/board/list?category=2'>평론</a></li>");

    Participant loginUser = (Participant) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      out.println("  <li><a href='/auth/form.html'>로그인</a></li>");
    } else {
      out.printf("  <li>%s <a href='/auth/logout'>로그아웃</a></li>", loginUser.getName());
    }
    out.println("</ul>");
    out.println("</body>");
    out.println("</html>");

  }
}











