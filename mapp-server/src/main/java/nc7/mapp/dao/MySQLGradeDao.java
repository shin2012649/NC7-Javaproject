package nc7.mapp.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.mapp.vo.Grade;

import java.util.List;

public class MySQLGradeDao implements GradeDao {

    private SqlSessionFactory sqlSessionFactory;

    public MySQLGradeDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(Grade grade) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.insert("nc7.mapp.dao.GradeDao.insert", grade);
        }
    }

    @Override
    public List<Grade> findAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.GradeDao.findAll");
        }
    }

    @Override
    public Grade findByNo(int gradeNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectOne("nc7.mapp.dao.GradeDao.findByNo", gradeNo);
        }
    }

    @Override
    public int update(Grade grade) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.update("nc7.mapp.dao.GradeDao.update", grade);
        }
    }

    @Override
    public int delete(int gradeNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.delete("nc7.mapp.dao.GradeDao.delete", gradeNo);
        }
    }
}
