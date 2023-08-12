package nc7.mapp.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.mapp.vo.WatchList;

public class MySQLWatchListDao implements WatchListDao {

    private SqlSessionFactory sqlSessionFactory;

    public MySQLWatchListDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(WatchList watchList) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.insert("nc7.mapp.dao.WatchListDao.insert", watchList);
        }
    }

    @Override
    public List<WatchList> findByUsersNo(int usersNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.WatchListDao.findByUsersNo", usersNo);
        }
    }

    @Override
    public List<WatchList> findByFilmsNo(int filmsNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.WatchListDao.findByFilmsNo", filmsNo);
        }
    }

    @Override
    public int delete(int usersNo, int filmsNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            WatchList watchList = new WatchList();
            watchList.setUsersNo(usersNo);
            watchList.setFilmsNo(filmsNo);
            return sqlSession.delete("nc7.mapp.dao.WatchListDao.delete", watchList);
        }
    }
}
