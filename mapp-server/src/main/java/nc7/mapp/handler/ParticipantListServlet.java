package nc7.mapp.handler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.vo.Participant;

@WebServlet("/participant/list")
public class ParticipantListServlet extends HttpServlet {

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
    out.println("<title>영화A 시사회</title>"); 
    out.println("</head>");
    out.println("<body>"); 
    out.println("<h1>영화A 시사회 명단</h1>");
    out.println("<div style='margin:5px;'>");
    out.println("<a href='/participant/form.html'>새 참여자</a>");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr><th>번호</th> <th>이름</th> <th>나이</th> <th>재관람 의사</th> <th>성별</th> <th>평점</th> <th>추가 정보</th></tr>");
    out.println("</thead>");
    out.println("<tbody>");

    List<Participant> list = InitServlet.participantDao.findAll();
    for (Participant p : list) {
      out.printf("<tr>"
          + " <td><a href='/participant/detail?no=%d'>%d</a></td>"
          + " <td>%s</td>"
          + " <td>%d</td>"
          + " <td>%s</td>"
          + " <td>%s</td>"
          + " <td>%d</td>"
          + " <td>%s</td></tr>\n",
          p.getNo(), p.getNo(), p.getName(), p.getAge(), p.getMovieAttendance(), p.getGender() == 'M' ? "남성" : "여성",
          p.getMovieRating(), p.getAdditionalInfo());
    }

    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");
    out.println("</body>");
    out.println("</html>");
  }
}

