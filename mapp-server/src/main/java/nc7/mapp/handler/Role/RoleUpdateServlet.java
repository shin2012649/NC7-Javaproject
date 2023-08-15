package nc7.mapp.handler.Role;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.dao.RoleDao;
import nc7.mapp.dao.MySQLRoleDao;
import nc7.mapp.vo.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet("/role/update")
public class RoleUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 전달된 역할 정보를 받아옴
            int roleId = Integer.parseInt(request.getParameter("roleId"));
            String roleName = request.getParameter("roleName");

            // 역할 정보 생성
            Role updatedRole = new Role();
            updatedRole.setRolesNo(roleId);
            updatedRole.setName(roleName);

            // RoleDao를 이용하여 역할 정보 업데이트
            RoleDao roleDao = new MySQLRoleDao(sqlSessionFactory);
            int result = roleDao.update(updatedRole);

            if (result > 0) {
                // 업데이트 성공 시 역할 정보 리스트 페이지로 리다이렉트
                response.sendRedirect(request.getContextPath() + "/role/list");
            } else {
                // 업데이트 실패 시 오류 처리 코드
                // (예: 업데이트 실패 메시지를 설정하고 에러 페이지로 포워딩)
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
