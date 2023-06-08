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
    if (!available()) {
      System.out.println("더 이상 입력할 수 없습니다!");
      return;
    }

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

  public static void viewParticipant() {
    String participantNo = Prompt.inputString("조회할 참여자 번호? ");
    int index = findParticipantIndex(Integer.parseInt(participantNo));
    if (index != -1) {
      System.out.printf("참여자 번호: %d\n", participantNumber[index]);
      System.out.printf("참여자 이름: %s\n", participantName[index]);
      System.out.printf("참여자 나이: %d\n", participantAge[index]);
      System.out.printf("영화 A 관람 여부: %b\n", movieAttendance[index]);
      System.out.printf("참여자 성별(남자(M), 여자(W)): %c\n", gender[index]);
      System.out.printf("영화 A에 대한 평가(1-5): %d\n", movieRating[index]);
      System.out.printf("추가 정보: %s\n", additionalInfo[index]);
    } else {
      System.out.println("해당 번호의 참여자를 찾을 수 없습니다.");
    }
  }

  public static void deleteParticipant() {
    String participantNo = Prompt.inputString("삭제할 참여자 번호? ");
    int index = findParticipantIndex(Integer.parseInt(participantNo));
    if (index != -1) {
      // Shift the elements to the left to overwrite the deleted participant
      for (int i = index; i < length - 1; i++) {
        participantNumber[i] = participantNumber[i + 1];
        participantName[i] = participantName[i + 1];
        participantAge[i] = participantAge[i + 1];
        movieAttendance[i] = movieAttendance[i + 1];
        gender[i] = gender[i + 1];
        movieRating[i] = movieRating[i + 1];
        additionalInfo[i] = additionalInfo[i + 1];
      }
      // Set the last element to default values
      participantNumber[length - 1] = 0;
      participantName[length - 1] = null;
      participantAge[length - 1] = 0;
      movieAttendance[length - 1] = false;
      gender[length - 1] = '\u0000';
      movieRating[length - 1] = 0;
      additionalInfo[length - 1] = null;
      length--;
      System.out.println("참여자를 삭제하였습니다.");
    } else {
      System.out.println("해당 번호의 참여자를 찾을 수 없습니다.");
    }
  }

  public static void updateParticipant() {
    String participantNo = Prompt.inputString("변경할 참여자 번호? ");
    int index = findParticipantIndex(Integer.parseInt(participantNo));
    if (index != -1) {
      participantName[index] = Prompt.inputString("변경할 참여자 이름? ");
      participantAge[index] = Integer.parseInt(Prompt.inputString("변경할 참여자 나이? "));
      movieAttendance[index] = Boolean.parseBoolean(Prompt.inputString("변경할 영화 A 관람 여부(true/false)? "));
      gender[index] = Prompt.inputString("변경할 참여자 성별(남자:M, 여자:W)? ").charAt(0);
      movieRating[index] = Integer.parseInt(Prompt.inputString("변경할 영화 A에 대한 평가(1-5)? "));
      additionalInfo[index] = Prompt.inputString("변경할 추가 정보? ");
      System.out.println("참여자 정보를 변경하였습니다.");
    } else {
      System.out.println("해당 번호의 참여자를 찾을 수 없습니다.");
    }
  }

  private static int findParticipantIndex(int participantNo) {
    for (int i = 0; i < length; i++) {
      if (participantNumber[i] == participantNo) {
        return i;
      }
    }
    return -1;
  }

  private static boolean available() {
    return length < MAX_SIZE;
  }
}


