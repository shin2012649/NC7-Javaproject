package nc7.mapp.dao;

import java.util.List;
import nc7.mapp.vo.Staff;

public interface StaffDao {
    void insert(Staff staff);
    List<Staff> findAll();
    Staff findByStaffNo(int staffNo);
    int update(Staff staff);
    int delete(int staffNo);
}
