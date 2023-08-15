package nc7.mapp.handler.Rating;

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
import nc7.mapp.dao.MySQLRatingDao;
import nc7.mapp.dao.RatingDao;
import nc7.mapp.vo.Rating;

@WebServlet("/rating/detail")
public class RatingDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 파라미터 값들을 가져오는 코드
            int userNo = Integer.parseInt(request.getParameter("userNo"));
            int filmNo = Integer.parseInt(request.getParameter("filmNo"));

            // RatingDao를 이용하여 등급 정보 조회
            RatingDao ratingDao = new MySQLRatingDao(sqlSessionFactory);
            Rating rating = (Rating) ratingDao.findByFilmId(filmNo);

            if (rating != null) {
                // 조회된 등급 정보를 request에 저장하고, JSP로 포워드
                request.setAttribute("rating", rating);
                request.getRequestDispatcher("/rating_detail.jsp").forward(request, response);
            } else {
                // 등급 정보가 없을 경우 처리 또는 오류 처리
                // 응답 처리 코드 작성
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
