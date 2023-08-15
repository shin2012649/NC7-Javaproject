package nc7.mapp.handler.Film;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.dao.FilmDao;
import nc7.mapp.handler.InitServlet;
import nc7.mapp.vo.Film;
import nc7.mapp.vo.User;

@WebServlet("/film/list")
public class FilmListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            // Redirect to login page or handle unauthorized access
            response.sendRedirect("/login"); // Adjust the actual login page URL
            return;
        }

        FilmDao filmDao = InitServlet.filmDao;
        List<Film> filmList = filmDao.findAll();

        response.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>영화 목록</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>영화 목록</h1>");

            if (filmList.isEmpty()) {
                out.println("<p>등록된 영화가 없습니다.</p>");
            } else {
                out.println("<ul>");

                for (Film film : filmList) {
                    out.println("<li>");
                    out.println("영화 번호: " + film.getFilmsNo() + "<br>");
                    out.println("제목: " + film.getTitle() + "<br>");
                    out.println("상영 시간: " + film.getRunningTime() + "분<br>");
                    // Add more film information here
                    out.println("<a href='/film/detail?filmNo=" + film.getFilmsNo() + "'>상세 보기</a>");
                    out.println("</li><br>");
                }

                out.println("</ul>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }
}
