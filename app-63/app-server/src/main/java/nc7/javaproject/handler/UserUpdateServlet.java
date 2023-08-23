package nc7.javaproject.handler;

import nc7.javaproject.dao.UserDao;
import nc7.javaproject.vo.User;
import nc7.util.NcpObjectStorageService;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@WebServlet("/user/update")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class UserUpdateServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    User user = new User();
    user.setNo(Integer.parseInt(request.getParameter("no")));
    user.setName(request.getParameter("name"));
    user.setEmail(request.getParameter("email"));
    user.setPassword(request.getParameter("password"));
    user.setGender(request.getParameter("gender").charAt(0));

    UserDao userDao = (UserDao) this.getServletContext().getAttribute("memberDao");
    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
    NcpObjectStorageService ncpObjectStorageService = (NcpObjectStorageService) this.getServletContext().getAttribute("ncpObjectStorageService");

    Part photoPart = request.getPart("photo");
    if (photoPart.getSize() > 0) {
      String uploadFileUrl = ncpObjectStorageService.uploadFile(
          "bitcamp-bucket-05", "user/", photoPart);
      user.setPhoto(uploadFileUrl);
    }

    try {
      if (userDao.update(user) == 0) {
        throw new Exception("회원이 없습니다.");
      } else {
        sqlSessionFactory.openSession(false).commit();
        response.sendRedirect("list");
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