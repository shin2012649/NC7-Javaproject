package nc7.mapp.handler.Event;

import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.vo.Event;
import nc7.mapp.vo.User;

@WebServlet("/event/add")
public class EventAddServlet extends HttpServlet {

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
        LocalDateTime screeningDate = LocalDateTime.parse(request.getParameter("screeningDate"));
        String screeningLocation = request.getParameter("screeningLocation");
        LocalDateTime entryPeriodStart = LocalDateTime.parse(request.getParameter("entryPeriodStart"));
        LocalDateTime entryPeriodEnd = LocalDateTime.parse(request.getParameter("entryPeriodEnd"));
        LocalDateTime announcementDate = LocalDateTime.parse(request.getParameter("announcementDate"));
        int winnersCount = Integer.parseInt(request.getParameter("winnersCount"));
        String notice = request.getParameter("notice");

        Event event = new Event();
        event.setFilmsNo(filmNo);
        event.setScreeningDate(screeningDate);
        event.setScreeningLocation(screeningLocation);
        event.setEntryPeriodStart(entryPeriodStart);
        event.setEntryPeriodEnd(entryPeriodEnd);
        event.setAnnouncementDate(announcementDate);
        event.setWinnersCount(winnersCount);
        event.setNotice(notice);

        // TODO: Perform database insertion using MyBatis here
        // You need to call the appropriate MyBatis mapper method to insert the event
        // For example:
        // EventDao eventDao = InitServlet.eventDao;
        // eventDao.insertEvent(event);

        // Redirect to the film details page or event list page
        response.sendRedirect("/film/detail?filmNo=" + filmNo);
    }
}
