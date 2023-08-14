package nc7.mapp.handler.Country;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.dao.CountryDao;
import nc7.mapp.vo.Country;

@WebServlet("/country/update")
public class CountryUpdateServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CountryDao countryDao; // CountryDao 인스턴스를 주입받거나 초기화하는 방식으로 구현해야 합니다

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String countryNo = request.getParameter("countryNo");
        String name = request.getParameter("name");

        Country country = new Country();
        country.setCountriesNo(countryNo);
        country.setName(name);

        int result = countryDao.update(country);

        if (result > 0) {
            // 업데이트 성공 시 처리
            response.sendRedirect("/country/list");
        } else {
            // 업데이트 실패 시 처리
            response.sendRedirect("/country/list?error=updatefailed");
        }
    }
}
