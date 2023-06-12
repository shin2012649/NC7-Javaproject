package nc7.javaproject.PreferenceSurveyApp;

import nc7.javaproject.util.Prompt;
import nc7.javaproject.vo.Participant;

public class ParticipantHandler {

  static final int MAX_SIZE = 100;
  static Participant[] participants = new Participant[MAX_SIZE];
  static int userId = 1;
  static int length = 0;
  static int[] ratingCounts = new int[5];
  static int totalScore = 0;
  static int participantCount = 0;

  static final char MALE = 'M';
  static final char FEMALE = 'W';

  public static void inputParticipant() {
    if (!available()) {
      System.out.println("더 이상 입력할 수 없습니다!");
      return;
    }

    Participant p = new Participant();

    p.setNo(userId++);
    p.setName(Prompt.inputString("참여자 이름? "));
    p.setAge(Integer.parseInt(Prompt.inputString("참여자 나이? ")));
    p.setMovieAttendance(Boolean.parseBoolean(Prompt.inputString("영화 A 관람 여부(true/false)? ")));
    p.setGender(inputGender((char) 0));
    p.setMovieRating(Integer.parseInt(Prompt.inputString("영화 A에 대한 평점(1-5)? ")));
    p.setAdditionalInfo(Prompt.inputString("추가 정보? "));

    ratingCounts[p.getMovieRating() - 1]++;
    totalScore += p.getMovieRating();
    participantCount++;

    participants[length++] = p;
  }

  public static void printRatingCounts() {
    System.out.println("등급별 선택 횟수:");
    for (int i = 0; i < ratingCounts.length; i++) {
      System.out.printf("%d점: %d명\n", i + 1, ratingCounts[i]);
    }
  }

  public static void printAverageScore() {
    if (participantCount != 0) {
      double averageScore = (double) totalScore / participantCount;
      System.out.printf("총 점수 평균: %.2f\n", averageScore);
    } else {
      System.out.println("참여자가 없습니다.");
    }
  }

  public static void printParticipants() {
    System.out.println("---------------------------------------");
    System.out.println("참여자 정보");
    System.out.println("---------------------------------------");

    for (int i = 0; i < length; i++) {
      Participant p = participants[i];
      System.out.printf("참여자 번호: %d\n", p.getNo());
      System.out.printf("참여자 이름: %s\n", p.getName());
      System.out.printf("참여자 나이: %d\n", p.getAge());
      System.out.printf("영화 A 관람 여부: %b\n", p.isMovieAttendance());
      System.out.printf("참여자 성별(남자(M), 여자(W)): %c\n", p.getGender());
      System.out.printf("영화 A에 대한 평가(1-5): %d\n", p.getMovieRating());
      System.out.printf("추가 정보: %s\n", p.getAdditionalInfo());
      System.out.println("---------------------------------------");
      System.out.printf(toGenderString(p.getGender()));
    }
  }

  public static void viewParticipant() {
    String participantNo = Prompt.inputString("조회할 참여자 번호? ");
    for (int i = 0; i < length; i++) {
      Participant p = participants[i];
      if (Integer.toString(p.getNo()).equals(participantNo)) {
        System.out.printf("참여자 이름: %s\n", p.getName());
        System.out.printf("참여자 나이: %d\n", p.getAge());
        System.out.printf("영화 A 관람 여부: %b\n", p.isMovieAttendance());
        System.out.printf("참여자 성별(남자(M), 여자(W)): %c\n", p.getGender());
        System.out.printf("영화 A에 대한 평가(1-5): %d\n", p.getMovieRating());
        System.out.printf("추가 정보: %s\n", p.getAdditionalInfo());
        return;
      }
    }

    System.out.println("해당 번호의 참여자를 찾을 수 없습니다.");
  }

  public static void updateParticipant() {
    String participantNo = Prompt.inputString("변경할 참여자 번호? ");
    for (int i = 0; i < length; i++) {
      Participant p = participants[i];
      if (p.getNo() == Integer.parseInt(participantNo)) {
        p.setName(Prompt.inputString("변경할 참여자 이름? "));
        p.setAge(Integer.parseInt(Prompt.inputString("변경할 참여자 나이? ")));
        p.setMovieAttendance(Boolean.parseBoolean(Prompt.inputString("변경할 영화 A 관람 여부(true/false)? ")));
        p.setGender(inputGender(p.getGender()));
        p.setMovieRating(Integer.parseInt(Prompt.inputString("변경할 영화 A에 대한 평가(1-5)? ")));
        p.setAdditionalInfo(Prompt.inputString("변경할 추가 정보? "));
        System.out.println("참여자 정보를 변경하였습니다.");
        return;
      }
    }
    System.out.println("해당 번호의 참여자를 찾을 수 없습니다.");
  }

  public static void deleteParticipant() {
    String participantNo = Prompt.inputString("삭제할 참여자 번호? ");
    int deletedIndex = indexOf(Integer.parseInt(participantNo));
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
      if (p.getNo() == participantNo) {
        return i;
      }
    }
    return -1;
  }

  private static boolean available() {
    return length < MAX_SIZE;
  }
}


