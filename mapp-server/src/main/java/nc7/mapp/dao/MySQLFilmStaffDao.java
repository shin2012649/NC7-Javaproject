package nc7.mapp.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.mapp.vo.FilmStaff;

import java.util.List;

public class MySQLFilmStaffDao implements FilmStaffDao {

    private SqlSessionFactory sqlSessionFactory;

    public MySQLFilmStaffDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(FilmStaff filmStaff) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.insert("nc7.mapp.dao.FilmStaffDao.insert", filmStaff);
        }
    }

    @Override
    public List<FilmStaff> findByFilmNo(int filmNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.FilmStaffDao.findByFilmNo", filmNo);
        }
    }

    @Override
    public int deleteByFilmNo(int filmNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.delete("nc7.mapp.dao.FilmStaffDao.deleteByFilmNo", filmNo);
        }
    }
}
