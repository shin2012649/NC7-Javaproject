package nc7.javaproject.PreferenceSurveyApp;

import nc7.javaproject.util.Prompt;

public class ParticipantHandler {

  static final int MAX_SIZE = 100;
  static int[] participantNumber = new int[MAX_SIZE];
  static String[] participantName = new String[MAX_SIZE];
  static int[] participantAge = new int[MAX_SIZE];
  static boolean[] movieAttendance = new boolean[MAX_SIZE];
  static char[] gender = new char[MAX_SIZE];
  static int[] movieRating = new int[MAX_SIZE];
  static String[] additionalInfo = new String[MAX_SIZE];
  static int length = 0;

  public static void inputParticipant() {
    participantNumber[length] = Integer.parseInt(Prompt.inputString("참여자 번호? "));
    participantName[length] = Prompt.inputString("참여자 이름? ");
    participantAge[length] = Integer.parseInt(Prompt.inputString("참여자 나이? "));
    movieAttendance[length] = Boolean.parseBoolean(Prompt.inputString("영화 A 관람 여부(true/false)? "));
    gender[length] = Prompt.inputString("참여자 성별(남자:M, 여자:W)? ").charAt(0);
    movieRating[length] = Integer.parseInt(Prompt.inputString("영화 A에 대한 평가(1-5)? "));
    additionalInfo[length] = Prompt.inputString("추가 정보? ");
    length++;
  }

  public static void printParticipants() {
    System.out.println("---------------------------------------");
    System.out.println("참여자 정보");
    System.out.println("---------------------------------------");

    for (int i = 0; i < length; i++) {
      System.out.printf("참여자 번호: %d\n", participantNumber[i]);
      System.out.printf("참여자 이름: %s\n", participantName[i]);
      System.out.printf("참여자 나이: %d\n", participantAge[i]);
      System.out.printf("영화 A 관람 여부: %b\n", movieAttendance[i]);
      System.out.printf("참여자 성별(남자(M), 여자(W)): %c\n", gender[i]);
      System.out.printf("영화 A에 대한 평가(1-5): %d\n", movieRating[i]);
      System.out.printf("추가 정보: %s\n", additionalInfo[i]);
      System.out.println("---------------------------------------");
    }
  }

  public static boolean available() {
    return length < MAX_SIZE;
  }
}

