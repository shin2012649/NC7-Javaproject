package nc7.mapp.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.mapp.vo.FilmGenre;

import java.util.List;

public class MySQLFilmGenreDao implements FilmGenreDao {

    private SqlSessionFactory sqlSessionFactory;

    public MySQLFilmGenreDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(FilmGenre filmGenre) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.insert("nc7.mapp.dao.FilmGenreDao.insert", filmGenre);
        }
    }

    @Override
    public List<FilmGenre> findByFilmNo(int filmNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.FilmGenreDao.findByFilmNo", filmNo);
        }
    }

    @Override
    public List<FilmGenre> findByGenreNo(int genreNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.FilmGenreDao.findByGenreNo", genreNo);
        }
    }

    @Override
    public int deleteByFilmNo(int filmNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.delete("nc7.mapp.dao.FilmGenreDao.deleteByFilmNo", filmNo);
        }
    }

    @Override
    public int deleteByGenreNo(int genreNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.delete("nc7.mapp.dao.FilmGenreDao.deleteByGenreNo", genreNo);
        }
    }
}
