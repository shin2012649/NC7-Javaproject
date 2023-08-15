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
import nc7.mapp.vo.Src;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@WebServlet("/src/update")
public class SrcUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 폼에서 전달받은 데이터 가져오기
            int srcNo = Integer.parseInt(request.getParameter("srcNo"));
            String newName = request.getParameter("newName");

            // 업데이트할 소스 정보 생성
            Src updatedSrc = new Src();
            updatedSrc.setSrcNo(srcNo);
            updatedSrc.setName(newName);

            // SrcDao를 이용하여 소스 정보 업데이트
            SrcDao srcDao = new MySQLSrcDao(sqlSessionFactory);
            srcDao.update(updatedSrc);

            // 업데이트 성공 후 처리 (예: 메시지 출력, 페이지 이동 등)
            response.sendRedirect(request.getContextPath() + "/src/list");
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
