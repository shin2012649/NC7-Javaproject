package nc7.mapp.dao;

import java.util.List;
import nc7.mapp.vo.Country;

public interface CountryDao {
    void insert(Country country);
    Country findByCountryNo(String countryNo);
    List<Country> findAll();
    int update(Country country);
    int delete(String countryNo);
}
