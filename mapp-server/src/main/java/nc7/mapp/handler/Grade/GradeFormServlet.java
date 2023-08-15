package nc7.mapp.handler.Grade;

import java.io.IOException;
import java.io.InputStream;
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

@WebServlet("/grade/form")
public class GradeFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 파라미터 값들을 가져오는 코드 (등급 번호가 있을 경우 수정 폼으로 사용)
            String gradeNoParam = request.getParameter("gradeNo");

            // 등급 번호가 있을 경우 해당 등급 정보 조회
            if (gradeNoParam != null && !gradeNoParam.isEmpty()) {
                int gradeNo = Integer.parseInt(gradeNoParam);

                // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
                String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
                InputStream inputStream = Resources.getResourceAsStream(resource);
                SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

                // GradeDao를 이용하여 등급 정보 조회
                GradeDao gradeDao = new MySQLGradeDao(sqlSessionFactory);
                Grade grade = gradeDao.findByNo(gradeNo);

                // 조회한 등급 정보를 request에 저장하고, JSP로 포워드
                request.setAttribute("grade", grade);
            }

            // JSP로 포워드
            request.getRequestDispatcher("/grade_form.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
