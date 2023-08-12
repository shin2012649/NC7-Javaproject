package nc7.mapp.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.mapp.vo.Src;

import java.util.List;

public class MySQLSrcDao implements SrcDao {

    private SqlSessionFactory sqlSessionFactory;

    public MySQLSrcDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(Src src) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.insert("nc7.mapp.dao.SrcDao.insert", src);
        }
    }

    @Override
    public List<Src> findAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.SrcDao.findAll");
        }
    }

    @Override
    public Src findBySrcNo(int srcNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectOne("nc7.mapp.dao.SrcDao.findBySrcNo", srcNo);
        }
    }

    @Override
    public int update(Src src) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.update("nc7.mapp.dao.SrcDao.update", src);
        }
    }

    @Override
    public int delete(int srcNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.delete("nc7.mapp.dao.SrcDao.delete", srcNo);
        }
    }
}
