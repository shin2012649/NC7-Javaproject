package nc7.javaproject.handler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.javaproject.vo.Participant;

@WebServlet("/participant/detail")
public class ParticipantDetailServlet extends HttpServlet{

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    Participant participant = InitServlet.participantDao.findBy(Integer.parseInt(request.getParameter("no")));
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>"); 
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>참여자</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>참여자</h1>");
    
    if (participant == null) {
      out.println("<p>해당 번호의 참여자를 없습니다!</p>");

    } else {

      out.println("<form action='/participant/update' method='post'enctype='multipart/form-data'>");
      out.println("<table border='1'>");
      out.printf("<tr><th style='width:120px;'>사진</th>"
          + " <td style='width:300px;'>"
          + (participant.getPhoto() == null ? "<img style='height:80px' src='/images/avatar.png'>" :
            "<a href='https://kr.object.ncloudstorage.com/bitcamp-bucket-05/participant/%s'>"
            + "<img src='http://qjeteawhqfgf19010749.cdn.ntruss.com/participant/%1$s?type=f&w=60&h=80&faceopt=true&ttype=jpg'>"
            + "</a>")
          + " <input type='file' name='photo'>"
          + "</td></tr>\n", participant.getPhoto());
      out.printf("<tr><th style='width:120px;'>번호</th>"
        + " <td style='width:300px;'><input type='text' name='no' value='%d' readonly></td></tr>\n", participant.getNo());
      out.printf("<tr><th>이름</th>"
        + " <td><input type='text' name='name' value='%s'></td></tr>\n", participant.getName());
      out.printf("<tr><th>나이</th>"
        + " <td><input type='age' name='age' value='%d'></td></tr>\n", participant.getAge());
      out.printf("<tr><th>영화 재관람 의사</th>"
        + " <td><input type='text' name='movieAttendance' value='%s'></td></tr>",
        participant.getMovieAttendance());
      out.printf("<tr><th>성별</th>\n"
          + " <td><select name='gender'>\n"
          + " <option value='M' %s>남자</option>\n"
          + " <option value='W' %s>여자</option></select></td></tr>\n",
          (participant.getGender() == 'M' ? "selected" : ""),
          (participant.getGender() == 'W' ? "selected" : ""));
      out.printf("<tr><th>영화 A에 대한 평가(1-5)</th>"
        + " <td><input type='text' name='movieRating' value='%d'></td></tr>\n", participant.getMovieRating());
      out.printf("<tr><th>추가 정보</th>"
        + " <td><input type='text' name='additionalInfo' value='%s'></td></tr>\n", participant.getAdditionalInfo());
      out.println("</table>");

      out.println("<div>");
      out.println("<button>변경</button>");
      out.println("<button type='reset'>초기화</button>");
      out.printf("<a href='/participant/delete?no=%d'>삭제</a>\n", participant.getNo());
      out.println("<a href='/participant/list'>목록</a>\n");
      out.println("</div>");
      out.println("</form>");
    }

      out.println("</body>");
      out.println("</html>");
  }
}



