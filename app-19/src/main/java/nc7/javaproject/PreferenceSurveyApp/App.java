package nc7.javaproject.PreferenceSurveyApp;

import nc7.javaproject.PreferenceSurveyApp.Handler.ParticipantHandler;
import nc7.javaproject.PreferenceSurveyApp.Handler.BoardHandler;
import nc7.javaproject.util.Prompt;
import nc7.javaproject.PreferenceSurveyApp.Handler.Handler;


public class App {

  public static void main(String[] args) {


    Prompt prompt = new Prompt();

    Handler participantHandler = new ParticipantHandler(prompt,"참여자");
    Handler boardHandler = new BoardHandler(prompt,"추천영화");
    Handler readingHandler = new BoardHandler(prompt,"영화 평론");

    printTitle();

    printMenu();

    while (true) {
      String menuNo = prompt.inputString("메인> ");
      if (menuNo.equals("0")) {
          break;
      } else if (menuNo.equals("menu")) {
          printMenu();
      } else if (menuNo.equals("1")) {
          participantHandler.execute();
      } else if (menuNo.equals("2")) {
          boardHandler.execute();
      } else if (menuNo.equals("3")) {
          readingHandler.execute();
      } else {
          System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }
    prompt.close();
  }


  static void printMenu() {
    System.out.println("1. 참여자");
    System.out.println("2. 추천 영화");
    System.out.println("3. 영화 평론");
    System.out.println("0. 종료");
  }

  static void printTitle() {
    System.out.println("영화 평가");
    System.out.println("----------------------------------");
  }
}
