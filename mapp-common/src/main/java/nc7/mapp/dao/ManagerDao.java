package nc7.mapp.dao;

import java.util.List;
import nc7.mapp.vo.Manager;

public interface ManagerDao {
    void insert(Manager manager);
    List<Manager> findAll();
    Manager findByUserNo(int userNo);
    int update(Manager manager);
    int delete(int userNo);
}
