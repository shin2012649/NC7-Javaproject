package nc7.mapp.handler.User;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.dao.UserDao;
import nc7.mapp.vo.User;

@WebServlet("/user/detail")
public class UserDetailServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDao userDao;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userNo = Integer.parseInt(request.getParameter("userNo"));

        User user = userDao.findBy(userNo);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>사용자 상세 정보</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>사용자 상세 정보</h1>");

        if (user == null) {
          out.println("<p>해당 사용자 정보가 없습니다!</p>");
      } else {
          out.println("<table border='1'>");
          out.printf("<tr><th>번호</th><td>%d</td></tr>\n", user.getUsersNo());
          out.printf("<tr><th>이름</th><td>%s</td></tr>\n", user.getName());
          out.printf("<tr><th>이메일</th><td>%s</td></tr>\n", user.getEmail());
          out.printf("<tr><th>프로필 이미지</th><td><img src='%s' alt='프로필 이미지' width='100'></td></tr>\n", user.getProfileImageUrl());
          out.printf("<tr><th>생년월일</th><td>%s</td></tr>\n", user.getDateOfBirth());
          out.printf("<tr><th>가입일</th><td>%s</td></tr>\n", user.getCreatedAt());

          if (user.isManager()) {
              out.println("<tr><th>관리자 여부</th><td>Yes</td></tr>");
          } else {
              out.println("<tr><th>관리자 여부</th><td>No</td></tr>");
          }

          out.println("</table>");
      }

        out.println("<div>");
        out.println("<a href='/auth/form.html'>로그인</a>");
        out.println("<a href='/'>홈으로</a>");
        out.println("</div>");

        out.println("</body>");
        out.println("</html>");
    }
}
