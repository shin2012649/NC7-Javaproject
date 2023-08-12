package nc7.mapp.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.mapp.vo.FilmSrc;

import java.util.List;

public class MySQLFilmSrcDao implements FilmSrcDao {

    private SqlSessionFactory sqlSessionFactory;

    public MySQLFilmSrcDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(FilmSrc filmSrc) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.insert("nc7.mapp.dao.FilmSrcDao.insert", filmSrc);
        }
    }

    @Override
    public List<FilmSrc> findByFilmNo(int filmNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.FilmSrcDao.findByFilmNo", filmNo);
        }
    }

    @Override
    public int deleteByFilmNo(int filmNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.delete("nc7.mapp.dao.FilmSrcDao.deleteByFilmNo", filmNo);
        }
    }
}
