package nc7.javaproject.PreferenceSurveyApp;

import nc7.javaproject.PreferenceSurveyApp.ParticipantHandler;
import nc7.javaproject.PreferenceSurveyApp.BoardHandler;
import nc7.javaproject.util.Prompt;


public class App {

  public static void main(String[] args) {


    Prompt prompt = new Prompt();

    ParticipantHandler participantHandler = new ParticipantHandler(prompt);
    BoardHandler boardHandler = new BoardHandler(prompt);
    BoardHandler readingHandler = new BoardHandler(prompt);

    printTitle();

    printMenu();

    while (true) {
      String menuNo = prompt.inputString("메인> ");
      if (menuNo.equals("99")) {
          break;
      } else if (menuNo.equals("menu")) {
          printMenu();
      } else if (menuNo.equals("1")) {
          participantHandler.inputParticipant();
      } else if (menuNo.equals("2")) {
          participantHandler.printParticipants();
      } else if (menuNo.equals("3")) {
          participantHandler.viewParticipant();
      } else if (menuNo.equals("4")) {
          participantHandler.updateParticipant();
      } else if (menuNo.equals("5")) {
          participantHandler.deleteParticipant();
      } else if (menuNo.equals("6")) {
          participantHandler.printRatingCounts();
      } else if (menuNo.equals("7")) {
          participantHandler.printAverageScore();
      } else if (menuNo.equals("8")) {
          boardHandler.inputBoard();
      } else if (menuNo.equals("9")) {
          boardHandler.printBoards();
      } else if (menuNo.equals("10")) {
          boardHandler.viewBoard();
      } else if (menuNo.equals("11")) {
          boardHandler.updateBoard();
      } else if (menuNo.equals("12")) {
          boardHandler.deleteBoard();
      }  else if (menuNo.equals("13")) {
          readingHandler.inputBoard();
      } else if (menuNo.equals("14")) {
          readingHandler.printBoards();
      } else if (menuNo.equals("15")) {
          readingHandler.viewBoard();
      } else if (menuNo.equals("16")) {
          readingHandler.updateBoard();
      } else if (menuNo.equals("17")) {
          readingHandler.deleteBoard();
      } else {
          System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }
    prompt.close();
  }


  static void printMenu() {
    System.out.println("1. 참여자 등록");
    System.out.println("2. 참여자 목록");
    System.out.println("3. 참여자 조회");
    System.out.println("4. 참여자 변경");
    System.out.println("5. 참여자 삭제");
    System.out.println("6. 평가_참여자 통계");
    System.out.println("7. 평가_평점 통계");
    System.out.println("8. 추천 영화 등록");
    System.out.println("9. 추천 영화 목록");
    System.out.println("10. 추천 영화 조회");
    System.out.println("11. 추천 영화 변경");
    System.out.println("12. 추천 영화 삭제");
    System.out.println("13. 영화 평론 등록");
    System.out.println("14. 영화 평론 목록");
    System.out.println("15. 영화 평론 조회");
    System.out.println("16. 영화 평론 변경");
    System.out.println("17. 영화 평론 삭제");
    System.out.println("99. 종료");
  }

  static void printTitle() {
    System.out.println("영화 평가");
    System.out.println("----------------------------------");
  }
}
