package nc7.javaproject.handler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import nc7.javaproject.vo.Participant;

@WebServlet("/participant/add")
public class ParticipantAddServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;



  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Participant p = new Participant();

    p.setName(request.getParameter("name"));
    p.setAge(Integer.parseInt(request.getParameter("age")));
    p.setMovieAttendance(request.getParameter("movieAttendance"));
    p.setGender(request.getParameter("gender").charAt(0));
    p.setMovieRating(Integer.parseInt(request.getParameter("movieRating")));
    p.setAdditionalInfo(request.getParameter("additionalInfo"));
    p.setPassword(request.getParameter("password"));
    
    Part photoPart = request.getPart("photo");
    if (photoPart.getSize() > 0) {
      String uploadFileUrl = InitServlet.ncpObjectStorageService.uploadFile(
          "bitcamp-bucket-05", "participant/", photoPart);
      p.setPhoto(uploadFileUrl);
    }

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='1;url=/participant/list'>");
    out.println("<title>참여자</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>시사회 참여자 등록</h1>");

    try {
      InitServlet.participantDao.insert(p);
      InitServlet.sqlSessionFactory.openSession(false).commit();
      out.println("<p>등록 성공입니다!</p>");

    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      out.println("<p>등록 실패입니다!</p>");
      e.printStackTrace();
    }

    out.println("</body>");
    out.println("</html>");
  }
}
