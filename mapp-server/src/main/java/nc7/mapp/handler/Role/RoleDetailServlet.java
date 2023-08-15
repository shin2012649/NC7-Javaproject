package nc7.mapp.handler.Role;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.dao.MySQLRoleDao;
import nc7.mapp.dao.RoleDao;
import nc7.mapp.vo.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet("/role/detail")
public class RoleDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 파라미터 값들을 가져오는 코드
            int roleNo = Integer.parseInt(request.getParameter("roleNo"));

            // RoleDao를 이용하여 역할 정보 조회
            RoleDao roleDao = new MySQLRoleDao(sqlSessionFactory);
            Role role = roleDao.findByRoleNo(roleNo);

            // 조회된 역할 정보를 request에 저장하여 JSP로 전달
            request.setAttribute("role", role);

            // JSP로 포워드
            request.getRequestDispatcher("/role_detail.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
