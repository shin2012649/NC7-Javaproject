package nc7.mapp.handler.Rating;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import nc7.mapp.dao.FilmDao;
import nc7.mapp.dao.MySQLFilmDao;
import nc7.mapp.dao.MySQLUserDao;
import nc7.mapp.dao.UserDao;
import nc7.mapp.vo.Film;
import nc7.mapp.vo.User;

@WebServlet("/rating/form")
public class RatingFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // UserDao, FilmDao를 이용하여 사용자와 영화 정보 조회
            UserDao userDao = new MySQLUserDao(sqlSessionFactory);
            FilmDao filmDao = new MySQLFilmDao(sqlSessionFactory);

            List<User> users = userDao.findAll();
            List<Film> films = filmDao.findAll();

            // 조회된 사용자와 영화 정보를 request에 저장하고, JSP로 포워드
            request.setAttribute("users", users);
            request.setAttribute("films", films);
            request.getRequestDispatcher("/rating_form.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
