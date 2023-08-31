package nc7.javaproject.controller;



import nc7.javaproject.dao.UserDao;
import nc7.javaproject.service.NcpObjectStorageService;
import nc7.javaproject.vo.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@Component("/user/add")
public class UserAddController implements PageController {

  UserDao userDao;
  SqlSessionFactory sqlSessionFactory;
  NcpObjectStorageService ncpObjectStorageService;

  public UserAddController(
          UserDao userDao,
          SqlSessionFactory sqlSessionFactory,
          NcpObjectStorageService ncpObjectStorageService) {
    this.userDao = userDao;
    this.sqlSessionFactory = sqlSessionFactory;
    this.ncpObjectStorageService = ncpObjectStorageService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (request.getMethod().equals("GET")) {
      return "/WEB-INF/jsp/user/form.jsp";
    }

    try {
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
      userDao.insert(u);
      sqlSessionFactory.openSession(false).commit();
      return "redirect:list";

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      request.setAttribute("message", "회원 등록 오류!");
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }
  }
}
