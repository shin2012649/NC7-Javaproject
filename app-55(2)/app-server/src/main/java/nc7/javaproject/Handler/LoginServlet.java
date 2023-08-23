package nc7.javaproject.handler;
import java.io.PrintWriter;
import nc7.javaproject.dao.ParticipantDao;
import nc7.javaproject.vo.Participant;
import nc7.util.Component;
import nc7.util.HttpServletRequest;
import nc7.util.HttpServletResponse;
import nc7.util.Servlet;

@Component("/auth/login")
public class LoginServlet implements Servlet {

  ParticipantDao participantDao;

  public LoginServlet(ParticipantDao participantDao){
    this.participantDao = participantDao;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Participant p = new Participant();
    p.setName(request.getParameter("name"));
    p.setPassword(request.getParameter("password"));
    
    Participant loginUser = participantDao.findByNameAndPassword(p);
    if (loginUser == null) {
      request.getSession().setAttribute("loginUser", loginUser);
      response.sendRedirect("/");
      return;
    }
    
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='1;url=/auth/form.html'>");
    out.println("<title>로그인</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>로그인</h1>");
    out.println("<p>참여자 정보가 일치하지 않습니다.</p>");
    out.println("</html>");
  }
}
