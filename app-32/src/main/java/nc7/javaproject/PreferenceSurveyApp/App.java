package nc7.javaproject.PreferenceSurveyApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
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
    
    loadParticipant("participant.csv", participantList);
    loadBoard("board.csv", boardList);
    loadBoard("reading.csv", readingList);
    
  }
  
  private void saveData() {
    
    saveParticipant("participant.csv", participantList);
    saveBoard("board.csv", boardList);
    saveBoard("reading.csv", readingList);
    
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
  
  private void loadParticipant(String filename, List<Participant> list) {
    try {
      FileReader in0 = new FileReader(filename);
      BufferedReader in = new BufferedReader(in0); // <== Decorator 역할을 수행!

      String line = null;
      
      while((line = in.readLine()) != null) {
        String[] values = line.split(",");
        Participant participant = new Participant();
        participant.setNo(Integer.parseInt(values[0]));
        participant.setName(values[1]);
        participant.setAge(Integer.parseInt(values[2]));
        participant.setMovieAttendance(values[3]);
        participant.setGender(values[4].charAt(0));
        participant.setMovieRating(Integer.parseInt(values[5]));
        participant.setAdditionalInfo(values[6]);
        list.add(participant);
      }
    
//      Participant.userId = participantList.size()==0? 
//          Participant.userId : participantList.get(participantList.size() - 1).getNo() + 1;

      if (list.size() > 0) {
        
        Participant.userId = participantList.get(participantList.size() - 1).getNo() + 1;
      }
      
      in.close();
      
    } catch (Exception e) {
      System.out.println("참여자 정보를 읽는 중 오류 발생!");   
    }
  }
  
  private void loadBoard(String filename, List<Board> list) {
    try {
      FileReader in0 = new FileReader(filename);
      BufferedReader in = new BufferedReader(in0); // <== Decorator 역할을 수행!

      String line = null;

      while ((line = in.readLine()) != null) {
        String[] values = line.split(",");

        Board board = new Board();
        board.setNo(Integer.parseInt(values[0]));
        board.setTitle(values[1]);
        board.setContent(values[2]);
        board.setWriter(values[3]);
        board.setPassword(values[4]);
        board.setViewCount(Integer.parseInt(values[5]));
        board.setCreatedDate(Long.parseLong(values[6]));

        list.add(board);
      }
      
      if(list.size() > 0) {
      
      Board.boardNo = Math.max(
          Board.boardNo,
          list.get(list.size() - 1).getNo() + 1);
      }
      in.close();
      

    } catch (Exception e) {
      System.out.println(filename + " 파일을 읽는 중 오류 발생!");
    }
  }
    
  private void saveParticipant(String filename, List<Participant> list) {
    try {
      FileWriter out0 = new FileWriter(filename);
      BufferedWriter out1 = new BufferedWriter(out0); // <== Decorator(장식품) 역할 수행!
      PrintWriter out = new PrintWriter(out1);

      for (Participant participant : list) {
        out.printf("%d,%s,%d,%s,%c,%d,%s\n",
        participant.getNo(),
        participant.getName(),
        participant.getAge(),
        participant.getMovieAttendance(),
        participant.getGender(),
        participant.getMovieRating(),
        participant.getAdditionalInfo());
      }
      out.close();

    } catch (Exception e) {
      System.out.println("회원 정보를 저장하는 중 오류 발생!");
    }
  }
  
  
  private void saveBoard(String filename, List<Board> list) {
    try {
      FileWriter out0 = new FileWriter(filename);
      BufferedWriter out1 = new BufferedWriter(out0); // <== Decorator(장식품) 역할 수행!
      PrintWriter out = new PrintWriter(out1); // <== Decorator(장식품) 역할 수행!

      for (Board board : list) {
        out.printf("%d,%s,%s,%s,%s,%d,%d\n",
            board.getNo(),
            board.getTitle(),
            board.getContent(),
            board.getWriter(),
            board.getPassword(),
            board.getViewCount(),
            board.getCreatedDate());
      }
      out.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
    }
  }
}

      
    


