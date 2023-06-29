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
import nc7.javaproject.io.BufferedInputStream;
import nc7.javaproject.io.BufferedOutputStream;
import nc7.javaproject.io.DataInputStream;
import nc7.javaproject.io.DataOutputStream;
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
      FileInputStream in0 = new FileInputStream("participant.data");
      BufferedInputStream in1 = new BufferedInputStream(in0); 
      DataInputStream in = new DataInputStream(in1);

      int size = in.readShort();
      
      for (int i = 0; i < size; i++) {
        Participant participant = new Participant();
        participant.setNo(in.readInt());
        participant.setName(in.readUTF());
        participant.setAge(in.readInt());
        participant.setMovieAttendance(in.readUTF());
        participant.setGender(in.readChar());
        participant.setMovieRating(in.readInt());
        participant.setAdditionalInfo(in.readUTF());
      
        participantList.add(participant);
      }
      
//      Participant.userId = participantList.size()==0? 
//          Participant.userId : participantList.get(participantList.size() - 1).getNo() + 1;

      Participant.userId = participantList.get(participantList.size() - 1).getNo() + 1;
      
      in.close();
      
    } catch (Exception e) {
      System.out.println("참여자 정보를 읽는 중 오류 발생!");
//      e.printStackTrace();
    }
  }
  
  private void loadBoard(String filename, List<Board> list) {
    try {
      FileInputStream in0 = new FileInputStream(filename);
      BufferedInputStream in1 = new BufferedInputStream(in0); 
      DataInputStream in = new DataInputStream(in1);

      int size = in.readShort();

      for (int i = 0; i < size; i++) {
        Board board = new Board();
        board.setNo(in.readInt());
        board.setTitle(in.readUTF());
        board.setContent(in.readUTF());
        board.setWriter(in.readUTF());
        board.setPassword(in.readUTF());
        board.setViewCount(in.readInt());
        board.setCreatedDate(in.readLong());
        list.add(board);
      }
      
//      Board.boardNo = list.size()==0? Board.boardNo : Math.max(
//          Board.boardNo,
//          list.get(list.size() - 1).getNo() + 1);
      
      if(boardList.size() > 0) {
      
      Board.boardNo = Math.max(
          Board.boardNo,
          list.get(list.size() - 1).getNo() + 1);
      }
      in.close();
      

    } catch (Exception e) {
      System.out.println(filename + " 파일을 읽는 중 오류 발생!");
    }
  }
    
  private void saveParticipant() {
    try {
      FileOutputStream out0 = new FileOutputStream("participant.data");
      BufferedOutputStream out1 = new BufferedOutputStream(out0); 
      DataOutputStream out = new DataOutputStream(out1);
      
      
      out.writeShort(participantList.size());

      for (Participant participant : participantList) {
        out.writeInt(participant.getNo());
        out.writeUTF(participant.getName());
        out.writeInt(participant.getAge());
        out.writeUTF(participant.getMovieAttendance());
        out.writeChar(participant.getGender());
        out.writeInt(participant.getMovieRating());
        out.writeUTF(participant.getAdditionalInfo());
       
      }
      out.close();

    } catch (Exception e) {
      System.out.println("회원 정보를 저장하는 중 오류 발생!");
//      e.printStackTrace();
    }
  }
  
  
  private void saveBoard(String filename, List<Board> list) {
    try {
      FileOutputStream out0 = new FileOutputStream(filename);
      BufferedOutputStream out1 = new BufferedOutputStream(out0); // <== Decorator(장식품) 역할 수행!
      DataOutputStream out = new DataOutputStream(out1);

      out.writeShort(list.size());

      for (Board board : list) {
        out.writeInt(board.getNo());
        out.writeUTF(board.getTitle());
        out.writeUTF(board.getContent());
        out.writeUTF(board.getWriter());
        out.writeUTF(board.getPassword());
        out.writeInt(board.getViewCount());
        out.writeLong(board.getCreatedDate());
      }
      out.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
    }
  }
}

      
    


