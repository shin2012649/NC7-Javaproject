package nc7.mapp.handler.Country;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.dao.CountryDao;
import nc7.mapp.vo.Country;

@WebServlet("/country/detail")
public class CountryDetailServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CountryDao countryDao; // CountryDao 인스턴스를 주입받거나 초기화하는 방식으로 구현해야 합니다

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String countryNo = request.getParameter("countryNo");

        Country country = countryDao.findByCountryNo(countryNo);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>국가 상세 정보</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>국가 상세 정보</h1>");

        if (country == null) {
            out.println("<p>해당 국가 정보가 없습니다!</p>");
        } else {
            out.println("<table border='1'>");
            out.printf("<tr><th style='width:120px;'>국가 번호</th>"
                    + " <td style='width:300px;'>%s</td></tr>\n", country.getCountriesNo());
            out.printf("<tr><th>이름</th>"
                    + " <td>%s</td></tr>\n", country.getName());
            out.println("</table>");
        }

        out.println("<a href='/country/list'>국가 목록</a>");
        out.println("</body>");
        out.println("</html>");
    }
}
