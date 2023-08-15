package nc7.mapp.handler.Src;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.dao.SrcDao;
import nc7.mapp.dao.MySQLSrcDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet("/src/delete")
public class SrcDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 전달된 소스 번호를 받아옴
            int srcNo = Integer.parseInt(request.getParameter("srcNo"));

            // SrcDao를 이용하여 소스 정보 삭제
            SrcDao srcDao = new MySQLSrcDao(sqlSessionFactory);
            srcDao.delete(srcNo);

            // 소스 정보 삭제 후 다른 페이지로 리다이렉트 또는 메시지를 표시
            response.sendRedirect(request.getContextPath() + "/src/list"); // 예시: 소스 정보 리스트 페이지로 리다이렉트
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
