package nc7.mapp.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.mapp.vo.Staff;

public class MySQLStaffDao implements StaffDao {

    private SqlSessionFactory sqlSessionFactory;

    public MySQLStaffDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(Staff staff) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.insert("nc7.mapp.dao.StaffDao.insert", staff);
        }
    }

    @Override
    public Staff findByStaffNo(int staffNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectOne("nc7.mapp.dao.StaffDao.findByStaffNo", staffNo);
        }
    }

    @Override
    public int update(Staff staff) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.update("nc7.mapp.dao.StaffDao.update", staff);
        }
    }

    @Override
    public int delete(int staffNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.delete("nc7.mapp.dao.StaffDao.delete", staffNo);
        }
    }

    @Override
    public List<Staff> findAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.StaffDao.findAll");
        }
    }
}
