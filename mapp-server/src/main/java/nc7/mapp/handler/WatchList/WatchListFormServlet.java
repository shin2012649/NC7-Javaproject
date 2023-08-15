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

@WebServlet("/watchlist/form")
public class WatchListFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 사용자 번호를 폼에 전달하여 JSP로 포워드
            int usersNo = Integer.parseInt(request.getParameter("usersNo"));
            request.setAttribute("usersNo", usersNo);
            request.getRequestDispatcher("/watchlist_form.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 폼에서 입력받은 사용자 번호와 영화 번호를 가져와 WatchListDao를 이용하여 관심 목록 추가
            int usersNo = Integer.parseInt(request.getParameter("usersNo"));
            int filmsNo = Integer.parseInt(request.getParameter("filmsNo"));
            WatchList watchList = new WatchList(usersNo, filmsNo);

            WatchListDao watchListDao = new MySQLWatchListDao(sqlSessionFactory);
            watchListDao.insert(watchList);

            // 관심 목록 추가 후 사용자의 관심 목록 페이지로 리다이렉트
            response.sendRedirect(request.getContextPath() + "/watchlist/detail?usersNo=" + usersNo);
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
