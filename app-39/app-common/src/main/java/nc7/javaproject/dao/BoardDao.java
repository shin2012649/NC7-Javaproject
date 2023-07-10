package nc7.javaproject.dao;

import java.util.List;
import nc7.javaproject.vo.Board;


public interface BoardDao {
  void insert(Board board);
  List<Board> list();
  Board findBy(int no);
  int update(Board board);
  int delete(int no);
}
