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

@WebServlet("/staff/update")
public class StaffUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 폼에서 입력받은 스태프 정보를 가져와 Staff 객체에 설정
            int staffId = Integer.parseInt(request.getParameter("staffId"));
            String name = request.getParameter("name");
            String imageUrl = request.getParameter("imageUrl");
            Staff updatedStaff = new Staff(staffId, name, imageUrl);

            // StaffDao를 이용하여 스태프 정보 업데이트
            StaffDao staffDao = new MySQLStaffDao(sqlSessionFactory);
            int updatedRows = staffDao.update(updatedStaff);

            if (updatedRows > 0) {
                // 업데이트 성공 시 성공 메시지 출력
                response.getWriter().println("Staff information updated successfully.");
            } else {
                response.getWriter().println("Failed to update staff information.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
