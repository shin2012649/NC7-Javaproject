package nc7.mapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/event/form")
public class EventFormServlet extends HttpServlet {

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
    out.println("<title>시사회 등록</title>");
    out.println("</head>");
    out.println("<body>"); 
    out.println("<h1>이벤트 등록</h1>");
    out.println("<form action='/event/add' method='post'>");
    out.println("이벤트 이름 <input type='text' name='name'><br>");
    out.println("응모 기간 <input type='text' name='entryPeriod'><br>");
    out.println("발표 일자 <input type='text' name='announcementDate'><br>");
    out.println("당첨 인원 <input type='text' name='winnersCount'><br>");
    out.println("상영 일자 <input type='text' name='screeningDate'><br>");
    out.println("상영 장소 <input type='text' name='screeningLocation'><br>");
    out.println("안내 사항 <textarea name='notice'></textarea><br>");
    out.println("<button>등록</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}
