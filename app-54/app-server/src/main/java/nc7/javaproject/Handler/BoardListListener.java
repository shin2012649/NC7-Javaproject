package nc7.javaproject.Handler;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import nc7.javaproject.dao.BoardDao;
import nc7.javaproject.vo.Board;
import nc7.util.ActionListener;
import nc7.util.BreadcrumbPrompt;
import nc7.util.Component;


@Component("/board/list")
public class BoardListListener implements ActionListener {

  BoardDao boardDao;
  SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

  public BoardListListener(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    prompt.println("---------------------------------------");
    prompt.println("번호, 제목, 작성자, 조회수, 등록일");
    prompt.println("---------------------------------------");

    int category = Integer.parseInt((String)prompt.getAttribute("category"));
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




