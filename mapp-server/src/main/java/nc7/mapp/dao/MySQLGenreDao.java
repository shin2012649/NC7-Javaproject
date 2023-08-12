package nc7.mapp.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.mapp.vo.Genre;

import java.util.List;

public class MySQLGenreDao implements GenreDao {

    private SqlSessionFactory sqlSessionFactory;

    public MySQLGenreDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(Genre genre) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.insert("nc7.mapp.dao.GenreDao.insert", genre);
        }
    }

    @Override
    public List<Genre> findAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.GenreDao.findAll");
        }
    }

    @Override
    public Genre findByNo(int genreNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectOne("nc7.mapp.dao.GenreDao.findByNo", genreNo);
        }
    }

    @Override
    public int update(Genre genre) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.update("nc7.mapp.dao.GenreDao.update", genre);
        }
    }

    @Override
    public int delete(int genreNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.delete("nc7.mapp.dao.GenreDao.delete", genreNo);
        }
    }
}
