package nc7.mapp.handler.Country;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.dao.CountryDao;
import nc7.mapp.vo.Country;

@WebServlet("/country/list")
public class CountryListServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CountryDao countryDao; // CountryDao 인스턴스를 주입받거나 초기화하는 방식으로 구현해야 합니다

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Country> countryList;

        countryList = countryDao.findAll();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>국가 목록</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>국가 목록</h1>");
        out.println("<table border='1'>");
        out.println("<thead>");
        out.println("  <tr><th>국가 번호</th> <th>이름</th></tr>");
        out.println("</thead>");
        out.println("<tbody>");

        for (Country country : countryList) {
            out.printf("<tr><td>%s</td> <td>%s</td></tr>\n", country.getCountriesNo(), country.getName());
        }

        out.println("</tbody>");
        out.println("</table>");
        out.println("<a href='/country/add-form'>국가 추가</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
