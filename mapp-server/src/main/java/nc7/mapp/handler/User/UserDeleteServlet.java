package nc7.mapp.handler.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.handler.InitServlet;

@WebServlet("/user/delete")
public class UserDeleteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int userNo = Integer.parseInt(request.getParameter("userNo"));

        try {
            if (InitServlet.userDao.delete(userNo) == 0) {
                throw new Exception("해당 번호의 사용자 정보가 없거나 삭제 권한이 없습니다.");
            } else {
                response.sendRedirect("/user/list");
            }
            InitServlet.sqlSessionFactory.openSession(false).commit();

        } catch (Exception e) {
            InitServlet.sqlSessionFactory.openSession(false).rollback();
            throw new RuntimeException(e);
        }
    }
}
