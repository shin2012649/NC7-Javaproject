package nc7.mapp.handler.Film;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.dao.GradeDao;
import nc7.mapp.handler.InitServlet;
import nc7.mapp.vo.Grade;
import nc7.mapp.vo.User;

@WebServlet("/film/form")
public class FilmFormServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null || !loginUser.isManager()) {
            // Redirect to login page or handle unauthorized access
            response.sendRedirect("/login"); // Adjust the actual login page URL
            return;
        }

        // Get the list of available grades
        GradeDao gradeDao = InitServlet.gradeDao;
        List<Grade> gradeList = gradeDao.findAll();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>영화 정보 입력/수정</title>");
        out.println("</head>");
        out.println("<body>");

        // 영화 정보 입력/수정 폼 HTML 코드 작성
        out.println("<h1>영화 정보 입력/수정</h1>");
        out.println("<form action='/film/add' method='post'>");
        out.println("<label for='title'>제목:</label>");
        out.println("<input type='text' id='title' name='title'><br>");
        out.println("<label for='filmsImageUrl'>이미지 URL:</label>");
        out.println("<input type='text' id='filmsImageUrl' name='filmsImageUrl'><br>");

        out.println("<label for='gradeNo'>등급:</label>");
        out.println("<select id='gradeNo' name='gradeNo'>");
        for (Grade grade : gradeList) {
            out.println("<option value='" + grade.getGradeNo() + "'>" + grade.getName() + "</option>");
        }
        out.println("</select><br>");

        out.println("<label for='descriptions'>설명:</label>");
        out.println("<textarea id='descriptions' name='descriptions'></textarea><br>");
        out.println("<label for='runningTime'>상영 시간:</label>");
        out.println("<input type='text' id='runningTime' name='runningTime'><br>");
        out.println("<label for='releasedDate'>개봉일:</label>");
        out.println("<input type='text' id='releasedDate' name='releasedDate'><br>");
        out.println("<input type='submit' value='영화 정보 저장'>");
        out.println("</form>");

        out.println("</body>");
        out.println("</html>");
    }
}
