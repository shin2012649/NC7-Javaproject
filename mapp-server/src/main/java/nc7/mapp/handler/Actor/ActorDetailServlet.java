package nc7.mapp.handler.Actor;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.handler.InitServlet;
import nc7.mapp.vo.Actor;

@WebServlet("/actor/detail")
public class ActorDetailServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int actorNo = Integer.parseInt(request.getParameter("actorNo"));
    Actor actor = InitServlet.actorDao.findByActorsNo(actorNo);

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>배우 상세 정보</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>배우 상세 정보</h1>");

    if (actor == null) {
      out.println("<p>해당 번호의 배우 정보가 없습니다!</p>");
    } else {
      out.println("<table border='1'>");
      out.printf("<tr><th style='width:120px;'>번호</th>"
          + " <td style='width:300px;'>%d</td></tr>\n", actor.getActorsNo());
      out.printf("<tr><th>이름</th>"
          + " <td>%s</td></tr>\n", actor.getActorsNo());
      out.printf("<tr><th>이미지 URL</th>"
          + " <td>%s</td></tr>\n", actor.getImageUrl());
      out.println("</table>");

      out.println("<div>");
      out.printf("<a href='/actor/update?actorNo=%d'>수정</a>\n", actor.getActorsNo());
      out.printf("<a href='/actor/delete?actorNo=%d'>삭제</a>\n", actor.getActorsNo());
      out.println("<a href='/actor/list'>목록</a>");
      out.println("</div>");
    }

    out.println("</body>");
    out.println("</html>");
  }
}
