package nc7.javaproject.Handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/events")
public class EventServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>시사회·이벤트</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>시사회·이벤트</h1>");
        out.println("<div style='margin:5px;'>");
        out.println("<p>여기에 시사회·이벤트  추가해야한다잉.</p>");
        out.println("</div>");
        out.println("<div style='margin:5px;'>");
        out.println("<a href='/winner'>당첨자 결과 발표</a>"); 
        out.println("</div>");
        out.println("<a href='/'>메인</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
