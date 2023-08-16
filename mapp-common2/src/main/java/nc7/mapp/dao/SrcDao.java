package nc7.mapp.dao;

import java.util.List;
import nc7.mapp.vo.Src;

public interface SrcDao {
    void insert(Src src);
    List<Src> findAll();
    Src findBySrcNo(int srcNo);
    int update(Src src);
    int delete(int srcNo);
}
