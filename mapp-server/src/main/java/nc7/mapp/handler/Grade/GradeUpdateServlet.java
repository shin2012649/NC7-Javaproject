package nc7.mapp.handler.Grade;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import nc7.mapp.dao.GradeDao;
import nc7.mapp.dao.MySQLGradeDao;
import nc7.mapp.vo.Grade;

@WebServlet("/grade/update")
public class GradeUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 파라미터 값들을 가져오는 코드
            int gradeNo = Integer.parseInt(request.getParameter("gradeNo"));
            String name = request.getParameter("name");
            // 필요한 다른 파라미터들도 가져와서 사용

            // Grade 객체 생성 및 값 설정
            Grade updatedGrade = new Grade();
            updatedGrade.setGradeNo(gradeNo);
            updatedGrade.setName(name);
            // 필요한 다른 값들도 설정

            // GradeDao를 이용하여 등급 정보 수정
            GradeDao gradeDao = new MySQLGradeDao(sqlSessionFactory);
            int rowsAffected = gradeDao.update(updatedGrade);

            // 수정 완료 후 리다이렉트
            if (rowsAffected > 0) {
                response.sendRedirect(request.getContextPath() + "/grade/list"); // 수정 후 목록 페이지로 리다이렉트
            } else {
                // 수정 실패 처리
                // 오류 메시지나 예외 처리 코드
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
