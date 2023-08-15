package nc7.mapp.handler.Genre;

import java.io.IOException;
import java.io.InputStream;
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

@WebServlet("/genre/add")
public class GenreAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 파라미터 값들을 가져오는 코드
            String genreName = request.getParameter("genreName");

            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // GenreDao를 이용하여 장르 정보 추가
            GenreDao genreDao = new MySQLGenreDao(sqlSessionFactory);
            Genre genre = new Genre();
            genre.setName(genreName);
            genreDao.insert(genre);

            // 처리 완료 후 리다이렉트
            response.sendRedirect(request.getContextPath() + "/genre/list"); // genre/list는 Genre 목록을 보여주는 페이지로 가정합니다.
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
