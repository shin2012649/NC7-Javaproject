package nc7.javaproject;

import java.util.Scanner;

public class App {
  static Scanner scanner = new Scanner(System.in);
  static final int MAX_SIZE = 100;
  static int[] participantNumber = new int[MAX_SIZE];
  static String[] participantName = new String[MAX_SIZE];
  static int[] participantAge = new int[MAX_SIZE];
  static boolean[] movieAttendance = new boolean[MAX_SIZE];
  static char[] gender = new char[MAX_SIZE];
  static int[] movieRating = new int[MAX_SIZE];
  static String[] additionalInfo = new String[MAX_SIZE];
  static int length = 0;

  public static void main(String[] args) {
    printTitle();

    while (length < MAX_SIZE) {
      inputParticipantData(length);
      if (!promptContinue()) {
        break;
      }
    }

    printParticipantData();

    scanner.close();
  }

  static void printTitle() {
    System.out.println("영화 A 선호도 조사");
    System.out.println("----------------------------------");
  }

  static void inputParticipantData(int index) {
    System.out.print("참여자 번호? ");
    participantNumber[index] = scanner.nextInt();

    System.out.print("참여자 이름? ");
    participantName[index] = scanner.next();

    System.out.print("참여자 나이? ");
    participantAge[index] = scanner.nextInt();

    System.out.print("영화 A 관람 여부(true/false)? ");
    movieAttendance[index] = scanner.nextBoolean();

    System.out.print("참여자 성별(남자:M, 여자:W)? ");
    gender[index] = scanner.next().charAt(0);

    System.out.print("영화 A에 대한 평가(1-5)? ");
    movieRating[index] = scanner.nextInt();

    scanner.nextLine(); // 버퍼 비우기

    System.out.print("추가 정보? ");
    additionalInfo[index] = scanner.nextLine();

    System.out.println("---------------------------------------");

    length++;
  }

  static boolean promptContinue() {
    System.out.print("더 입력하시겠습니까?(Y/n) ");
    String response = scanner.next();
    if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
      return false;
    }
    return true;
  }

  static void printParticipantData() {
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
}






