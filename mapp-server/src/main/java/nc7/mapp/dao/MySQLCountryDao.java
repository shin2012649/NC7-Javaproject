package nc7.mapp.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.mapp.vo.Country;

import java.util.List;

public class MySQLCountryDao implements CountryDao {

    private SqlSessionFactory sqlSessionFactory;

    public MySQLCountryDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(Country country) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.insert("nc7.mapp.dao.CountryDao.insert", country);
        }
    }

    @Override
    public Country findByCountryNo(String countryNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectOne("nc7.mapp.dao.CountryDao.findByCountryNo", countryNo);
        }
    }

    @Override
    public List<Country> findAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.CountryDao.findAll");
        }
    }

    @Override
    public int update(Country country) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.update("nc7.mapp.dao.CountryDao.update", country);
        }
    }

    @Override
    public int delete(String countryNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.delete("nc7.mapp.dao.CountryDao.delete", countryNo);
        }
    }
}
