package nc7.javaproject.handler;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import nc7.javaproject.dao.BoardDao;
import nc7.javaproject.vo.Board;
import nc7.util.ActionListener;
import nc7.util.BreadcrumbPrompt;


public class BoardListListener implements ActionListener {

  int category;
  BoardDao boardDao;
  SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

  public BoardListListener(int category, BoardDao boardDao) {
    this.category = category;
    this.boardDao = boardDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    prompt.println("---------------------------------------");
    prompt.println("번호, 제목, 작성자, 조회수, 등록일");
    prompt.println("---------------------------------------");

    List<Board> list = boardDao.findAll(category);

    for (Board board : list) {
      prompt.printf("%d, %s, %s, %d, %s\n",
          board.getNo(),
          board.getTitle(),
          board.getWriter().getName(),
          board.getViewCount(),
          dateFormatter.format(board.getCreatedDate()));
    }
  }

}





