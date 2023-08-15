package nc7.mapp.handler.Rating;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.dao.MySQLRatingDao;
import nc7.mapp.dao.RatingDao;
import nc7.mapp.vo.Rating;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet("/rating/list")
public class RatingListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // RatingDao를 이용하여 등급 정보 조회
            RatingDao ratingDao = new MySQLRatingDao(sqlSessionFactory);
            List<Rating> ratings = ratingDao.findAll();

            // 조회된 등급 정보를 request에 저장하고, JSP로 포워드
            request.setAttribute("ratings", ratings);
            request.getRequestDispatcher("/rating_list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
