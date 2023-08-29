package nc7.javaproject.controller;


import nc7.javaproject.dao.UserDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/detail")
public class UserDetailController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    UserDao userDao = (UserDao) this.getServletContext().getAttribute("userDao");
    request.setAttribute("user", userDao.findBy(Integer.parseInt(request.getParameter("no"))));
    response.setContentType("text/html;charset=UTF-8");
    request.getRequestDispatcher("/user/detail.jsp").include(request, response);
  }
}
