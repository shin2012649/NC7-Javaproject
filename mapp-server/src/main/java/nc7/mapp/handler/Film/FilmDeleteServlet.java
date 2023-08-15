package nc7.mapp.handler.Film;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.dao.FilmDao;
import nc7.mapp.handler.InitServlet;
import nc7.mapp.vo.User;

@WebServlet("/film/delete")
public class FilmDeleteServlet extends HttpServlet {

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

        int filmNo = Integer.parseInt(request.getParameter("filmNo"));

        // TODO: Perform film deletion using MyBatis here
        // You need to call the appropriate MyBatis mapper method to delete the film
        FilmDao filmDao = InitServlet.filmDao;
        filmDao.delete(filmNo);

        // Redirect to the film list page
        response.sendRedirect("/film/list");
    }
}
