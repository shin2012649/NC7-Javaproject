package nc7.mapp.handler.Genre;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.dao.GenreDao;
import nc7.mapp.dao.MySQLGenreDao;
import nc7.mapp.vo.Genre;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet("/genre/list")
public class GenreListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // GenreDao를 이용하여 모든 장르 정보 조회
            GenreDao genreDao = new MySQLGenreDao(sqlSessionFactory);
            List<Genre> genreList = genreDao.findAll();

            // 조회된 장르 정보를 request에 저장하고, JSP로 포워드
            request.setAttribute("genreList", genreList);
            request.getRequestDispatcher("/genre_list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
