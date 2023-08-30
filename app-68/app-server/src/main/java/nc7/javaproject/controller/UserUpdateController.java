package nc7.javaproject.controller;


import nc7.javaproject.controller.PageController;
import nc7.javaproject.dao.UserDao;
import nc7.javaproject.service.NcpObjectStorageService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@Component("/user/update")
public class UserUpdateController implements PageController {

  UserDao userDao;
  SqlSessionFactory sqlSessionFactory;
  NcpObjectStorageService ncpObjectStorageService;

  public UserUpdateController(
          UserDao userDao,
          SqlSessionFactory sqlSessionFactory,
          NcpObjectStorageService ncpObjectStorageService) {
    this.userDao = userDao;
    this.sqlSessionFactory = sqlSessionFactory;
    this.ncpObjectStorageService = ncpObjectStorageService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    try {
      User user = new User();
      user.setNo(Integer.parseInt(request.getParameter("no")));
      user.setName(request.getParameter("name"));
      user.setEmail(request.getParameter("email"));
      user.setPassword(request.getParameter("password"));
      user.setGender(request.getParameter("gender").charAt(0));

      Part photoPart = request.getPart("photo");
      if (photoPart.getSize() > 0) {
        String uploadFileUrl = ncpObjectStorageService.uploadFile(
                "bitcamp-bucket-05", "user/", photoPart);
        user.setPhoto(uploadFileUrl);
      }

      if (userDao.update(user) == 0) {
        throw new Exception("회원이 없습니다.");
      } else {
        sqlSessionFactory.openSession(false).commit();
        return "redirect:list";
      }

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }
  }
}
