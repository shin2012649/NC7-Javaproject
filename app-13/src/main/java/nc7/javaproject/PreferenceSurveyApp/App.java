package nc7.javaproject.PreferenceSurveyApp;

import nc7.javaproject.PreferenceSurveyApp.ParticipantHandler;
import nc7.javaproject.PreferenceSurveyApp.BoardHandler;
import nc7.javaproject.util.Prompt;


public class App {

  public static void main(String[] args) {

    printTitle();

    printMenu();

    while (true) {
      String menuNo = Prompt.inputString("메인> ");
      if (menuNo.equals("99")) {
          break;
      } else if (menuNo.equals("menu")) {
          printMenu();
      } else if (menuNo.equals("1")) {
          ParticipantHandler.inputParticipant();
      } else if (menuNo.equals("2")) {
          ParticipantHandler.printParticipants();
      } else if (menuNo.equals("3")) {
          ParticipantHandler.viewParticipant();
      } else if (menuNo.equals("4")) {
          ParticipantHandler.updateParticipant();
      } else if (menuNo.equals("5")) {
          ParticipantHandler.deleteParticipant();
      } else if (menuNo.equals("6")) {
          ParticipantHandler.printRatingCounts();
      } else if (menuNo.equals("7")) {
          ParticipantHandler.printAverageScore();
      } else if (menuNo.equals("8")) {
          BoardHandler.inputBoard();
      } else if (menuNo.equals("9")) {
          BoardHandler.printBoards();
      } else if (menuNo.equals("10")) {
          BoardHandler.viewBoard();
      } else if (menuNo.equals("11")) {
          BoardHandler.updateBoard();
      } else if (menuNo.equals("12")) {
          BoardHandler.deleteBoard();
      } else {
          System.out.println(menuNo);
      }
  }

    Prompt.close();
  }

  static void printMenu() {
    System.out.println("1. 참여자 등록");
    System.out.println("2. 참여자 목록");
    System.out.println("3. 참여자 조회");
    System.out.println("4. 참여자 변경");
    System.out.println("5. 참여자 삭제");
    System.out.println("6. 평가_참여자 통계");
    System.out.println("7. 평가_평점 통계");
    System.out.println("8. 게시글 등록");
    System.out.println("9. 게시글 목록");
    System.out.println("10. 게시글 조회");
    System.out.println("11. 게시글 변경");
    System.out.println("12. 게시글 삭제");
    System.out.println("8. 종료");
  }

  static void printTitle() {
    System.out.println("관객 시사회 평가");
    System.out.println("----------------------------------");
  }


}
