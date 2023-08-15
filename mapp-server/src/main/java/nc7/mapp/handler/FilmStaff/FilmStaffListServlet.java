package nc7.mapp.handler.FilmStaff;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
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

@WebServlet("/filmstaff/list")
public class FilmStaffListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // FilmStaffDao를 이용하여 모든 영화 스텝 정보 조회
            FilmStaffDao filmStaffDao = new MySQLFilmStaffDao(sqlSessionFactory);
            List<FilmStaff> filmStaffList = filmStaffDao.findByFilmNo(0);

            // 조회된 영화 스텝 정보를 request에 저장하여 JSP로 전달
            request.setAttribute("filmStaffList", filmStaffList);

            // JSP로 포워드
            request.getRequestDispatcher("/filmstaff_list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
