package nc7.javaproject.controller;



import nc7.javaproject.dao.UserDao;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/user/detail")
public class UserDetailController implements PageController {

  UserDao userDao;

  public UserDetailController(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.setAttribute("user", userDao.findBy(Integer.parseInt(request.getParameter("no"))));
    return "/WEB-INF/jsp/user/detail.jsp";
  }
}
