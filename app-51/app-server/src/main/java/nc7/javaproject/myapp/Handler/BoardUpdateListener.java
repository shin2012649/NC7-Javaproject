package nc7.javaproject.myapp.Handler;
import java.io.IOException;
import nc7.javaproject.dao.BoardDao;
import nc7.javaproject.util.ActionListener;
import nc7.javaproject.util.BreadcrumbPrompt;
import nc7.javaproject.util.DataSource;
import nc7.javaproject.vo.Board;

public class BoardUpdateListener implements ActionListener {

  BoardDao boardDao;
  DataSource ds;

  public BoardUpdateListener(BoardDao boardDao,  DataSource ds) {
    this.boardDao = boardDao;
    this.ds = ds;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    int boardNo = prompt.inputInt("번호? ");

    Board board = boardDao.findBy(boardNo);
    if (board == null) {
      prompt.println("해당 번호의 게시글이 없습니다!");
      return;
    }

    try {
      if (boardDao.update(board) == 0) {
        prompt.println("게시글 변경 권한이 없습니다.");
      } else {
        prompt.println("변경했습니다!");
      }
      ds.getConnection().commit();

    } catch (Exception e) {
      try {ds.getConnection().rollback();} catch (Exception e2) {}
      throw new RuntimeException(e);
    }
  }
}



