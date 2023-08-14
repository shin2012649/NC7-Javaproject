package nc7.mapp.handler.Actor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.handler.InitServlet;
import nc7.mapp.vo.Actor;

@WebServlet("/actor/list")
public class ActorListServlet extends HttpServlet {

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
    out.println("<title>배우 목록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>배우 목록</h1>");
    out.println("<div style='margin:5px;'>");
    out.println("<a href='/actor/form'>새 배우 추가</a>");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr><th>번호</th> <th>이름</th> <th>이미지 URL</th></tr>");
    out.println("</thead>");

    List<Actor> actorList = InitServlet.actorDao.findAll();

    out.println("<tbody>");
    for (Actor actor : actorList) {
      out.printf("<tr>"
          + " <td>%d</td>"
          + " <td><a href='/actor/detail?actorNo=%d'>%s</a></td>"
          + " <td>%s</td></tr>\n",
          actor.getActorsNo(),
          actor.getActorsNo(),
          actor.getName(),
          actor.getImageUrl()
      );
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");
    out.println("</body>");
    out.println("</html>");
  }
}
