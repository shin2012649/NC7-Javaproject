package nc7.mapp.handler.Film;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.dao.FilmDao;
import nc7.mapp.handler.InitServlet;
import nc7.mapp.vo.Film;
import nc7.mapp.vo.Grade;
import nc7.mapp.vo.User;

@WebServlet("/film/add")
public class FilmAddServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null || !loginUser.isManager()) {
            // Redirect to login page or handle unauthorized access
            response.sendRedirect("/login"); // Adjust the actual login page URL
            return;
        }

        String title = request.getParameter("title");
        String filmsImageUrl = request.getParameter("filmsImageUrl");
        int gradeNo = Integer.parseInt(request.getParameter("gradeNo"));
        String descriptions = request.getParameter("descriptions");
        int runningTime = Integer.parseInt(request.getParameter("runningTime"));
        LocalDate releasedDate = LocalDate.parse(request.getParameter("releasedDate"));

        Film film = new Film();
        film.setTitle(title);
        film.setFilmsImageUrl(filmsImageUrl);
        Grade grade = new Grade();
        grade.setGradeNo(gradeNo);
        film.setDescriptions(descriptions);
        film.setRunningTime(runningTime);
        film.setReleasedDate(releasedDate);

        // TODO: Perform database insertion using MyBatis here
        // You need to call the appropriate MyBatis mapper method to insert the film
        // For example:
         FilmDao filmDao = InitServlet.filmDao;
         filmDao.insert(film);

        // Redirect to the film list page
        response.sendRedirect("/film/list");
    }
}
