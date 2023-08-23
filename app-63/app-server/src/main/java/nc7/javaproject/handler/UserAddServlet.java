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

@WebServlet("/user/add")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class UserAddServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    UserDao userDao = (UserDao) this.getServletContext().getAttribute("userDao");
    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
    NcpObjectStorageService ncpObjectStorageService = (NcpObjectStorageService) this.getServletContext().getAttribute("ncpObjectStorageService");

    User u = new User();
    u.setName(request.getParameter("name"));
    u.setEmail(request.getParameter("email"));
    u.setPassword(request.getParameter("password"));
    u.setGender(request.getParameter("gender").charAt(0));

    Part photoPart = request.getPart("photo");
    if (photoPart.getSize() > 0) {
      String uploadFileUrl = ncpObjectStorageService.uploadFile(
              "bitcamp-bucket-05", "user/", photoPart);
      u.setPhoto(uploadFileUrl);
    }

    try {
      userDao.insert(u);
      sqlSessionFactory.openSession(false).commit();
      response.sendRedirect("list");

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();

      request.setAttribute("error", e);
      request.setAttribute("message", "회원 등록 오류!");
      request.setAttribute("refresh", "2;url=list");

      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}