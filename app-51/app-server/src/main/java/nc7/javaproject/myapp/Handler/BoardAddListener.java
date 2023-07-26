package nc7.javaproject.myapp.Handler;
import java.io.IOException;
import nc7.javaproject.dao.BoardDao;
import nc7.javaproject.util.ActionListener;
import nc7.javaproject.util.BreadcrumbPrompt;
import nc7.javaproject.util.DataSource;
import nc7.javaproject.vo.Board;
import nc7.javaproject.vo.Participant;

public class BoardAddListener implements ActionListener {

  BoardDao boardDao;
  DataSource ds;

  public BoardAddListener(BoardDao boardDao, DataSource ds) {
    this.boardDao = boardDao;
    this.ds = ds;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Board board = new Board();
    board.setTitle(prompt.inputString("제목? "));
    board.setContent(prompt.inputString("내용? "));
    board.setWriter((Participant) prompt.getAttribute("loginUser"));

    try {
      boardDao.insert(board);
      //      Thread.sleep(5000);
      //
      //      boardDao.insert(board);
      //      Thread.sleep(5000);
      //
      //      boardDao.insert(board);
      //      Thread.sleep(5000);

      ds.getConnection().commit();

    } catch (Exception e) {
      try {ds.getConnection().rollback();} catch (Exception e2) {}
      throw new RuntimeException(e);
    }
  }
}





