package nc7.javaproject.PreferenceSurveyApp.Handler;

import nc7.javaproject.PreferenceSurveyApp.dao.BoardDao;
import nc7.javaproject.util.ActionListener;
import nc7.javaproject.util.BreadcrumbPrompt;

public class BoardDeleteListener implements ActionListener {

  BoardDao boardDao;

  public BoardDeleteListener(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    if (boardDao.delete(prompt.inputInt("번호? ")) == 0) {
      System.out.println("해당 번호의 게시글이 없습니다!");
    }
  }
}



  
