package nc7.javaproject.PreferenceSurveyApp.Handler;
import nc7.javaproject.PreferenceSurveyApp.dao.BoardDao;
import nc7.javaproject.util.ActionListener;
import nc7.javaproject.util.BreadcrumbPrompt;
import nc7.javaproject.vo.Board;

public class BoardUpdateListener implements ActionListener {

  BoardDao boardDao;

  public BoardUpdateListener(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int boardNo = prompt.inputInt("번호? ");

    Board board = boardDao.findBy(boardNo);
    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다!");
      return;
    }

    if (!prompt.inputString("암호? ").equals(board.getPassword())) {
      System.out.println("암호가 일치하지 않습니다!");
      return;
    }

    board.setTitle(prompt.inputString("제목(%s)? ", board.getTitle()));
    board.setContent(prompt.inputString("내용(%s)? ", board.getContent()));

    boardDao.update(board);
  }
}


  