package nc7.mapp.handler.FilmSrc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/filmsrc/form")
public class FilmSrcFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 필요한 처리 작업 (예: 필요한 데이터 조회 등)
            
            // 필요한 데이터를 request에 저장하고, JSP로 포워드
            request.getRequestDispatcher("/filmsrc_form.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 오류 처리 코드
        }
    }
}
