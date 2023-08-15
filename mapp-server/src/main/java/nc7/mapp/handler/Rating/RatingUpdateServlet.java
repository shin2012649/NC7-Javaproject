package nc7.mapp.handler.Rating;

import java.io.IOException;
import java.io.InputStream;
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

@WebServlet("/rating/update")
public class RatingUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 파라미터 값들을 가져오는 코드
            int userNo = Integer.parseInt(request.getParameter("userNo"));
            int filmNo = Integer.parseInt(request.getParameter("filmNo"));
            int ratingScore = Integer.parseInt(request.getParameter("ratingScore"));

            // Rating 객체 생성 및 값 설정
            Rating rating = new Rating();
            rating.setUsersNo(userNo);
            rating.setFilmsNo(filmNo);
            rating.setRatingScore(ratingScore);

            // RatingDao를 이용하여 등급 정보 업데이트
            RatingDao ratingDao = new MySQLRatingDao(sqlSessionFactory);
            int result = ratingDao.update(rating);

            // 업데이트 결과에 따른 처리 (예: 업데이트 성공 시 리다이렉트 등)
            if (result > 0) {
                response.sendRedirect(request.getContextPath() + "/rating/list");
            } else {
                // 업데이트 실패 처리
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
