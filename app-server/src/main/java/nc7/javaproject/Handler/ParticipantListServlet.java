package nc7.javaproject.Handler;
import java.io.PrintWriter;
import java.util.List;
import nc7.javaproject.dao.ParticipantDao;
import nc7.javaproject.vo.Participant;
import nc7.util.Component;
import nc7.util.HttpServletRequest;
import nc7.util.HttpServletResponse;
import nc7.util.Servlet;

@Component("/participant/list")
public class ParticipantListServlet implements Servlet {

  ParticipantDao participantDao;

  public ParticipantListServlet(ParticipantDao participantDao){
    this.participantDao = participantDao;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>참여자</title>"); 
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>참여자 목록</h1>");
    out.println("<div style='margin:5px;'>");
    out.println("<a href='/participant/form.html'>새 회원</a>");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr><th>번호</th> <th>이름</th> <th>나이</th> <th>영화 재관람</th> <th>성별</th> <th>평점</th> <th>추가 정보</th></tr>");
    out.println("</thead>");
    out.println("<tbody>");

    List<Participant> list = participantDao.findAll();
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

