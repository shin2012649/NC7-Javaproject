package nc7.javaproject.controller;

import nc7.javaproject.dao.UserDao;
import nc7.javaproject.service.NcpObjectStorageService;
import nc7.javaproject.vo.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@Component("/user/add")
public class UserAddController implements PageController {

  UserDao userDao;
  PlatformTransactionManager txManager;
  NcpObjectStorageService ncpObjectStorageService;

  public UserAddController(
          UserDao userDao,
          PlatformTransactionManager txManager,
          NcpObjectStorageService ncpObjectStorageService) {
    this.userDao = userDao;
    this.txManager = txManager;
    this.ncpObjectStorageService = ncpObjectStorageService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (request.getMethod().equals("GET")) {
      return "/WEB-INF/jsp/user/form.jsp";
    }

    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("tx1");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    TransactionStatus status = txManager.getTransaction(def);

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
      txManager.commit(status);
      return "redirect:list";

    } catch (Exception e) {
      txManager.rollback(status);
      request.setAttribute("message", "회원 등록 오류!");
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }
  }
}
