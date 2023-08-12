package nc7.mapp.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.mapp.vo.Rating;

import java.util.List;

public class MySQLRatingDao implements RatingDao {

    private SqlSessionFactory sqlSessionFactory;

    public MySQLRatingDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(Rating rating) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.insert("nc7.mapp.dao.RatingDao.insert", rating);
        }
    }

    @Override
    public List<Rating> findAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.RatingDao.findAll");
        }
    }

    @Override
    public Rating findByUserAndFilm(int userNo, int filmNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            Rating searchCriteria = new Rating();
            searchCriteria.setUsersNo(userNo);
            searchCriteria.setFilmsNo(filmNo);
            return sqlSession.selectOne("nc7.mapp.dao.RatingDao.findByUserAndFilm", searchCriteria);
        }
    }

    @Override
    public int update(Rating rating) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.update("nc7.mapp.dao.RatingDao.update", rating);
        }
    }

    @Override
    public int delete(int userNo, int filmNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            Rating searchCriteria = new Rating();
            searchCriteria.setUsersNo(userNo);
            searchCriteria.setFilmsNo(filmNo);
            return sqlSession.delete("nc7.mapp.dao.RatingDao.delete", searchCriteria);
        }
    }
}
