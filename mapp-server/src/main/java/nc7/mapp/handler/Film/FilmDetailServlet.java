package nc7.mapp.handler.Film;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.dao.FilmDao;
import nc7.mapp.handler.InitServlet;
import nc7.mapp.vo.Film;

@WebServlet("/film/detail")
public class FilmDetailServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int filmNo = Integer.parseInt(request.getParameter("filmNo"));

        // TODO: Perform film retrieval using MyBatis here
        // You need to call the appropriate MyBatis mapper method to retrieve the film details
        FilmDao filmDao = InitServlet.filmDao;
        Film film = filmDao.findByNo(filmNo);

        request.setAttribute("film", film);

        // Forward the request to the film detail JSP page
        response.sendRedirect("/film/list");
    }
}
