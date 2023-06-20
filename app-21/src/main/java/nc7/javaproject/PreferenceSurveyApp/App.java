package nc7.javaproject.PreferenceSurveyApp;

import nc7.javaproject.PreferenceSurveyApp.Handler.BoardHandler;
import nc7.javaproject.PreferenceSurveyApp.Handler.Handler;
import nc7.javaproject.PreferenceSurveyApp.Handler.ParticipantHandler;
import nc7.javaproject.util.ArrayList;
import nc7.javaproject.util.LinkedList;
import nc7.javaproject.util.MenuPrompt;


public class App {

  public static void main(String[] args) {


    MenuPrompt prompt = new MenuPrompt();
    prompt.appendBreadcrumb("메인", getMenu());

    Handler participantHandler = new ParticipantHandler(prompt,"참여자", new ArrayList());
    Handler boardHandler = new BoardHandler(prompt,"추천영화",new LinkedList());
    Handler readingHandler = new BoardHandler(prompt,"영화 평론",new LinkedList());

    printTitle();

    printTitle();

    prompt.printMenu();

    loop: while (true) {
      String menuNo = prompt.inputMenu();
      switch (menuNo) {
        case "0": break loop;
        case "1": participantHandler.execute(); break;
        case "2": boardHandler.execute(); break;
        case "3": readingHandler.execute(); break;
      }
    }

    prompt.close();
  }

  static String getMenu() {
    StringBuilder menu = new StringBuilder();
    menu.append("1. 회원\n");
    menu.append("2. 게시글\n");
    menu.append("3. 독서록\n");
    menu.append("0. 종료\n");
    return menu.toString();
  }


  static void printTitle() {
    System.out.println("나의 목록 관리 시스템");
    System.out.println("----------------------------------");
  }
}
