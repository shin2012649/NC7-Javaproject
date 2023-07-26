package nc7.javaproject.myapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
import nc7.javaproject.myapp.Handler.LoginListener;
import nc7.javaproject.myapp.Handler.ParticipantAddListener;
import nc7.javaproject.myapp.Handler.ParticipantDeleteListener;
import nc7.javaproject.myapp.Handler.ParticipantDetailListener;
import nc7.javaproject.myapp.Handler.ParticipantListListener;
import nc7.javaproject.myapp.Handler.ParticipantUpdateListener;
import nc7.javaproject.util.BreadcrumbPrompt;
import nc7.javaproject.util.DataSource;
import nc7.javaproject.util.Menu;
import nc7.javaproject.util.MenuGroup;
import nc7.net.NetProtocol;

public class ServerApp {

  // 자바 스레드풀 준비
  ExecutorService threadPool = Executors.newFixedThreadPool(10);

  DataSource ds = new DataSource("jdbc:mysql://localhost:3306/studydb" , "study", "1111");
  ParticipantDao participantDao;
  BoardDao boardDao;
  BoardDao readingDao;

  MenuGroup mainMenu = new MenuGroup("메인");

  int port;

  public ServerApp(int port) throws Exception {

    this.port = port;

    this.participantDao = new MySQLParticipantDao(ds);
    this.boardDao = new MysqlBoardDao(ds, 1);
    this.readingDao = new MysqlBoardDao(ds, 2);

    prepareMenu();
  }

  public void close() throws Exception {
  }

  public static void main(String[] args) throws Exception {
    ServerApp app = new ServerApp(8888);
    app.execute();
    app.close();
  }

  public void execute() {
    try (ServerSocket serverSocket = new ServerSocket(this.port)) {
      System.out.println("서버 실행 중...");

      while (true) {
        Socket socket = serverSocket.accept();
        threadPool.execute(() -> processRequest(socket));
      }
    } catch (Exception e) {
      System.out.println("서버 실행 오류!");
      e.printStackTrace();
    }
  }
  
  private void processRequest(Socket socket) {
    try (Socket s = socket;
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

      BreadcrumbPrompt prompt = new BreadcrumbPrompt(in, out);

      InetSocketAddress clientAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
      System.out.printf("%s 클라이언트 접속함!\n", clientAddress.getHostString());

      out.writeUTF("[영화 정보 공유 사이트 ]\n"
          + "-----------------------------------------");

      new LoginListener(participantDao).service(prompt);

      mainMenu.execute(prompt);
      out.writeUTF(NetProtocol.NET_END);

    } catch (Exception e) {
      System.out.println("클라이언트 통신 오류!");
      e.printStackTrace();
    }
  }

  private void prepareMenu() {

    MenuGroup ParticipantMenu = new MenuGroup("시사회 참여");
    ParticipantMenu.add(new Menu("등록", new ParticipantAddListener(participantDao,ds)));
    ParticipantMenu.add(new Menu("목록", new ParticipantListListener(participantDao)));
    ParticipantMenu.add(new Menu("조회", new ParticipantDetailListener(participantDao)));
    ParticipantMenu.add(new Menu("변경", new ParticipantUpdateListener(participantDao,ds)));
    ParticipantMenu.add(new Menu("삭제", new ParticipantDeleteListener(participantDao, ds)));
    mainMenu.add(ParticipantMenu);

    MenuGroup boardMenu = new MenuGroup("게시글");
    boardMenu.add(new Menu("등록", new BoardAddListener(boardDao, ds)));
    boardMenu.add(new Menu("목록", new BoardListListener(boardDao)));
    boardMenu.add(new Menu("조회", new BoardDetailListener(boardDao, ds)));
    boardMenu.add(new Menu("변경", new BoardUpdateListener(boardDao , ds)));
    boardMenu.add(new Menu("삭제", new BoardDeleteListener(boardDao, ds)));
    mainMenu.add(boardMenu);

    MenuGroup readingMenu = new MenuGroup("평론");
    readingMenu.add(new Menu("등록", new BoardAddListener(readingDao, ds)));
    readingMenu.add(new Menu("목록", new BoardListListener(readingDao)));
    readingMenu.add(new Menu("조회", new BoardDetailListener(readingDao, ds)));
    readingMenu.add(new Menu("변경", new BoardUpdateListener(readingDao, ds)));
    readingMenu.add(new Menu("삭제", new BoardDeleteListener(readingDao, ds)));
    mainMenu.add(readingMenu);

    Menu helloMenu = new Menu("안녕!");
    helloMenu.addActionListener(new HeadListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FootListener());
    mainMenu.add(helloMenu);
  }
}










