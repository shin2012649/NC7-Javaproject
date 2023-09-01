package nc7.javaproject.controller;

import nc7.javaproject.controller.PageController;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/auth/logout")
public class LogoutController implements PageController {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    request.getSession().invalidate();
    return "redirect:/";
  }
}
