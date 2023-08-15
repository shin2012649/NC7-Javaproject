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

@WebServlet("/watchlist/update")
public class WatchListUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 요청 파라미터로부터 사용자 번호와 영화 번호를 가져옴
            int usersNo = Integer.parseInt(request.getParameter("usersNo"));
            int filmsNo = Integer.parseInt(request.getParameter("filmsNo"));

            // WatchList 객체 생성 및 설정
            WatchList watchList = new WatchList();
            watchList.setUsersNo(usersNo);
            watchList.setFilmsNo(filmsNo);

            // WatchListDao를 이용하여 관심 목록 업데이트
            WatchListDao watchListDao = new MySQLWatchListDao(sqlSessionFactory);
            int rowsUpdated = watchListDao.update(watchList);

            // 업데이트 성공 시 메시지를 설정하고, 실패 시 메시지를 설정하여 리다이렉트
            if (rowsUpdated > 0) {
                request.getSession().setAttribute("message", "관심 목록이 업데이트되었습니다.");
            } else {
                request.getSession().setAttribute("errorMessage", "관심 목록 업데이트에 실패했습니다.");
            }
            response.sendRedirect(request.getContextPath() + "/watchlist/list?usersNo=" + usersNo);
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
