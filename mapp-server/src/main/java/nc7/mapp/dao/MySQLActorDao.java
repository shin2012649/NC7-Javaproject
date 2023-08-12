package nc7.mapp.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.mapp.vo.Actor;

public class MySQLActorDao implements ActorDao {

    private SqlSessionFactory sqlSessionFactory;

    public MySQLActorDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(Actor actor) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.insert("nc7.mapp.dao.ActorDao.insert", actor);
        }
    }

    @Override
    public Actor findByActorsNo(int actorsNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectOne("nc7.mapp.dao.ActorDao.findByActorsNo", actorsNo);
        }
    }

    @Override
    public int update(Actor actor) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.update("nc7.mapp.dao.ActorDao.update", actor);
        }
    }

    @Override
    public int delete(int actorsNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.delete("nc7.mapp.dao.ActorDao.delete", actorsNo);
        }
    }

    @Override
    public List<Actor> findAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.ActorDao.findAll");
        }
    }
}
