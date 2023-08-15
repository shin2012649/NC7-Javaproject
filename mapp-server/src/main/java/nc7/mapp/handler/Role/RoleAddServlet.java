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

@WebServlet("/role/add")
public class RoleAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 파라미터 값들을 가져오는 코드
            String roleName = request.getParameter("roleName");

            // Role 객체 생성 및 값 설정
            Role role = new Role();
            role.setName(roleName);

            // RoleDao를 이용하여 역할 정보 추가
            RoleDao roleDao = new MySQLRoleDao(sqlSessionFactory);
            roleDao.insert(role);

            // 추가 성공 시 리다이렉트
            response.sendRedirect(request.getContextPath() + "/role/list");
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
