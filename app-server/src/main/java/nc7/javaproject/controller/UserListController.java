package nc7.javaproject.controller;


import nc7.javaproject.dao.UserDao;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/user/list")
public class UserListController implements PageController {

  UserDao userDao;

  public UserListController(UserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.setAttribute("list", userDao.findAll());
    return "/WEB-INF/jsp/user/list.jsp";
  }

}
