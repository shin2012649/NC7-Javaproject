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
import nc7.mapp.vo.Staff;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet("/staff/detail")
public class StaffDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 파라미터에서 조회할 스태프 번호 가져오기
            int staffNo = Integer.parseInt(request.getParameter("staffNo"));

            // StaffDao를 이용하여 스태프 정보 조회
            StaffDao staffDao = new MySQLStaffDao(sqlSessionFactory);
            Staff staff = staffDao.findByStaffNo(staffNo);

            // 조회된 스태프 정보를 request에 저장하고, JSP로 포워드
            request.setAttribute("staff", staff);
            request.getRequestDispatcher("/staff_detail.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
