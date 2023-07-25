package nc7.javaproject.myapp.Handler;
import java.io.IOException;
import nc7.javaproject.dao.BoardDao;
import nc7.javaproject.util.ActionListener;
import nc7.javaproject.util.BreadcrumbPrompt;
import nc7.javaproject.vo.Board;
import nc7.javaproject.vo.Participant;

public class BoardAddListener implements ActionListener {

  BoardDao boardDao;

  public BoardAddListener(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Board board = new Board();
    board.setTitle(prompt.inputString("제목? "));
    board.setContent(prompt.inputString("내용? "));
    Participant writer = new Participant();
    writer.setNo(prompt.inputInt("작성자? "));
    board.setWriter((Participant) prompt.getAttribute("loginUser"));
    board.setPassword(prompt.inputString("암호? "));

    boardDao.insert(board);
  }
}





