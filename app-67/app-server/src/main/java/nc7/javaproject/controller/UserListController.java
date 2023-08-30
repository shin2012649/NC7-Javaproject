package nc7.javaproject.controller;

import nc7.javaproject.dao.UserDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/list")
public class UserListController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    UserDao userDao = (UserDao) this.getServletContext().getAttribute("userDao");
    request.setAttribute("list", userDao.findAll());

    request.setAttribute("viewUrl", "/WEB-INF/jsp/member/list.jsp");
  }

}
