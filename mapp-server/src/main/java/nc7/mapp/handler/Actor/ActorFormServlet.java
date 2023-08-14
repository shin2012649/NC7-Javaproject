package nc7.mapp.handler.Actor;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/actor/form")
public class ActorFormServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>배우 등록</title>");
    out.println("</head>");
    out.println("<body>"); 
    out.println("<h1>배우 등록</h1>");
    out.println("<form action='/actor/add' method='post'>");
    out.println("<label for='name'>이름</label> <input type='text' id='name' name='name'><br>");
    out.println("<label for='imageUrl'>이미지 URL</label> <input type='text' id='imageUrl' name='imageUrl'><br>");
    out.println("<button type='submit'>등록</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}
