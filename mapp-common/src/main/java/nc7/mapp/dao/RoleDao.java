package nc7.mapp.dao;

import java.util.List;
import nc7.mapp.vo.Role;

public interface RoleDao {
    void insert(Role role);
    List<Role> findAll();
    Role findByRoleNo(int roleNo);
    int update(Role role);
    int delete(int roleNo);
}
