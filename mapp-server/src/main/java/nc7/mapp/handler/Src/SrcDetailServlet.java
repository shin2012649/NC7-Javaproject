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

@WebServlet("/src/detail")
public class SrcDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // MyBatis 설정 파일을 로드하여 SqlSessionFactory 생성
            String resource = "mybatis-config.xml"; // MyBatis 설정 파일의 경로
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // 전달된 소스 번호를 받아옴
            int srcNo = Integer.parseInt(request.getParameter("srcNo"));

            // SrcDao를 이용하여 소스 정보 조회
            SrcDao srcDao = new MySQLSrcDao(sqlSessionFactory);
            Src src = srcDao.findBySrcNo(srcNo);

            // 조회된 소스 정보를 request에 저장하고, JSP로 포워드
            request.setAttribute("src", src);
            request.getRequestDispatcher("/src_detail.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
