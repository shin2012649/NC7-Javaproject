package nc7.mapp.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.mapp.vo.Role;

import java.util.List;

public class MySQLRoleDao implements RoleDao {

    private SqlSessionFactory sqlSessionFactory;

    public MySQLRoleDao(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void insert(Role role) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            sqlSession.insert("nc7.mapp.dao.RoleDao.insert", role);
        }
    }

    @Override
    public List<Role> findAll() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectList("nc7.mapp.dao.RoleDao.findAll");
        }
    }

    @Override
    public Role findByRoleNo(int roleNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(false)) {
            return sqlSession.selectOne("nc7.mapp.dao.RoleDao.findByRoleNo", roleNo);
        }
    }

    @Override
    public int update(Role role) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.update("nc7.mapp.dao.RoleDao.update", role);
        }
    }

    @Override
    public int delete(int roleNo) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            return sqlSession.delete("nc7.mapp.dao.RoleDao.delete", roleNo);
        }
    }
}
