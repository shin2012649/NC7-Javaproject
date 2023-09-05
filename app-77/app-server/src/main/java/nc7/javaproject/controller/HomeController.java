package nc7.javaproject.controller;

import org.springframework.stereotype.Controller;

@Controller
public class HomeController {
  @RequestMapping("/")
  public String home() throws Exception {
    System.out.println(" 홈 컨트롤러 완료! ");
    return "/WEB-INF/jsp/index.jsp";
  }

}
