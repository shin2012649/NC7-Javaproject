package nc7.javaproject.PreferenceSurveyApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
import nc7.javaproject.vo.AutoIncrement;
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
    loadJson("participant.json", participantList, Participant.class);
    loadJson("board.json", boardList, Board.class);
    loadJson("reading.json", readingList, Board.class);
  }

  private void saveData() {
    saveJson("participant.json", participantList);
    saveJson("board.json", boardList);
    saveJson("reading.json", readingList);
  }

  private void prepareMenu() {

    MenuGroup ParticipantMenu = new MenuGroup("시사회 참여");
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

    MenuGroup readingMenu = new MenuGroup("평론");
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



  private <T> void loadJson(String filename, List<T> list, Class<T> clazz) {
    try {
      FileReader in0 = new FileReader(filename);
      BufferedReader in = new BufferedReader(in0);

      StringBuilder strBuilder = new StringBuilder();

      String line = null;

      while((line = in.readLine()) != null) {
        strBuilder.append(line);
      }

      in.close();

      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
      Collection<T> objects = gson.fromJson(strBuilder.toString(),
          TypeToken.getParameterized(Collection.class, clazz).getType());

      list.addAll(objects);

//      Class<?>[] interfaces = clazz.getInterfaces();
//      for (Class<?> info : interfaces) {
//        if (info == AutoIncrement.class) {
//          AutoIncrement autoIncrement = (AutoIncrement) list.get(list.size() - 1);
//          autoIncrement.updateKey();
//          break;
      if (!list.isEmpty()) {
        T lastObject = list.get(list.size() - 1);
        Class<?>[] interfaces = lastObject.getClass().getInterfaces();
        for (Class<?> info : interfaces) {
          if (info == AutoIncrement.class) {
            AutoIncrement autoIncrement = (AutoIncrement) lastObject;
            autoIncrement.updateKey();
            break;
        }
      }
    }

    } catch (Exception e) {
      System.out.println(filename + " 파일을 읽는 중 오류 발생!");
      e.printStackTrace();

    }
  }

  private void saveJson(String filename, List<?> list) {
    try {

      FileWriter out0 = new FileWriter(filename);
      BufferedWriter out = new BufferedWriter(out0);

      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create();
      out.write(gson.toJson(list));

      out.close();

    } catch (Exception e) {
      System.out.println(filename + " 파일을 저장하는 중 오류 발생!");
    }
  }
}








