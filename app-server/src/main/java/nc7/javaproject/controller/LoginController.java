package nc7.javaproject.controller;

import nc7.javaproject.dao.UserDao;
import nc7.javaproject.vo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/auth/login")
public class LoginController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    // 인클루딩 하는 경우,
    // 여기서 콘텐트 타입을 미리 설정해야 한다.
    response.setContentType("text/html;charset=UTF-8");

    // View 컴포넌트를 인클루딩 한다.
    request.getRequestDispatcher("/auth/form.jsp").include(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    User u = new User();
    u.setEmail(request.getParameter("email"));
    u.setPassword(request.getParameter("password"));

    if (request.getParameter("saveEmail") != null) {
      Cookie cookie = new Cookie("email", u.getEmail());
      response.addCookie(cookie);
    } else {
      Cookie cookie = new Cookie("email", "no");
      cookie.setMaxAge(0);
      response.addCookie(cookie);
    }

    UserDao userDao = (UserDao) this.getServletContext().getAttribute("userDao");
    User loginUser = userDao.findByEmailAndPassword(u);
    if (loginUser != null) {
      // 로그인 정보를 다른 요청에서도 사용할 있도록 세션 보관소에 담아 둔다.
      request.getSession().setAttribute("loginUser", loginUser);
      response.sendRedirect("/");
      return;
    }
    request.setAttribute("refresh", "2;url=/auth/login");
    throw new ServletException("회원 정보가 일치하지 않습니다.");
  }
}
