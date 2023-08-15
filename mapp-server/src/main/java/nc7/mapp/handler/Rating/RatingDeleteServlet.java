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

@WebServlet("/rating/delete")
public class RatingDeleteServlet extends HttpServlet {
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

            // RatingDao를 이용하여 등급 정보 삭제
            RatingDao ratingDao = new MySQLRatingDao(sqlSessionFactory);
            int deletedRows = ratingDao.delete(userNo, filmNo);

            if (deletedRows > 0) {
                // 삭제 완료 후 리다이렉트 또는 응답 처리
                // response.sendRedirect(request.getContextPath() + "/rating/list"); // 삭제 후 목록 페이지로 리다이렉트
                // 또는 응답 처리 코드 작성
            } else {
                // 삭제 실패 처리 또는 오류 처리
                // 응답 처리 코드 작성
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
