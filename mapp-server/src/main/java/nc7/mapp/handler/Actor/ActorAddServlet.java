package nc7.mapp.handler.Actor;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.vo.Actor;
import nc7.mapp.vo.User;

@WebServlet("/actor/add")
public class ActorAddServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String name = request.getParameter("name");
    String imageUrl = request.getParameter("imageUrl");
    
    User loginUser = (User) request.getSession().getAttribute("loginUser");
    if (loginUser != null && loginUser.isManager()) {

    Actor actor = new Actor();
    actor.setName(name);
    actor.setImageUrl(imageUrl);

    // Perform database insertion here

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    // Add any other necessary meta tags and title
    out.println("<title>Actor 등록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>Actor 등록 결과</h1>");
    // Display insertion success/failure message
    out.println("</body>");
    out.println("</html>");
} else {
    // Redirect to actor list page with a message for non-manager users
    response.sendRedirect("/actor/list?error=notauthorized");
}
}
}