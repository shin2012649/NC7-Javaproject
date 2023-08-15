package nc7.mapp.handler.FilmSrc;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.dao.FilmSrcDao;
import nc7.mapp.dao.MySQLFilmSrcDao;
import nc7.mapp.vo.FilmSrc;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet("/filmsrc/list")
public class FilmSrcListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // FilmSrcDao를 이용하여 데이터베이스에서 영화 소스 목록 조회
            FilmSrcDao filmSrcDao = new MySQLFilmSrcDao(sqlSessionFactory);
            List<FilmSrc> filmSrcList = filmSrcDao.findByFilmNo(0);

            // 조회된 영화 소스 목록을 사용하여 필요한 처리 작업 수행

            // 필요한 처리 작업 결과를 리퀘스트에 저장하여 JSP로 전달

            // JSP로 포워드
            // request.getRequestDispatcher("/filmsrc_list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
