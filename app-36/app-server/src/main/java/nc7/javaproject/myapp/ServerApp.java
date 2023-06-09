package nc7.javaproject.myapp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import com.google.gson.Gson;
import nc7.javaproject.myapp.dao.BoardDao;
import nc7.javaproject.myapp.dao.BoardListDao;
import nc7.javaproject.myapp.dao.ParticipantDao;
import nc7.javaproject.myapp.dao.ParticipantListDao;
import nc7.javaproject.vo.Board;

public class ServerApp {
  
  int port;
  ServerSocket serverSocket;
  

  ParticipantDao participantDao = new ParticipantListDao("participant.json");
  BoardDao boardDao = new BoardListDao("board.json");
  BoardDao readingDao = new BoardListDao("reading.json");
  
  LinkedList<Board> boardList = new LinkedList<>();
  LinkedList<Board> readingList = new LinkedList<>();

  public ServerApp(int port) throws Exception {
    this.port = port;
  }

  public void close() throws Exception {
    serverSocket.close();
  }

  public static void main(String[] args) throws Exception {
    if (args.length < 1) {
      System.out.println("실행 예) java ... ServerApp 포트번호");
      return;
    }

    ServerApp app = new ServerApp(Integer.parseInt(args[0]));
    app.execute();
    app.close();
  }

  public void execute() throws Exception {
    System.out.println("[MyList 서버 애플리케이션]");

    this.serverSocket = new ServerSocket(port);
    System.out.println("서버 실행 중...");

    Socket socket = serverSocket.accept();
    DataInputStream in = new DataInputStream(socket.getInputStream());
    DataOutputStream out = new DataOutputStream(socket.getOutputStream());

    Gson gson = new Gson();

    while (true) {
      String command = in.readUTF();

      System.out.println(command);

      HashMap<String,String> response = new HashMap<>();

      if (command.equals("quit")) {
        break;
      } else if (command.equals("board/list")) {
        response.put("status", "success");
        response.put("data", gson.toJson(boardDao.list()));

      } else {
        response.put("status", "failure");
        response.put("message", "nono!");
      }

      out.writeUTF(gson.toJson(response));
    }

    in.close();
    out.close();
    socket.close();
  }
}










