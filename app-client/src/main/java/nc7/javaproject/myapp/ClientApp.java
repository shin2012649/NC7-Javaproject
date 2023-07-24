package nc7.javaproject.myapp;

import java.sql.Connection;
import java.sql.DriverManager;
import nc7.dao.MySQLParticipantDao;
import nc7.dao.MysqlBoardDao;
import nc7.javaproject.dao.BoardDao;
import nc7.javaproject.dao.ParticipantDao;
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
import nc7.javaproject.util.BreadcrumbPrompt;
import nc7.javaproject.util.Menu;
import nc7.javaproject.util.MenuGroup;

public class ClientApp {
  
  final int BOARD_CATEGORY = 1;
  final int READING_CATEGORY = 2;

  ParticipantDao participantDao;
  BoardDao boardDao;
  BoardDao readingDao;

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();
  MenuGroup mainMenu = new MenuGroup("메인");

  public ClientApp(String ip, int port) throws Exception {
    
    Connection con = DriverManager.getConnection("jdbc:mysql://study:1111@localhost:3306/studydb"
        );

    this.participantDao = new MySQLParticipantDao(con);
    this.boardDao = new MysqlBoardDao(con, 1);
    this.readingDao = new MysqlBoardDao(con, 2);

    prepareMenu();
  }

  public void close() throws Exception {
    prompt.close();
  }

  public static void main(String[] args) throws Exception {
    if (args.length < 2) {
      System.out.println("실행 예) java ... ClientApp 서버주소 포트번호");
      return;
    }

    ClientApp app = new ClientApp(args[0], Integer.parseInt(args[1]));
    app.execute();
    app.close();
  }

  static void printTitle() {
    System.out.println("나의 목록 관리 시스템");
    System.out.println("----------------------------------");
  }

  public void execute() {
    printTitle();
    mainMenu.execute(prompt);
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










