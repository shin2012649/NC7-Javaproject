package nc7.javaproject;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    System.out.println("영화 A 관객 선호도 조사");
    System.out.println("----------------------------------");

    // 키보드 스캐너 준비
    Scanner scanner = new Scanner(System.in);

    final int MAX_SIZE = 100;
    int participantNumber = 1;
    int length = 0;

    int[] no = new int[MAX_SIZE];
    String[] name = new String[MAX_SIZE];
    int[] age = new int[MAX_SIZE];
    boolean[] movieAttendance = new boolean[MAX_SIZE];
    char[] gender = new char[MAX_SIZE];
    int[] movieRating = new int[MAX_SIZE];
    String[] additionalInfo = new String[MAX_SIZE];

    // 참여자 정보 등록
    for (int i = 0; i < MAX_SIZE; i++) {
      System.out.print("참여자 이름? ");
      name[i] = scanner.next();

      System.out.print("참여자 나이? ");
      age[i] = scanner.nextInt();

      System.out.print("영화 A 관람 여부(true/false)? ");
      movieAttendance[i] = scanner.nextBoolean();

      loop: while (true) {
        System.out.print("참여자 성별(남자:M, 여자:W)? ");
        String genderStr = scanner.next();
        gender[i] = genderStr.charAt(0);
        if (gender[i] == 'M' || gender[i] == 'W') {
          break loop;
        } else {
          System.out.println("무효한 입력입니다. 남자는 'M', 여자는 'W'로 입력하세요.");
        }
      }

      System.out.print("영화 A에 대한 평가(1-5)? ");
      movieRating[i] = scanner.nextInt();

      System.out.print("추가 정보? ");
      scanner.nextLine(); // 버퍼 비우기
      additionalInfo[i] = scanner.nextLine();

      no[i] = participantNumber++;
      length++;

      System.out.print("계속 하시겠습니까?(Y/n) ");
      String response = scanner.nextLine();
      if (!response.isEmpty() && !response.equalsIgnoreCase("Y")) {
        break;
      }
    }

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

    scanner.close();
  }
}





