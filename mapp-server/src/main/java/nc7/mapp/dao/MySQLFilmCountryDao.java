package nc7.mapp.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.mapp.vo.FilmCountry;

import java.util.List;

public class MySQLFilmCountryDao implements FilmCountryDao {

    private SqlSessionFactory sqlSessionFactory;

    public MySQLFilmCountryDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(FilmCountry filmCountry) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.insert("nc7.mapp.dao.FilmCountryDao.insert", filmCountry);
        }
    }

    @Override
    public List<FilmCountry> findByFilmNo(int filmNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.FilmCountryDao.findByFilmNo", filmNo);
        }
    }

    @Override
    public int delete(int filmNo, String countryNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            FilmCountry filmCountry = new FilmCountry();
            filmCountry.setFilmsNo(filmNo);
            filmCountry.setCountriesNo(countryNo);
            return sqlSession.delete("nc7.mapp.dao.FilmCountryDao.delete", filmCountry);
        }
    }
}
