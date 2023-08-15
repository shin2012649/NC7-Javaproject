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

@WebServlet("/genre/update")
public class GenreUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 파라미터 값들을 가져오는 코드
            int genreNo = Integer.parseInt(request.getParameter("genreNo"));
            String updatedName = request.getParameter("updatedName");

            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // GenreDao를 이용하여 장르 정보 업데이트
            GenreDao genreDao = new MySQLGenreDao(sqlSessionFactory);
            Genre genre = new Genre();
            genre.setGenresNo(genreNo);
            genre.setName(updatedName);
            int updatedRows = genreDao.update(genre);

            if (updatedRows > 0) {
                // 업데이트 성공 시 처리
                response.sendRedirect(request.getContextPath() + "/genre/list");
            } else {
                // 업데이트 실패 시 처리
                // 오류 메시지 등을 설정하고 다시 수정 폼으로 이동
                response.sendRedirect(request.getContextPath() + "/genre/update-form?genreNo=" + genreNo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
