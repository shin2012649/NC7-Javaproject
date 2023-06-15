package nc7.javaproject.PreferenceSurveyApp;

import nc7.javaproject.util.Prompt;
import nc7.javaproject.vo.Participant;

public class ParticipantHandler {

  private static final int MAX_SIZE = 100;
  private Prompt prompt;
  private Participant[] participants = new Participant[MAX_SIZE];
  private int length;
  private int[] ratingCounts = new int[5];
  private int totalScore = 0;
  private int participantCount = 0;
  private String title;

  public ParticipantHandler(Prompt prompt, String title){
    this.prompt = prompt;
    this.titile = title;
  }

  public void execute(){
    printMenu();

    while (true) {
      String menuNo = prompt.inputString("%s> ", this.title);
      if (menuNo.equals("0")) {
          break;
      } else if (menuNo.equals("menu")) {
          printMenu();
      } else if (menuNo.equals("1")) {
          this.inputParticipant();
      } else if (menuNo.equals("2")) {
        this.printParticipants();
      } else if (menuNo.equals("3")) {
        this.viewParticipant();
      } else if (menuNo.equals("4")) {
        this.updateParticipant();
      } else if (menuNo.equals("5")) {
        this.deleteParticipant();
      } else if (menuNo.equals("6")) {
        this.printRatingCounts();
      } else if (menuNo.equals("7")) {
        this.printAverageScore();
      } else {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }
  }

  private void printMenu() {
    System.out.println("1. 등록");
    System.out.println("2. 목록");
    System.out.println("3. 조회");
    System.out.println("4. 변경");
    System.out.println("5. 삭제");
    System.out.println("6. 통계");
    System.out.println("7. 평점 통계");
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

  private void printRatingCounts() {
    System.out.println("등급별 선택 횟수:");
    for (int i = 0; i < ratingCounts.length; i++) {
      System.out.printf("%d점: %d명\n", i + 1, ratingCounts[i]);
    }
  }

  private void printAverageScore() {
    if (participantCount != 0) {
      double averageScore = (double) totalScore / participantCount;
      System.out.printf("총 점수 평균: %.2f\n", averageScore);
    } else {
      System.out.println("참여자가 없습니다.");
    }
  }

  private void printParticipants() {
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

  private void viewParticipant() {
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

  private static String toGenderString(char gender) {
    return gender == 'M' ? "남성" : "여성";
  }

  private  void updateParticipant() {
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

  private void deleteParticipant() {
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


