package nc7.javaproject.PreferenceSurveyApp;

import nc7.javaproject.util.Prompt;
import nc7.javaproject.vo.Participant;

public class ParticipantHandler {

  private static final int MAX_SIZE = 100;
  private Prompt prompt;
  private Participant[] participants = new Participant[MAX_SIZE];
  private int length = 0;
  private int[] ratingCounts = new int[5];
  private int totalScore = 0;
  private int participantCount = 0;

  public ParticipantHandler(Prompt prompt){
    this.prompt = prompt;
  }

  public void inputParticipant() {
    if (!this.available()) {
      System.out.println("더 이상 입력할 수 없습니다!");
      return;
    }

    Participant p = new Participant();

    p.setName(this.prompt.inputString("참여자 이름? "));
    p.setAge(Integer.parseInt(this.prompt.inputString("참여자 나이? ")));
    p.setMovieAttendance(this.prompt.inputString("영화 A 재관람 의사(Y/N)? "));
    p.setGender(inputGender((char) 0));
    p.setMovieRating(Integer.parseInt(this.prompt.inputString("영화 A에 대한 평점(1-5)? ")));
    p.setAdditionalInfo(this.prompt.inputString("추가 정보? "));

    ratingCounts[p.getMovieRating() - 1]++;
    totalScore += p.getMovieRating();
    participantCount++;

    this.participants[this.length++] = p;
  }

  public void printRatingCounts() {
    System.out.println("등급별 선택 횟수:");
    for (int i = 0; i < ratingCounts.length; i++) {
      System.out.printf("%d점: %d명\n", i + 1, ratingCounts[i]);
    }
  }

  public void printAverageScore() {
    if (participantCount != 0) {
      double averageScore = (double) totalScore / participantCount;
      System.out.printf("총 점수 평균: %.2f\n", averageScore);
    } else {
      System.out.println("참여자가 없습니다.");
    }
  }

  public void printParticipants() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름 나이, 관람여부, 성별, 평점, 추가정보");
    System.out.println("---------------------------------------");

    for (int i = 0; i < this.length; i++) {
      Participant p = this.participants[i];
      
      System.out.printf("%d, %s, %d, %s, %s, %d, %s\n",
      p.getNo(), p.getName(), p.getAge(), p.getMovieAttendance(), p.getGender(),p.getMovieRating(), p.getAdditionalInfo());
      System.out.println("---------------------------------------");
    }
  }

  public void viewParticipant() {
    String participantNo = this.prompt.inputString("조회할 참여자 번호? ");
    for (int i = 0; i < this.length; i++) {
      Participant p = this.participants[i];
      if (p.getNo() == Integer.parseInt(participantNo)) {
        System.out.printf("참여자 이름: %s\n", p.getName());
        System.out.printf("참여자 나이: %d\n", p.getAge());
        System.out.printf("영화  재관람 의사: %s\n", p.getMovieAttendance());
        System.out.printf("성별: %s\n", toGenderString(p.getGender()));
        System.out.printf("영화 A에 대한 평가(1-5): %d\n", p.getMovieRating());
        System.out.printf("추가 정보: %s\n", p.getAdditionalInfo());
        return;
      }
    }

    System.out.println("해당 번호의 참여자를 찾을 수 없습니다.");
  }

  public  void updateParticipant() {
    String participantNo = this.prompt.inputString("변경할 참여자 번호? ");
    for (int i = 0; i < this.length; i++) {
      Participant p = this.participants[i];
      if (p.getNo() == Integer.parseInt(participantNo)) {
        p.setName(this.prompt.inputString("변경할 참여자 이름? "));
        p.setAge(Integer.parseInt(this.prompt.inputString("변경할 참여자 나이? ")));
        p.setMovieAttendance(this.prompt.inputString("변경할 영화 재관람 의사(Y/N)? "));
        p.setGender(inputGender(p.getGender()));
        p.setMovieRating(Integer.parseInt(this.prompt.inputString("변경할 영화 A에 대한 평가(1-5)? ")));
        p.setAdditionalInfo(this.prompt.inputString("변경할 추가 정보? "));
        System.out.println("참여자 정보를 변경하였습니다.");
        return;
      }
    }
    System.out.println("해당 번호의 참여자를 찾을 수 없습니다.");
  }

  private String toGenderString(char gender) {
    switch (gender) {
      case Participant.MALE:
        return "남자";
      case Participant.FEMALE:
        return "여자";
      default:
        return "알 수 없음";
    }
  }

  private  char inputGender(char gender) {
    String label;
    if (gender == 0) {
      label = "성별?\n";
    } else {
      label = String.format("성별(%s)?\n", toGenderString(gender));
    }

    while (true) {
      String menuNo = this.prompt.inputString(label +
          "  1. 남자\n" +
          "  2. 여자\n" +
          "> ");

      switch (menuNo) {
        case "1":
          return Participant.MALE;
        case "2":
          return Participant.FEMALE;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  public void deleteParticipant() {
    int participantNo = this.prompt.inputInt("삭제할 참여자 번호? ");
    int deletedIndex = indexOf(participantNo);
    if (deletedIndex == -1) {
      System.out.println("해당 번호의 참여자가 없습니다!");
      return;
    }

    for (int i = deletedIndex; i < this.length - 1; i++) {
      this.participants[i] = this.participants[i + 1];
    }

    this.participants[--this.length] = null;
  }

  private int indexOf(int participantNo) {
    for (int i = 0; i < this.length; i++) {
      Participant p = this.participants[i];
      if (p.getNo() == participantNo) {
        return i;
      }
    }
    return -1;
  }

  private boolean available() {
    return this.length < MAX_SIZE;
  }
}


