package nc7.javaproject.PreferenceSurveyApp.Handler;
import nc7.javaproject.util.List;
import nc7.javaproject.util.MenuPrompt;
import nc7.javaproject.vo.Participant;


public class ParticipantHandler implements Handler {

  private List list;
  private MenuPrompt prompt;
  private String title;
  
  
  public ParticipantHandler(MenuPrompt prompt, String title, List list){
    this.prompt = prompt;
    this.title = title;
    this.list = list;
  }

  public void execute(){
    prompt.appendBreadcrumb(this.title, getMenu());
    prompt.printMenu();

    while (true) {
      String menuNo = prompt.inputMenu();
      switch (menuNo) {
        case "0": prompt.removeBreadcrumb(); return;
        case "1": this.inputParticipant(); break;
        case "2": this.printParticipants(); break;
        case "3": this.viewParticipant(); break;
        case "4": this.updateParticipant(); break;
        case "5": this.deleteParticipant(); break;
      }
    }
  }

  private static String getMenu() {
    StringBuilder menu = new StringBuilder();
    menu.append("1. 등록\n");
    menu.append("2. 목록\n");
    menu.append("3. 조회\n");
    menu.append("4. 변경\n");
    menu.append("5. 삭제\n");
    menu.append("0. 메인\n");
    return menu.toString();
  }

  private void inputParticipant() {
    Participant p = new Participant();

    p.setName(this.prompt.inputString("참여자 이름? "));
    p.setAge(Integer.parseInt(this.prompt.inputString("참여자 나이? ")));
    p.setMovieAttendance(this.prompt.inputString("영화 A 재관람 의사(Y/N)? "));
    p.setGender(inputGender((char) 0));
    p.setMovieRating(Integer.parseInt(this.prompt.inputString("영화 A에 대한 평점(1-5)? ")));
    p.setAdditionalInfo(this.prompt.inputString("추가 정보? "));
    

    this.list.add(p);
    }


  private void printParticipants() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름 나이, 관람여부, 성별, 평점, 추가정보");
    System.out.println("---------------------------------------");

    for (int i = 0; i < this.list.size(); i++) {
      Participant p = (Participant) this.list.get(i);
      System.out.printf("%d, %s, %d, %s, %s, %d, %s\n",
      p.getNo(), p.getName(), p.getAge(), p.getMovieAttendance(), p.getGender(),p.getMovieRating(), p.getAdditionalInfo());
      System.out.println("---------------------------------------");
    }
  }

  private void viewParticipant() {
    int participantNo = this.prompt.inputInt("조회할 참여자 번호? ");
    Participant p = this.findBy(participantNo);
      if (p == null) {
        System.out.println("해당 번호의 참여자를 찾을 수 없습니다.");
        return;
      }
        System.out.printf("참여자 이름: %s\n", p.getName());
        System.out.printf("참여자 나이: %d\n", p.getAge());
        System.out.printf("영화  재관람 의사: %s\n", p.getMovieAttendance());
        System.out.printf("성별: %s\n", toGenderString(p.getGender()));
        System.out.printf("영화 A에 대한 평가(1-5): %d\n", p.getMovieRating());
        System.out.printf("추가 정보: %s\n", p.getAdditionalInfo());
    }

  private static String toGenderString(char gender) {
    return gender == 'M' ? "남성" : "여성";
  }

  private  void updateParticipant() {
    int participantNo = this.prompt.inputInt("변경할 참여자 번호? ");
      Participant p = this.findBy(participantNo);
      if (p == null){
        System.out.println("해당 번호의 참여자를 찾을 수 없습니다.");
        return;
      } else {
        System.out.println("참여자 정보를 변경하였습니다.");
      }
        p.setName(this.prompt.inputString("변경할 참여자 이름? "));
        p.setAge(Integer.parseInt(this.prompt.inputString("변경할 참여자 나이? ")));
        p.setMovieAttendance(this.prompt.inputString("변경할 영화 재관람 의사(Y/N)? "));
        p.setGender(inputGender(p.getGender()));
        p.setMovieRating(Integer.parseInt(this.prompt.inputString("변경할 영화 A에 대한 평가(1-5)? ")));
        p.setAdditionalInfo(this.prompt.inputString("변경할 추가 정보? "));
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
    if (!this.list.remove(new Participant(this.prompt.inputInt("번호? ")))) {
      System.out.println("해당 번호의 회원이 없습니다!");
    }
  }
  private Participant findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Participant m = (Participant) this.list.get(i);
      if (m.getNo() == no) {
        return m;
      }
    }
    return null;
  }
}


