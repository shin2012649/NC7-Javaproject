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

@WebServlet("/filmcountry/add")
public class FilmCountryAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 파라미터 값들을 가져오는 코드
            int filmNo = Integer.parseInt(request.getParameter("filmNo"));
            String countryNo = request.getParameter("countryNo");

            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // FilmCountry 객체 생성 및 값 설정
            FilmCountry filmCountry = new FilmCountry();
            filmCountry.setFilmsNo(filmNo);
            filmCountry.setCountriesNo(countryNo);

            // FilmCountryDao를 이용하여 데이터베이스에 추가
            FilmCountryDao filmCountryDao = new MySQLFilmCountryDao(sqlSessionFactory);
            filmCountryDao.insert(filmCountry);

            // 처리 완료 후 리다이렉트
            response.sendRedirect(request.getContextPath() + "/filmcountry/list"); // filmcountry/list는 FilmCountry 목록을 보여주는 페이지로 가정합니다.
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
