package nc7.mapp.handler.Grade;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
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

@WebServlet("/grade/list")
public class GradeListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // GradeDao를 이용하여 등급 정보 목록 조회
            GradeDao gradeDao = new MySQLGradeDao(sqlSessionFactory);
            List<Grade> gradeList = gradeDao.findAll();

            // 조회한 등급 정보 목록을 request에 저장하고, JSP로 포워드
            request.setAttribute("gradeList", gradeList);
            request.getRequestDispatcher("/grade_list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
