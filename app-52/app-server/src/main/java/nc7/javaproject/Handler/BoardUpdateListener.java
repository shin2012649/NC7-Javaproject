package nc7.javaproject.handler;
import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import nc7.javaproject.dao.BoardDao;
import nc7.javaproject.vo.Board;
import nc7.javaproject.vo.Participant;
import nc7.util.ActionListener;
import nc7.util.BreadcrumbPrompt;

public class BoardUpdateListener implements ActionListener {

  int category;
  BoardDao boardDao;
  SqlSessionFactory sqlSessionFactory;

  public BoardUpdateListener(int category, BoardDao boardDao, SqlSessionFactory sqlSessionFactory) {
    this.category = category;
    this.boardDao = boardDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    int boardNo = prompt.inputInt("번호? ");

    Board board = boardDao.findBy(category, boardNo);
    if (board == null) {
      prompt.println("해당 번호의 게시글이 없습니다!");
      return;
    }

    board.setTitle(prompt.inputString("제목(%s)? ", board.getTitle()));
    board.setContent(prompt.inputString("내용(%s)? ", board.getContent()));
    board.setWriter((Participant) prompt.getAttribute("loginUser"));

    try {
      if (boardDao.update(board) == 0) {
        prompt.println("게시글 변경 권한이 없습니다.");
      } else {
        prompt.println("변경했습니다!");
      }
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}



