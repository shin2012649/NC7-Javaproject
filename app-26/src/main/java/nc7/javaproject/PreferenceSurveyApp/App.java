package nc7.javaproject.PreferenceSurveyApp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import nc7.javaproject.PreferenceSurveyApp.Handler.BoardAddListener;
import nc7.javaproject.PreferenceSurveyApp.Handler.BoardDeleteListener;
import nc7.javaproject.PreferenceSurveyApp.Handler.BoardDetailListener;
import nc7.javaproject.PreferenceSurveyApp.Handler.BoardListListener;
import nc7.javaproject.PreferenceSurveyApp.Handler.BoardUpdateListener;
import nc7.javaproject.PreferenceSurveyApp.Handler.FootListener;
import nc7.javaproject.PreferenceSurveyApp.Handler.HeadListener;
import nc7.javaproject.PreferenceSurveyApp.Handler.HelloListener;
import nc7.javaproject.PreferenceSurveyApp.Handler.ParticipantAddListener;
import nc7.javaproject.PreferenceSurveyApp.Handler.ParticipantDeleteListener;
import nc7.javaproject.PreferenceSurveyApp.Handler.ParticipantDetailListener;
import nc7.javaproject.PreferenceSurveyApp.Handler.ParticipantListListener;
import nc7.javaproject.PreferenceSurveyApp.Handler.ParticipantUpdateListener;
import nc7.javaproject.util.BreadcrumbPrompt;
import nc7.javaproject.util.Menu;
import nc7.javaproject.util.MenuGroup;
import nc7.javaproject.vo.Board;
import nc7.javaproject.vo.Participant;

public class App {
  
