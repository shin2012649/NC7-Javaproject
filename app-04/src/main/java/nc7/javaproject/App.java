
package nc7.javaproject;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    System.out.println("영화 A 관객 선호도 조사");
    System.out.println("----------------------------------");

    // 키보드 스캐너 준비
    Scanner scanner = new Scanner(System.in);

    System.out.print("참여자 번호? ");
    int participantNumber = scanner.nextInt();

    System.out.print("참여자 이름? ");
    String participantName = scanner.next();

    System.out.print("참여자 나이? ");
    int participantAge = scanner.nextInt();

    System.out.print("영화 A 관람 여부(true/false)? ");
    boolean movieAttendance = scanner.nextBoolean();

    System.out.print("참여자 성별(남자:M, 여자:W)? ");
    String genderStr = scanner.next();
    char gender = genderStr.charAt(0);

    System.out.print("영화 A에 대한 평가(1-5)? ");
    int movieRating = scanner.nextInt();

    System.out.println("---------------------------------------");

    System.out.printf("참여자 번호: %d\n", participantNumber);
    System.out.printf("참여자 이름: %s\n", participantName);
    System.out.printf("참여자 나이: %d\n", participantAge);
    System.out.printf("영화 A 관람 여부: %b\n", movieAttendance);
    System.out.printf("참여자 성별(남자(M), 여자(W)): %c\n", gender);
    System.out.printf("영화 A에 대한 평가(1-5): %d\n", movieRating);

    scanner.close();
  }
}





