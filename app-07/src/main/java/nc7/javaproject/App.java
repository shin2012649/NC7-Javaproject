package nc7.javaproject;


import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    final int MAX_SIZE = 100;
    int participantNumber = 1;
    int length = 0;

    int[] participantNumber = new int[MAX_SIZE];
    String[] participantName = new String[MAX_SIZE];
    int[] participantAge = new int[MAX_SIZE];
    boolean[] movieAttendance = new boolean[MAX_SIZE];
    char[] gender = new char[MAX_SIZE];
    int[] movieRating = new int[MAX_SIZE];

    printTitle();

    for (int i = 0; i < MAX_SIZE; i++) {
      inputParticipantData(scanner, i, participantNumber, participantName, participantAge, movieAttendance, gender, movieRating);
      length++;
      if (!promptContinue(scanner)) {
        break;
      }
    }

    printParticipantData(length, participantNumber, participantName, participantAge, movieAttendance, gender, movieRating);

    scanner.close();
  }

  static void printTitle() {
    System.out.println("영화 A 선호도 조사");
    System.out.println("----------------------------------");
  }

  static void inputParticipantData(Scanner scanner, int i, int[] participantNumber, String[] participantName, int[] participantAge, boolean[] movieAttendance, char[] gender, int[] movieRating) {

    System.out.print("참여자 번호? ");
    participantNumber[i] = scanner.nextInt();

    System.out.print("참여자 이름? ");
    participantName[i] = scanner.next();

    System.out.print("참여자 나이? ");
    participantAge[i] = scanner.nextInt();

    System.out.print("영화 A 관람 여부(true/false)? ");
    movieAttendance[i] = scanner.nextBoolean();

    System.out.print("참여자 성별(남자:M, 여자:W)? ");
    String genderStr = scanner.next();
    gender[i] = genderStr.charAt(0);

    System.out.print("영화 A에 대한 평가(1-5)? ");
    movieRating[i] = scanner.nextInt();

    System.out.println("---------------------------------------");
  }

  static boolean promptContinue(Scanner scanner) {
    System.out.print("더 입력하시겠습니까?(Y/n) ");
    String response = scanner.next();
    if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
      return false;
    }
    return true;
  }

  static void printParticipantData(int length, int[] participantNumber, String[] participantName, int[] participantAge, boolean[] movieAttendance, char[] gender, int[] movieRating) {
    System.out.println("---------------------------------------");
    System.out.println("참여자 정보");
    System.out.println("---------------------------------------");



    for (int i = 0; i < length; i++) {
      System.out.printf("참여자 번호: %d\n", no[i]);
      System.out.printf("참여자 이름: %s\n", name[i]);
      System.out.printf("참여자 나이: %d\n", age[i]);
      System.out.printf("영화 A 관람 여부: %b\n", movieAttendance[i]);
      System.out.printf("참여자 성별(남자(M), 여자(W)): %c\n", gender[i]);
      System.out.printf("영화 A에 대한 평가(1-5): %d\n", movieRating[i]);
      System.out.printf("추가 정보: %s\n", additionalInfo[i]);
      System.out.println("---------------------------------------");
    }
  }
}





