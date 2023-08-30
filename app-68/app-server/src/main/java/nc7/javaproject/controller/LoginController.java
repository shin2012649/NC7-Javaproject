package nc7.javaproject.controller;



import nc7.javaproject.dao.UserDao;
import nc7.javaproject.vo.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/auth/login")
public class LoginController implements PageController {

  UserDao userDao;

  public LoginController(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (request.getMethod().equals("GET")) {
      return "/WEB-INF/jsp/auth/form.jsp";
    }

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

    User loginUser = userDao.findByEmailAndPassword(u);
    if (loginUser == null) {
      request.setAttribute("refresh", "2;url=/app/auth/login");
      throw new Exception("회원 정보가 일치하지 않습니다.");
    }

    request.getSession().setAttribute("loginUser", loginUser);
    return "redirect:/";
  }
}
