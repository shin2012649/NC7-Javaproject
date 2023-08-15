package nc7.mapp.handler.Staff;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.dao.StaffDao;
import nc7.mapp.dao.MySQLStaffDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet("/staff/delete")
public class StaffDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 파라미터에서 삭제할 스태프 번호 가져오기
            int staffNo = Integer.parseInt(request.getParameter("staffNo"));

            // StaffDao를 이용하여 스태프 정보 삭제
            StaffDao staffDao = new MySQLStaffDao(sqlSessionFactory);
            staffDao.delete(staffNo);

            // 삭제 성공 후 처리 (예: 메시지 출력, 페이지 이동 등)
            response.sendRedirect(request.getContextPath() + "/staff/list");
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
