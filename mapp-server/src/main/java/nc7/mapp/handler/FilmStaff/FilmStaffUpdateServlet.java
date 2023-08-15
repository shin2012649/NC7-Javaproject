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

@WebServlet("/filmstaff/update")
public class FilmStaffUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 파라미터 값들을 가져오는 코드
            int filmStaffNo = Integer.parseInt(request.getParameter("filmStaffNo"));
            String role = request.getParameter("role");
            String position = request.getParameter("position");

            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // FilmStaffDao를 이용하여 영화 스텝 정보 업데이트
            FilmStaffDao filmStaffDao = new MySQLFilmStaffDao(sqlSessionFactory);
            FilmStaff filmStaff = new FilmStaff();
            filmStaff.setFilmsStaffNo(filmStaffNo);
            filmStaff.setRole(role);
            filmStaff.setPosition(position);
            filmStaffDao.insert(filmStaff);

            // 처리 완료 후 리다이렉트
            response.sendRedirect(request.getContextPath() + "/filmstaff/list"); // filmstaff/list는 FilmStaff 목록을 보여주는 페이지로 가정합니다.
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
