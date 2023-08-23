package nc7.javaproject.handler;

import nc7.javaproject.dao.UserDao;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/delete")
public class UserDeleteServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    UserDao userDao = (UserDao) this.getServletContext().getAttribute("userDao");
    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");

    try {
      if (userDao.delete(Integer.parseInt(request.getParameter("no"))) == 0) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      } else {
        sqlSessionFactory.openSession(false).commit();
        response.sendRedirect("/user/list");
      }

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();

      request.setAttribute("error", e);
      request.setAttribute("message", e.getMessage());
      request.setAttribute("refresh", "2;url=list");

      request.getRequestDispatcher("/error").forward(request, response);
    }
  }

}
