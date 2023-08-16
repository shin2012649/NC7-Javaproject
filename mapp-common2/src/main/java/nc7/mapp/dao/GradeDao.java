package nc7.mapp.dao;

import java.util.List;
import nc7.mapp.vo.Grade;

public interface GradeDao {
    void insert(Grade grade);
    List<Grade> findAll();
    Grade findByNo(int gradeNo);
    int update(Grade grade);
    int delete(int gradeNo);
}
