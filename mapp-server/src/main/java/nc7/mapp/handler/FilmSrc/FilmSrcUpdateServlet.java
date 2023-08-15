package nc7.mapp.handler.FilmSrc;

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
import nc7.mapp.dao.FilmSrcDao;
import nc7.mapp.dao.MySQLFilmSrcDao;
import nc7.mapp.vo.FilmSrc;

@WebServlet("/filmsrc/update")
public class FilmSrcUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 파라미터 값들을 가져오는 코드
            int filmNo = Integer.parseInt(request.getParameter("filmNo"));
            int srcNo = Integer.parseInt(request.getParameter("srcNo"));

            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // FilmSrc 객체 생성 및 값 설정
            FilmSrc filmSrc = new FilmSrc();
            filmSrc.setFilmsNo(filmNo);
            filmSrc.setSrcNo(srcNo);

            // FilmSrcDao를 이용하여 데이터베이스의 영화 소스 정보 업데이트
            FilmSrcDao filmSrcDao = new MySQLFilmSrcDao(sqlSessionFactory);
            filmSrcDao.insert(filmSrc);

            // 처리 완료 후 리다이렉트
            response.sendRedirect(request.getContextPath() + "/filmsrc/list"); // filmsrc/list는 FilmSrc 목록을 보여주는 페이지로 가정합니다.
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
