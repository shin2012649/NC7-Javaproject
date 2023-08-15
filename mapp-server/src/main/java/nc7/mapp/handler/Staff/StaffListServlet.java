package nc7.mapp.handler.Staff;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.dao.StaffDao;
import nc7.mapp.dao.MySQLStaffDao;
import nc7.mapp.vo.Staff;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet("/staff/list")
public class StaffListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // StaffDao를 이용하여 모든 스태프 정보 조회
            StaffDao staffDao = new MySQLStaffDao(sqlSessionFactory);
            List<Staff> staffList = staffDao.findAll();

            // 조회된 스태프 정보를 request에 저장하고, JSP로 포워드
            request.setAttribute("staffList", staffList);
            request.getRequestDispatcher("/staff_list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
