package nc7.mapp.handler.Grade;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.dao.GradeDao;
import nc7.mapp.dao.MySQLGradeDao;
import nc7.mapp.vo.Grade;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet("/grade/add")
public class GradeAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 파라미터 값들을 가져오는 코드
            String name = request.getParameter("name");
            
            // 현재 시각을 등급 객체에 설정
            Timestamp addAt = new Timestamp(System.currentTimeMillis());

            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // GradeDao를 이용하여 등급 정보 추가
            GradeDao gradeDao = new MySQLGradeDao(sqlSessionFactory);
            Grade grade = new Grade();
            grade.setName(name);
            grade.setAddAt(addAt);
            gradeDao.insert(grade);

            // 등급 목록 페이지로 리다이렉트
            response.sendRedirect(request.getContextPath() + "/grade/list");
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
