package nc7.mapp.handler.FilmActor;

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
import nc7.mapp.dao.FilmActorDao;
import nc7.mapp.dao.MySQLFilmActorDao;
import nc7.mapp.vo.FilmActor;

@WebServlet("/filmactor/update")
public class FilmActorUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 파라미터 값들을 가져오는 코드
            int filmNo = Integer.parseInt(request.getParameter("filmNo"));
            int actorNo = Integer.parseInt(request.getParameter("actorNo"));
            int newRoleNo = Integer.parseInt(request.getParameter("newRoleNo"));

            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // FilmActorDao를 이용하여 데이터베이스에서 FilmActor 조회 및 업데이트
            FilmActorDao filmActorDao = new MySQLFilmActorDao(sqlSessionFactory);
            FilmActor filmActor = (FilmActor) filmActorDao.findByFilmNo(filmNo);
            
            if (filmActor != null) {
                filmActor.setRolesNo(newRoleNo);
                filmActorDao.update(filmActor);
            }

            // 처리 완료 후 리다이렉트
            response.sendRedirect(request.getContextPath() + "/filmactor/list"); // filmactor/list는 FilmActor 목록을 보여주는 페이지로 가정합니다.
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}