package nc7.mapp.handler.User;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.handler.InitServlet;
import nc7.mapp.vo.User;

@WebServlet("/user/update")
public class UserUpdateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            response.sendRedirect("/auth/form.html");
            return;
        }

        String inputEmail = request.getParameter("email");
        String inputPassword = request.getParameter("password");

        // 이메일과 비밀번호 확인 로직 추가 (이 부분은 실제로 사용하는 인증 방식에 맞게 변경해야 합니다)
        if (!loginUser.getEmail().equals(inputEmail) || !loginUser.getPassword().equals(inputPassword)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>사용자 정보 변경</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>사용자 정보 변경 실패</h1>");
            out.println("<p>이메일 또는 비밀번호가 일치하지 않습니다.</p>");
            out.println("</body>");
            out.println("</html>");
            return;
        }

        User updatedUser = new User();
        updatedUser.setUsersNo(loginUser.getUsersNo());
        updatedUser.setName(request.getParameter("name"));
        updatedUser.setEmail(request.getParameter("newEmail")); // 변경된 이메일 필드
        updatedUser.setPassword(request.getParameter("newPassword")); // 변경된 비밀번호 필드
        // 나머지 필드 업데이트 (생년월일, 프로필 이미지 등)

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>사용자 정보 변경</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>사용자 정보 변경</h1>");
        try {
            int result = InitServlet.userDao.update(updatedUser);
            if (result == 0) {
                out.println("<p>사용자 정보 변경 실패하였습니다.</p>");
            } else {
                out.println("<p>사용자 정보를 성공적으로 변경하였습니다!</p>");
            }
            InitServlet.sqlSessionFactory.openSession(false).commit();

        } catch (Exception e) {
            InitServlet.sqlSessionFactory.openSession(false).rollback();
            out.println("<p>사용자 정보 변경 실패하였습니다!</p>");
            e.printStackTrace();
        }
        out.println("</body>");
        out.println("</html>");
    }
}
