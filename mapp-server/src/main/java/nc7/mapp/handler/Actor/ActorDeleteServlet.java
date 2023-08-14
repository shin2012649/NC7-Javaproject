package nc7.mapp.handler.Actor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.vo.User;

@WebServlet("/actor/delete")
public class ActorDeleteServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    User loginUser = (User) request.getSession().getAttribute("loginUser");
    if (loginUser != null && loginUser.isManager()) {

    @SuppressWarnings("unused")
    int actorNo = Integer.parseInt(request.getParameter("actorNo"));
    
    } else {
      // Redirect to actor list page with a message for non-manager users
      response.sendRedirect("/actor/list?error=notauthorized");
      return;
  }

    // Perform actor deletion here

    // Redirect to actor list page
    response.sendRedirect("/actor/list");
  }
}
