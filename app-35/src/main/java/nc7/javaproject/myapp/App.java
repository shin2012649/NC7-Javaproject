package nc7.javaproject.myapp;

import java.util.LinkedList;
import nc7.javaproject.myapp.Handler.BoardAddListener;
import nc7.javaproject.myapp.Handler.BoardDeleteListener;
import nc7.javaproject.myapp.Handler.BoardDetailListener;
import nc7.javaproject.myapp.Handler.BoardListListener;
import nc7.javaproject.myapp.Handler.BoardUpdateListener;
import nc7.javaproject.myapp.Handler.FootListener;
import nc7.javaproject.myapp.Handler.HeadListener;
import nc7.javaproject.myapp.Handler.HelloListener;
import nc7.javaproject.myapp.Handler.ParticipantAddListener;
import nc7.javaproject.myapp.Handler.ParticipantDeleteListener;
import nc7.javaproject.myapp.Handler.ParticipantDetailListener;
import nc7.javaproject.myapp.Handler.ParticipantListListener;
import nc7.javaproject.myapp.Handler.ParticipantUpdateListener;
import nc7.javaproject.myapp.dao.BoardDao;
import nc7.javaproject.myapp.dao.BoardListDao;
import nc7.javaproject.myapp.dao.ParticipantDao;
import nc7.javaproject.myapp.dao.ParticipantListDao;
import nc7.javaproject.util.BreadcrumbPrompt;
import nc7.javaproject.util.Menu;
import nc7.javaproject.util.MenuGroup;
import nc7.javaproject.vo.Board;

public class App {

  ParticipantDao participantDao = new ParticipantListDao("participant.json");
  BoardDao boardDao = new BoardListDao("board.json");
  BoardDao readingDao = new BoardListDao("reading.json");
  
  LinkedList<Board> boardList = new LinkedList<>();
  LinkedList<Board> readingList = new LinkedList<>();

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");

  public App() {
    prepareMenu();
  }

  public static void main(String[] args) {
    new App().execute();
  }

  static void printTitle() {
    System.out.println("영화 평가 프로그램");
    System.out.println("----------------------------------");
  }

  public void execute() {
    printTitle();
    mainMenu.execute(prompt);
    prompt.close();
  }

  private void prepareMenu() {

    MenuGroup ParticipantMenu = new MenuGroup("시사회 참여");
    ParticipantMenu.add(new Menu("등록", new ParticipantAddListener(participantDao)));
    ParticipantMenu.add(new Menu("목록", new ParticipantListListener(participantDao)));
    ParticipantMenu.add(new Menu("조회", new ParticipantDetailListener(participantDao)));
    ParticipantMenu.add(new Menu("변경", new ParticipantUpdateListener(participantDao)));
    ParticipantMenu.add(new Menu("삭제", new ParticipantDeleteListener(participantDao)));
    mainMenu.add(ParticipantMenu);

    MenuGroup boardMenu = new MenuGroup("게시글");
    boardMenu.add(new Menu("등록", new BoardAddListener(boardDao)));
    boardMenu.add(new Menu("목록", new BoardListListener(boardDao)));
    boardMenu.add(new Menu("조회", new BoardDetailListener(boardDao)));
    boardMenu.add(new Menu("변경", new BoardUpdateListener(boardDao)));
    boardMenu.add(new Menu("삭제", new BoardDeleteListener(boardDao)));
    mainMenu.add(boardMenu);

    MenuGroup readingMenu = new MenuGroup("평론");
    readingMenu.add(new Menu("등록", new BoardAddListener(readingDao)));
    readingMenu.add(new Menu("목록", new BoardListListener(readingDao)));
    readingMenu.add(new Menu("조회", new BoardDetailListener(readingDao)));
    readingMenu.add(new Menu("변경", new BoardUpdateListener(readingDao)));
    readingMenu.add(new Menu("삭제", new BoardDeleteListener(readingDao)));
    mainMenu.add(readingMenu);

    Menu helloMenu = new Menu("안녕!");
    helloMenu.addActionListener(new HeadListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FootListener());
    mainMenu.add(helloMenu);
  }
}










