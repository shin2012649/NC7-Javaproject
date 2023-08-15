package nc7.mapp.handler.WatchList;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.dao.WatchListDao;
import nc7.mapp.dao.MySQLWatchListDao;
import nc7.mapp.vo.WatchList;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet("/watchlist/delete")
public class WatchListDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 폼에서 입력받은 사용자 번호와 영화 번호를 가져와 WatchList 객체에 설정
            int usersNo = Integer.parseInt(request.getParameter("usersNo"));
            int filmsNo = Integer.parseInt(request.getParameter("filmsNo"));
            WatchList watchList = new WatchList(usersNo, filmsNo);

            // WatchListDao를 이용하여 관심 목록에서 항목 삭제
            WatchListDao watchListDao = new MySQLWatchListDao(sqlSessionFactory);
            watchListDao.delete(usersNo, filmsNo);

            // 삭제 성공 시 성공 메시지 출력
            response.getWriter().println("Item removed from watchlist successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
