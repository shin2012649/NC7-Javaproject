package nc7.mapp.handler.Country;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nc7.mapp.dao.CountryDao;
import nc7.mapp.vo.Country;
import nc7.mapp.vo.User;

@WebServlet("/country/add")
public class CountryAddServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CountryDao countryDao;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null || !loginUser.isManager()) {
            // 적절한 페이지로 리다이렉트합니다 (예: 접근 거부 페이지 또는 로그인 페이지)
            response.sendRedirect("/access-denied"); // 필요에 따라 URL을 조정합니다
            return;
        }

        String countryNo = request.getParameter("countryNo");
        String name = request.getParameter("name");

        Country country = new Country();
        country.setCountriesNo(countryNo);
        country.setName(name);

        countryDao.insert(country);

        response.sendRedirect("/country/list");
    }
}
