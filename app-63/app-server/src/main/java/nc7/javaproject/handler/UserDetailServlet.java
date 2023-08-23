package nc7.javaproject.handler;

import nc7.javaproject.dao.UserDao;
import nc7.javaproject.vo.User;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/user/detail")
public class UserDetailServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    UserDao userDao = (UserDao) this.getServletContext().getAttribute("userDao");
    User user = userDao.findBy(Integer.parseInt(request.getParameter("no")));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>회원</title>");
    out.println("</head>");
    out.println("<body>");

    request.getRequestDispatcher("/header").include(request, response);

    out.println("<h1>회원</h1>");

    if (user == null) {
      out.println("<p>해당 번호의 회원이 없습니다!</p>");

    } else {
      out.println("<form action='/user/update' method='post' enctype='multipart/form-data'>");
      out.println("<table border='1'>");
      out.printf("<tr><th style='width:120px;'>사진</th>"
          + " <td style='width:300px;'>"
          + (user.getPhoto() == null ? "<img style='height:80px' src='/images/avatar.png'>" :
            "<a href='https://kr.object.ncloudstorage.com/bitcamp-bucket-05/user/%s'>"
            + "<img src='http://qjeteawhqfgf19010749.cdn.ntruss.com/user/%1$s?type=f&w=60&h=80&faceopt=true&ttype=jpg'>"
            + "</a>")
          + " <input type='file' name='photo'>"
          + "</td></tr>\n", user.getPhoto());
      out.printf("<tr><th style='width:120px;'>번호</th>"
          + " <td style='width:300px;'><input type='text' name='no' value='%d' readonly></td></tr>\n", user.getNo());
      out.printf("<tr><th>이름</th>"
          + " <td><input type='text' name='name' value='%s'></td></tr>\n", user.getName());
      out.printf("<tr><th>이메일</th>"
          + " <td><input type='email' name='email' value='%s'></td></tr>\n", user.getEmail());
      out.println("<tr><th>암호</th>"
          + " <td><input type='password' name='password'></td></tr>");
      out.printf("<tr><th>성별</th>\n"
          + " <td><select name='gender'>\n"
          + " <option value='M' %s>남자</option>\n"
          + " <option value='W' %s>여자</option></select></td></tr>\n",
          (user.getGender() == 'M' ? "selected" : ""),
          (user.getGender() == 'W' ? "selected" : ""));
      out.println("</table>");

      out.println("<div>");
      out.println("<button>변경</button>");
      out.println("<button type='reset'>초기화</button>");
      out.printf("<a href='/user/delete?no=%d'>삭제</a>\n", user.getNo());
      out.println("<a href='/user/list'>목록</a>\n");
      out.println("</div>");
      out.println("</form>");
    }

    request.getRequestDispatcher("/footer").include(request, response);

    out.println("</body>");
    out.println("</html>");

  }
}