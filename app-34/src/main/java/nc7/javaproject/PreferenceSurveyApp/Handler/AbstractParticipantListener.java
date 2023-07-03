package nc7.javaproject.PreferenceSurveyApp.Handler;

import java.util.List;
import nc7.javaproject.util.ActionListener;
import nc7.javaproject.util.BreadcrumbPrompt;
import nc7.javaproject.vo.Participant;

public abstract class AbstractParticipantListener implements ActionListener {

  protected List<Participant> list;

  public AbstractParticipantListener(List<Participant> list) {
    this.list = list;
  }

  protected static String toGenderString(char gender) {
    return gender == 'M' ? "남성" : "여성";
  }

  protected Participant findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Participant m = this.list.get(i);
      if (m.getNo() == no) {
        return m;
      }
    }
    return null;
  }

  protected char inputGender(char gender, BreadcrumbPrompt prompt) {
    String label;
    if (gender == 0) {
      label = "성별?\n";
    } else {
      label = String.format("성별(%s)?\n", toGenderString(gender));
    }

    while (true) {
      String menuNo = prompt.inputString(label +
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

}
