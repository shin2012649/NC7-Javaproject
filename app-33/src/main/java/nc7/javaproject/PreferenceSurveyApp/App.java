package nc7.javaproject.PreferenceSurveyApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;
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
import nc7.javaproject.vo.CsvObject;
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
    System.out.println("영화 평가 프로그램");
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
    
    loadCsv("participant.csv", participantList, Participant.class);
    loadCsv("board.csv", boardList, Board.class);
    loadCsv("reading.csv", readingList, Board.class);
  }
  
  private void saveData() {
    saveCsv("participant.csv", participantList);
    saveCsv("board.csv", boardList);
    saveCsv("reading.csv", readingList);
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
  
 
  @SuppressWarnings("unchecked")
  private <T extends CsvObject> void loadCsv(String filename, List<T> list, Class<T> clazz) {
    try {
      Method factoryMethod = clazz.getDeclaredMethod("fromCsv", String.class);
      
      FileReader in0 = new FileReader(filename);
      BufferedReader in = new BufferedReader(in0); 

      String line = null;
      
      while((line = in.readLine()) != null) {
        list.add((T)factoryMethod.invoke(null, line));
      } 
      in.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 읽는 중 오류 발생!");
      e.printStackTrace();
    
    }
  }
  
  private void saveCsv(String filename, List<? extends CsvObject> list) {
    try {
      FileWriter out0 = new FileWriter(filename);
      BufferedWriter out1 = new BufferedWriter(out0); // <== Decorator(장식품) 역할 수행!
      PrintWriter out = new PrintWriter(out1); // <== Decorator(장식품) 역할 수행!

      for (CsvObject obj : list) {
        out.println(obj.toCsvString());
        // Board나 Member 클래스에 필드가 추가/변경/삭제되더라도
        // 여기 코드를 변경할 필요가 없다.
        // 이것이 Information Expert 설계를 적용하는 이유다!
        // 설계를 어떻게 하느냐에 따라 유지보수가 쉬워질 수도 있고,
        // 어려워질 수도 있다.
      }
      out.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
      e.printStackTrace();
    }
  }
}
  
  

      
    


