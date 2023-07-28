package nc7.javaproject.Handler;
import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.javaproject.dao.BoardDao;
import nc7.javaproject.vo.Board;
import nc7.javaproject.vo.Participant;
import nc7.util.ActionListener;
import nc7.util.BreadcrumbPrompt;

public class BoardAddListener implements ActionListener {

  int category;
  BoardDao boardDao;
  SqlSessionFactory sqlSessionFactory;

  public BoardAddListener(int category, BoardDao boardDao, SqlSessionFactory sqlSessionFactory) {
    this.category = category;
    this.boardDao = boardDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Board board = new Board();
    board.setTitle(prompt.inputString("제목? "));
    board.setContent(prompt.inputString("내용? "));
    board.setWriter((Participant) prompt.getAttribute("loginUser"));
    board.setCategory(category);

    try {
      boardDao.insert(board);
      //      Thread.sleep(5000);
      //
      //      boardDao.insert(board);
      //      Thread.sleep(5000);
      //
      //      boardDao.insert(board);
      //      Thread.sleep(5000);

      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}





