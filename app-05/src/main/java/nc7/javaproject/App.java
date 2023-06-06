package nc7.javaproject;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    System.out.println("영화 A 관객 선호도 조사");
    System.out.println("----------------------------------");

    // 키보드 스캐너 준비
    Scanner scanner = new Scanner(System.in);

    final int SIZE = 100;

    int[] participantNumber = new int[SIZE];
    String[] participantName = new String[SIZE];
    int[] participantAge = new int[SIZE];
    boolean[] movieAttendance = new boolean[SIZE];
    char[] gender = new char[SIZE];
    int[] movieRating = new int[SIZE];
    String[] additionalInfo = new String[SIZE];

    for (int i = 0; i < SIZE; i++) {
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

      System.out.print("추가 정보? ");
      scanner.nextLine(); // 버퍼 비우기
      additionalInfo[i] = scanner.nextLine();
    }

    System.out.println("---------------------------------------");

    for (int i = 0; i < SIZE; i++) {
      System.out.printf("참여자 번호: %d\n", participantNumber[i]);
      System.out.printf("참여자 이름: %s\n", participantName[i]);
      System.out.printf("참여자 나이: %d\n", participantAge[i]);
      System.out.printf("영화 A 관람 여부: %b\n", movieAttendance[i]);
      System.out.printf("참여자 성별(남자(M), 여자(W)): %c\n", gender[i]);
      System.out.printf("영화 A에 대한 평가(1-5): %d\n", movieRating[i]);
      System.out.printf("추가 정보: %s\n", additionalInfo[i]);
      System.out.println("---------------------------------------");
    }
    scanner.close();
  }
}




