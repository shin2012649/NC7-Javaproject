package nc7.mapp.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.mapp.vo.FilmActor;

import java.util.List;

public class MySQLFilmActorDao implements FilmActorDao {

    private SqlSessionFactory sqlSessionFactory;

    public MySQLFilmActorDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(FilmActor filmActor) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.insert("nc7.mapp.dao.FilmActorDao.insert", filmActor);
        }
    }

    @Override
    public List<FilmActor> findByFilmNo(int filmNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.FilmActorDao.findByFilmNo", filmNo);
        }
    }

    @Override
    public int update(FilmActor filmActor) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.update("nc7.mapp.dao.FilmActorDao.update", filmActor);
        }
    }

    @Override
    public int delete(int filmNo, int actorNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            FilmActor filmActor = new FilmActor();
            filmActor.setFilmsNo(filmNo);
            filmActor.setActorsNo(actorNo);
            return sqlSession.delete("nc7.mapp.dao.FilmActorDao.delete", filmActor);
        }
    }
}
