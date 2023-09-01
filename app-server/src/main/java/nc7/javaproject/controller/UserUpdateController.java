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

@Component("/user/update")
public class UserUpdateController implements PageController {

  UserDao userDao;
  PlatformTransactionManager txManager;
  NcpObjectStorageService ncpObjectStorageService;

  public UserUpdateController(
          UserDao userDao,
          PlatformTransactionManager txManager,
          NcpObjectStorageService ncpObjectStorageService) {
    this.userDao = userDao;
    this.txManager = txManager;
    this.ncpObjectStorageService = ncpObjectStorageService;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("tx1");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    TransactionStatus status = txManager.getTransaction(def);

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
        txManager.commit(status);
        return "redirect:list";
      }

    } catch (Exception e) {
      txManager.rollback(status);
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }
  }
}
