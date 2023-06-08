package nc7.javaproject.PreferenceSurveyApp;

import nc7.javaproject.util.Prompt;
import nc7.javaproject.vo.Participant;

public class ParticipantHandler {

  static final int MAX_SIZE = 100;
  static Participant[] participants = new Participant[MAX_SIZE];
  static int userId = 1;
  static int length = 0;

  static final char MALE = 'M';
  static final char FEMALE = 'W';

  public static void inputParticipant() {
    if (!available()) {
      System.out.println("더 이상 입력할 수 없습니다!");
      return;
    }

    Participant p = new Participant();

    p.no = Integer.parseInt(Prompt.inputString("참여자 번호? "));
    p.name = Prompt.inputString("참여자 이름? ");
    p.age = Integer.parseInt(Prompt.inputString("참여자 나이? "));
    p.movieAttendance = Boolean.parseBoolean(Prompt.inputString("영화 A 관람 여부(true/false)? "));
    p.gender = Prompt.inputString("참여자 성별(남자:M, 여자:W)? ").charAt(0);
    p.movieRating = Integer.parseInt(Prompt.inputString("영화 A에 대한 평가(1-5)? "));
    p.additionalInfo= Prompt.inputString("추가 정보? ");
    p.no = userId++;
    
    participants[length++] = p;
  }

  public static void printParticipants() {
    System.out.println("---------------------------------------");
    System.out.println("참여자 정보");
    System.out.println("---------------------------------------");

    for (int i = 0; i < length; i++) {
      Participant p = participants[i];
      System.out.printf("참여자 번호: %d\n", p.no);
      System.out.printf("참여자 이름: %s\n", p.name);
      System.out.printf("참여자 나이: %d\n", p.age);
      System.out.printf("영화 A 관람 여부: %b\n", p.movieAttendance);
      System.out.printf("참여자 성별(남자(M), 여자(W)): %c\n", p.gender);
      System.out.printf("영화 A에 대한 평가(1-5): %d\n", p.movieRating);
      System.out.printf("추가 정보: %s\n", p.additionalInfo);
      System.out.println("---------------------------------------");
      System.out.printf(toGenderString(p.gender));
    }
  }

  public static void viewParticipant() {
    String participantNo = Prompt.inputString("조회할 참여자 번호? ");
    for (int i = 0; i < length; i++) {
      Participant p = participants[i];
    if (p.no == Integer.parseInt(participantNo)) {
      System.out.printf("참여자 이름: %s\n", p.name);
      System.out.printf("참여자 나이: %d\n", p.age);
      System.out.printf("영화 A 관람 여부: %b\n", p.movieAttendance);
      System.out.printf("참여자 성별(남자(M), 여자(W)): %c\n", toGenderString(p.gender));
      System.out.printf("영화 A에 대한 평가(1-5): %d\n", p.movieRating);
      System.out.printf("추가 정보: %s\n", p.additionalInfo);
      return;
      } 
    }

    System.out.println("해당 번호의 참여자를 찾을 수 없습니다.");
  }
  
  public static String toGenderString(char gender) {
    return gender == 'M' ? "남성" : "여성";
  }

  public static void updateParticipant() {
    String participantNo = Prompt.inputString("변경할 참여자 번호? ");
    for (int i = 0; i < length; i++) {
      Participant p = participants[i];
      if (p.no == Integer.parseInt(participantNo))  {
      p.name = Prompt.inputString("변경할 참여자 이름? ");
      p.age = Integer.parseInt(Prompt.inputString("변경할 참여자 나이? "));
      p.movieAttendance = Boolean.parseBoolean(Prompt.inputString("변경할 영화 A 관람 여부(true/false)? "));
      p.gender = inputGender(p.gender);
      p.movieRating = Integer.parseInt(Prompt.inputString("변경할 영화 A에 대한 평가(1-5)? "));
      p.additionalInfo = Prompt.inputString("변경할 추가 정보? ");
      System.out.println("참여자 정보를 변경하였습니다.");
      return;
     } 
   }  
   System.out.println("해당 번호의 참여자를 찾을 수 없습니다.");
  }


  private static char inputGender(char gender) {
    String label;
    if (gender == 0) {
      label = "성별?\n";
    } else {
      label = String.format("성별(%s)?\n", toGenderString(gender));
    }
    loop: while (true) {
      String menuNo = Prompt.inputString(label + 
      "  1. 남자\n" + 
      "  2. 여자\n" + 
      "> ");

      switch (menuNo) {
        case "1":
          return MALE;
        case "2":
          return FEMALE;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  public static void deleteParticipant() {
    int participantNo = Prompt.inputInt("삭제할 참여자 번호? ");
    int deletedIndex = indexOf(participantNo);
    if (deletedIndex == -1) {
      System.out.println("해당 번호의 참여자가 없습니다!");
      return;
    }

    for (int i = deletedIndex; i < length - 1; i++) {
      participants[i] = participants[i + 1];
    }

    participants[--length] = null;
  }
  

  private static int indexOf(int participantNo) {
    for (int i = 0; i < length; i++) {
      Participant p = participants[i];
      if (p.no == participantNo) {
        return i;
      }
    }
    return -1;
  }

  private static boolean available() {
    return length < MAX_SIZE;
  }
}


