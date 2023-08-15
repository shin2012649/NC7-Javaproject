package nc7.mapp.handler.FilmCountry;
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
import nc7.mapp.dao.FilmCountryDao;
import nc7.mapp.dao.MySQLFilmCountryDao;
import nc7.mapp.vo.FilmCountry;

@WebServlet("/filmcountry/detail")
public class FilmCountryDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 파라미터 값들을 가져오는 코드
            int filmNo = Integer.parseInt(request.getParameter("filmNo"));
            String countryNo = request.getParameter("countryNo");

            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // FilmCountryDao를 이용하여 데이터베이스에서 FilmCountry 조회
            FilmCountryDao filmCountryDao = new MySQLFilmCountryDao(sqlSessionFactory);
            FilmCountry filmCountry = (FilmCountry) filmCountryDao.findByFilmNo(filmNo);

            // 조회된 FilmCountry 객체를 JSP로 전달하여 상세 정보를 보여줌
            request.setAttribute("filmCountry", filmCountry);
            request.getRequestDispatcher("/filmcountry_detail.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}

