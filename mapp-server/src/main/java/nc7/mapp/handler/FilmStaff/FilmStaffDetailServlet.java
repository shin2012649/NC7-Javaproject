package nc7.mapp.handler.FilmStaff;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.dao.FilmStaffDao;
import nc7.mapp.dao.MySQLFilmStaffDao;
import nc7.mapp.vo.FilmStaff;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet("/filmstaff/detail")
public class FilmStaffDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 파라미터 값들을 가져오는 코드
            int filmStaffNo = Integer.parseInt(request.getParameter("filmStaffNo"));

            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // FilmStaffDao를 이용하여 데이터베이스에서 영화 스텝 정보 조회
            FilmStaffDao filmStaffDao = new MySQLFilmStaffDao(sqlSessionFactory);
            FilmStaff filmStaff = (FilmStaff) filmStaffDao.findByFilmNo(filmStaffNo);

            // 조회된 영화 스텝 정보를 request에 저장하여 JSP로 전달
            request.setAttribute("filmStaff", filmStaff);

            // JSP로 포워드
            request.getRequestDispatcher("/filmstaff_detail.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
