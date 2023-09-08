package nc7.javaproject.controller;


import nc7.javaproject.service.NcpObjectStorageService;
import nc7.javaproject.service.UserService;
import nc7.javaproject.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/user")
public class UserController {

  {
    System.out.println("UserController 생성됨!");
  }

  @Autowired
  UserService userService;

  @Autowired
  NcpObjectStorageService ncpObjectStorageService;

  @GetMapping("form")
  public void form() {
  }

  @PostMapping("add")
  public String add(
          User user,
          MultipartFile photofile,
          Model model) throws Exception {

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
      model.addAttribute("message", "회원 등록 오류!");
      model.addAttribute("refresh", "2;url=list");
      throw e;
    }
  }

  @GetMapping("delete")
  public String delete(
          int no,
          Model model) throws Exception {

    try {
      if (userService.delete(no) == 0) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      } else {
        return "redirect:list";
      }
    } catch (Exception e) {
      model.addAttribute("refresh", "2;url=list");
      throw e;
    }
  }

  @GetMapping("{no}")
  public String detail(
          @PathVariable int no,
          Model model) throws Exception {
    model.addAttribute("user", userService.get(no));
    return "user/detail";
  }

  @GetMapping("list")
  public void list(Model model) throws Exception {
    model.addAttribute("list", userService.list());
  }

  @PostMapping("update")
  public String update(
          User user,
          MultipartFile photofile,
          Model model) throws Exception {
    try {
      if (photofile.getSize() > 0) {
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
      model.addAttribute("refresh", "2;url=list");
      throw e;
    }
  }
}