  ArrayList<Participant> participantList = new ArrayList<>();
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
    System.out.println("영화 평가 시스템");
    System.out.println("----------------------------------");
  }
  
  public void execute() {
    printTitle();
    loadData();
    mainMenu.execute(prompt);
    saveData();

    prompt.close();
  }
  
  private void loadData() {
    
    loadParticipant();
    loadBoard("board.data", boardList);
    loadBoard("reading.data", readingList);
    
  }
  
  private void saveData() {
    
    saveParticipant();
    saveBoard("board.data", boardList);
    saveBoard("reading.data", readingList);
    
  }

  private void prepareMenu() {

    MenuGroup ParticipantMenu = new MenuGroup("참여자");
    ParticipantMenu.add(new Menu("등록", new ParticipantAddListener(participantList)));
    ParticipantMenu.add(new Menu("목록", new ParticipantListListener(participantList)));
    ParticipantMenu.add(new Menu("조회", new ParticipantDetailListener(participantList)));
    ParticipantMenu.add(new Menu("변경", new ParticipantUpdateListener(participantList)));
    ParticipantMenu.add(new Menu("삭제", new ParticipantDeleteListener(participantList)));
    mainMenu.add(ParticipantMenu);

    MenuGroup boardMenu = new MenuGroup("게시글");
    boardMenu.add(new Menu("등록", new BoardAddListener(boardList)));
    boardMenu.add(new Menu("목록", new BoardListListener(boardList)));
    boardMenu.add(new Menu("조회", new BoardDetailListener(boardList)));
    boardMenu.add(new Menu("변경", new BoardUpdateListener(boardList)));
    boardMenu.add(new Menu("삭제", new BoardDeleteListener(boardList)));
    mainMenu.add(boardMenu);

    MenuGroup readingMenu = new MenuGroup("독서록");
    readingMenu.add(new Menu("등록", new BoardAddListener(readingList)));
    readingMenu.add(new Menu("목록", new BoardListListener(readingList)));
    readingMenu.add(new Menu("조회", new BoardDetailListener(readingList)));
    readingMenu.add(new Menu("변경", new BoardUpdateListener(readingList)));
    readingMenu.add(new Menu("삭제", new BoardDeleteListener(readingList)));
    mainMenu.add(readingMenu);

    Menu helloMenu = new Menu("안녕!");
    helloMenu.addActionListener(new HeadListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FootListener());
    mainMenu.add(helloMenu);
  }
  
  private void loadParticipant() {
    try {
      FileInputStream in = new FileInputStream("participant.data");
      int size = in.read() << 8;
      size |= in.read();
      
      byte[] buf = new byte[1000];
      
      for (int i = 0; i < size; i++) {
        Participant participant = new Participant();
        participant.setNo(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        int length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        participant.setName(new String(buf, 0, length, "UTF-8"));

        participant.setAge(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());
        
        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        participant.setMovieAttendance((new String(buf, 0, length, "UTF-8")));
        
        participant.setGender((char)(in.read() << 8 | in.read()));

        participant.setMovieRating(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());
        
        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        participant.setAdditionalInfo(new String(buf, 0, length, "UTF-8"));

        participantList.add(participant);
      }
      
      Participant.userId = participantList.size()==0? 
          Participant.userId : participantList.get(participantList.size() - 1).getNo() + 1;

      in.close();
      
    } catch (Exception e) {
      System.out.println("참여자 정보를 읽는 중 오류 발생!");
      e.printStackTrace();
    }
  }
  
  private void loadBoard(String filename, List<Board> list) {
    try {
      FileInputStream in = new FileInputStream(filename);
      int size = in.read() << 8;
      size |= in.read();

      byte[] buf = new byte[1000];

      for (int i = 0; i < size; i++) {
        Board board = new Board();
        board.setNo(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        int length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        board.setTitle(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        board.setContent(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        board.setWriter(new String(buf, 0, length, "UTF-8"));

        length = in.read() << 8 | in.read();
        in.read(buf, 0, length);
        board.setPassword(new String(buf, 0, length, "UTF-8"));

        board.setViewCount(in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read());

        board.setCreatedDate(
            (long)in.read() << 56
            | (long)in.read() << 48
            | (long)in.read() << 40
            | (long)in.read() << 32
            | (long)in.read() << 24
            | (long)in.read() << 16
            | (long)in.read() << 8
            | in.read());

        list.add(board);
      }

      
      
      Board.boardNo = list.size()==0? Board.boardNo : Math.max(
          Board.boardNo,
          list.get(list.size() - 1).getNo() + 1);

      in.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 읽는 중 오류 발생!");
      e.printStackTrace();
    }
  }
    
  private void saveParticipant() {
    try {
      FileOutputStream out = new FileOutputStream("participant.data");

    
      int size = participantList.size();
      out.write(size >> 8);
      out.write(size);

      for (Participant participant : participantList) {
        int no = participant.getNo();
        out.write(no >> 24);
        out.write(no >> 16);
        out.write(no >> 8);
        out.write(no);

        byte[] bytes = participant.getName().getBytes("UTF-8");
        
        out.write(bytes.length >> 8);
        out.write(bytes.length);

  
        out.write(bytes);
        
        
        int age = participant.getAge();
        out.write(age >> 24);
        out.write(age >> 16);
        out.write(age >> 8);
        out.write(age);
        
        bytes = participant.getMovieAttendance().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);
        
        char gender = participant.getGender();
        out.write(gender >> 8);
        out.write(gender);
        
        int movierating = participant.getMovieRating();
        out.write(movierating >> 24);
        out.write(movierating >> 16);
        out.write(movierating >> 8);
        out.write(movierating);
        
  
        bytes = participant.getAdditionalInfo().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);
    
      }
      out.close();

    } catch (Exception e) {
      System.out.println("회원 정보를 저장하는 중 오류 발생!");
      e.printStackTrace();
    }
  }
  
  private void saveBoard(String filename, List<Board> list) {
    try {
      FileOutputStream out = new FileOutputStream(filename);

  
      int size = list.size();
      out.write(size >> 8);
      out.write(size);

      for (Board board : list) {
        int no = board.getNo();
        out.write(no >> 24);
        out.write(no >> 16);
        out.write(no >> 8);
        out.write(no);

        byte[] bytes = board.getTitle().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);


        bytes = board.getContent().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = board.getWriter().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        bytes = board.getPassword().getBytes("UTF-8");
        out.write(bytes.length >> 8);
        out.write(bytes.length);
        out.write(bytes);

        int viewCount = board.getViewCount();
        out.write(viewCount >> 24);
        out.write(viewCount >> 16);
        out.write(viewCount >> 8);
        out.write(viewCount);

        long createdDate = board.getCreatedDate();
        out.write((int)(createdDate >> 56));
        out.write((int)(createdDate >> 48));
        out.write((int)(createdDate >> 40));
        out.write((int)(createdDate >> 32));
        out.write((int)(createdDate >> 24));
        out.write((int)(createdDate >> 16));
        out.write((int)(createdDate >> 8));
        out.write((int)createdDate);
      }
      out.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
      e.printStackTrace();
    }
  }
}

      
    


