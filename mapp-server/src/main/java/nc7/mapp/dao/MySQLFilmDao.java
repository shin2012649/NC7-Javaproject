package nc7.mapp.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.mapp.vo.Film;

import java.util.List;

public class MySQLFilmDao implements FilmDao {

    private SqlSessionFactory sqlSessionFactory;

    public MySQLFilmDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(Film film) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.insert("nc7.mapp.dao.FilmDao.insert", film);
        }
    }

    @Override
    public List<Film> findAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.FilmDao.findAll");
        }
    }

    @Override
    public Film findByNo(int filmNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectOne("nc7.mapp.dao.FilmDao.findByNo", filmNo);
        }
    }

    @Override
    public int update(Film film) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.update("nc7.mapp.dao.FilmDao.update", film);
        }
    }

    @Override
    public int delete(int filmNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.delete("nc7.mapp.dao.FilmDao.delete", filmNo);
        }
    }
}
