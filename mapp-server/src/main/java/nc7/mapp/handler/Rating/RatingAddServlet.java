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

@WebServlet("/rating/add")
public class RatingAddServlet extends HttpServlet {
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
            Rating newRating = new Rating();
            newRating.setUsersNo((userNo)); // 이 부분은 User 객체 생성해서 설정해야 함
            newRating.setFilmsNo((filmNo)); // 이 부분은 Film 객체 생성해서 설정해야 함
            newRating.setRatingScore(ratingScore);

            // RatingDao를 이용하여 등급 정보 추가
            RatingDao ratingDao = new MySQLRatingDao(sqlSessionFactory);
            ratingDao.insert(newRating);

            // 추가 완료 후 리다이렉트 또는 응답 처리
            // response.sendRedirect(request.getContextPath() + "/rating/list"); // 추가 후 목록 페이지로 리다이렉트
            // 또는 응답 처리 코드 작성
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
