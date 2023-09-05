package nc7.javaproject.controller;


import nc7.javaproject.service.NcpObjectStorageService;
import nc7.javaproject.service.UserService;
import nc7.javaproject.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.Part;
import java.util.Map;

@Controller
public class UserController {

  @Autowired
  UserService userService;

  @Autowired
  NcpObjectStorageService ncpObjectStorageService;

  @RequestMapping("/user/form")
  public String add() {
    return "/WEB-INF/jsp/user/form.jsp";
  }

  @RequestMapping("/user/add")
  public String add(
          User user,
          @RequestParam("photofile") Part photofile,
          Map<String,Object> model) throws Exception {

    try {
      System.out.println(user);
      if (photofile.getSize() > 0) {
        String uploadFileUrl = ncpObjectStorageService.uploadFile(
                "bitcamp-bucket-05", "user/", photofile);
        user.setPhoto(uploadFileUrl);
      }
      userService.add(user);
      return "redirect:list";

    } catch (Exception e) {
      model.put("message", "회원 등록 오류!");
      model.put("refresh", "2;url=list");
      throw e;
    }
  }

  @RequestMapping("/user/delete")
  public String delete(
          @RequestParam("no") int no,
          Map<String,Object> model) throws Exception {

    try {
      if (userService.delete(no) == 0) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      } else {
        return "redirect:list";
      }
    } catch (Exception e) {
      model.put("refresh", "2;url=list");
      throw e;
    }
  }

  @RequestMapping("/user/detail")
  public String detail(
          @RequestParam("no") int no,
          Map<String,Object> model) throws Exception {
    model.put("user", userService.get(no));
    return "/WEB-INF/jsp/user/detail.jsp";
  }

  @RequestMapping("/user/list")
  public String list(Map<String,Object> model) throws Exception {
    model.put("list", userService.list());
    return "/WEB-INF/jsp/user/list.jsp";
  }

  @RequestMapping("/user/update")
  public String update(
          User user,
          @RequestParam("photofile") Part photofile,
          Map<String,Object> model) throws Exception {
    try {
      if (photofile != null && photofile.getSize() > 0) {
        String uploadFileUrl = ncpObjectStorageService.uploadFile(
                "bitcamp-bucket-05", "user/", photofile);
        user.setPhoto(uploadFileUrl);
      }

      if (userService.update(user) == 0) {
        throw new Exception("회원이 없습니다.");
      } else {
        return "redirect:list";
      }

    } catch (Exception e) {
      model.put("refresh", "2;url=list");
      throw e;
    }
  }
}
