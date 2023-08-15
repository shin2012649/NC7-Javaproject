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

@WebServlet("/staff/form")
public class StaffFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // StaffDao를 이용하여 스태프 정보 조회 (새로운 스태프 정보 입력을 위해 사용)
            StaffDao staffDao = new MySQLStaffDao(sqlSessionFactory);

            // JSP로 포워드하기 전에 초기화할 데이터를 request에 저장
            request.setAttribute("staff", new Staff());
            // 사용 가능한 다른 데이터도 필요하다면 여기에 추가
            
            // JSP로 포워드
            request.getRequestDispatcher("/staff_form.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
