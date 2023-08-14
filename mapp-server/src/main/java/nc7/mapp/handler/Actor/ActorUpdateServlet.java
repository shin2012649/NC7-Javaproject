package nc7.mapp.handler.Actor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.handler.InitServlet;
import nc7.mapp.vo.Actor;
import nc7.mapp.vo.User;

@WebServlet("/actor/update")
public class ActorUpdateServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    User loginUser = (User) request.getSession().getAttribute("loginUser");
    if (loginUser == null || !loginUser.isManager()) {
      // Redirect to actor list page with a message for non-manager users
      response.sendRedirect("/actor/list?error=notauthorized");
      return;
    }

    int actorNo = Integer.parseInt(request.getParameter("actorNo"));
    String name = request.getParameter("name");
    String imageUrl = request.getParameter("imageUrl");

    Actor actor = new Actor();
    actor.setActorsNo(actorNo);
    actor.setName(name);
    actor.setImageUrl(imageUrl);

    InitServlet.actorDao.update(actor);

    // Redirect to actor list page
    response.sendRedirect("/actor/list");
  }
}
