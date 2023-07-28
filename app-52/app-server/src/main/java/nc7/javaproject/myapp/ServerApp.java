package nc7.javaproject.myapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import nc7.javaproject.Handler.BoardAddListener;
import nc7.javaproject.Handler.BoardDeleteListener;
import nc7.javaproject.Handler.BoardDetailListener;
import nc7.javaproject.Handler.BoardListListener;
import nc7.javaproject.Handler.BoardUpdateListener;
import nc7.javaproject.Handler.FootListener;
import nc7.javaproject.Handler.HeadListener;
import nc7.javaproject.Handler.HelloListener;
import nc7.javaproject.Handler.LoginListener;
import nc7.javaproject.Handler.ParticipantAddListener;
import nc7.javaproject.Handler.ParticipantDeleteListener;
import nc7.javaproject.Handler.ParticipantDetailListener;
import nc7.javaproject.Handler.ParticipantListListener;
import nc7.javaproject.Handler.ParticipantUpdateListener;
import nc7.javaproject.dao.BoardDao;
import nc7.javaproject.dao.MySQLBoardDao;
import nc7.javaproject.dao.MySQLParticipantDao;
import nc7.javaproject.dao.ParticipantDao;
import nc7.net.NetProtocol;
import nc7.util.BreadcrumbPrompt;
import nc7.util.Menu;
import nc7.util.MenuGroup;
import nc7.util.SqlSessionFactoryProxy;

public class ServerApp {

  // 자바 스레드풀 준비
  ExecutorService threadPool = Executors.newFixedThreadPool(10);

  SqlSessionFactory sqlSessionFactory;
  ParticipantDao participantDao;
  BoardDao boardDao;

  MenuGroup mainMenu = new MenuGroup("메인");

  int port;

  public ServerApp(int port) throws Exception {

    this.port = port;
    
    
    InputStream mybatisConfigIn = Resources.getResourceAsStream("nc7/javaproject/config/mybatis-config.xml");
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
    sqlSessionFactory = new SqlSessionFactoryProxy(builder.build(mybatisConfigIn));

    this.participantDao = new MySQLParticipantDao(sqlSessionFactory);
    this.boardDao = new MySQLBoardDao(sqlSessionFactory);


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
    } finally {
      ((SqlSessionFactoryProxy) sqlSessionFactory).clean();
    }
  }

  private void prepareMenu() {

    MenuGroup ParticipantMenu = new MenuGroup("시사회 참여");
    ParticipantMenu.add(new Menu("등록", new ParticipantAddListener(participantDao,sqlSessionFactory)));
    ParticipantMenu.add(new Menu("목록", new ParticipantListListener(participantDao)));
    ParticipantMenu.add(new Menu("조회", new ParticipantDetailListener(participantDao)));
    ParticipantMenu.add(new Menu("변경", new ParticipantUpdateListener(participantDao,sqlSessionFactory)));
    ParticipantMenu.add(new Menu("삭제", new ParticipantDeleteListener(participantDao, sqlSessionFactory)));
    mainMenu.add(ParticipantMenu);

    MenuGroup boardMenu = new MenuGroup("게시글");
    boardMenu.add(new Menu("등록", new BoardAddListener(1, boardDao, sqlSessionFactory)));
    boardMenu.add(new Menu("목록", new BoardListListener(1, boardDao)));
    boardMenu.add(new Menu("조회", new BoardDetailListener(1, boardDao, sqlSessionFactory)));
    boardMenu.add(new Menu("변경", new BoardUpdateListener(1, boardDao , sqlSessionFactory)));
    boardMenu.add(new Menu("삭제", new BoardDeleteListener(1, boardDao, sqlSessionFactory)));
    mainMenu.add(boardMenu);

    MenuGroup readingMenu = new MenuGroup("평론");
    readingMenu.add(new Menu("등록", new BoardAddListener(2, boardDao, sqlSessionFactory)));
    readingMenu.add(new Menu("목록", new BoardListListener(2, boardDao)));
    readingMenu.add(new Menu("조회", new BoardDetailListener(2, boardDao, sqlSessionFactory)));
    readingMenu.add(new Menu("변경", new BoardUpdateListener(2, boardDao, sqlSessionFactory)));
    readingMenu.add(new Menu("삭제", new BoardDeleteListener(2, boardDao, sqlSessionFactory)));
    mainMenu.add(readingMenu);

    Menu helloMenu = new Menu("안녕!");
    helloMenu.addActionListener(new HeadListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FootListener());
    mainMenu.add(helloMenu);
  }
}










