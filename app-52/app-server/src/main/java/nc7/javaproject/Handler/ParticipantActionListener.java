package nc7.javaproject.handler;

import java.io.IOException;
import nc7.javaproject.vo.Participant;
import nc7.util.ActionListener;
import nc7.util.BreadcrumbPrompt;

public interface ParticipantActionListener extends ActionListener {

  static char inputGender(char gender, BreadcrumbPrompt prompt) throws IOException{
    String label;
    if (gender == 0) {
      label = "성별?\n";
    } else {
      label = String.format("성별(%s)?\n", gender == 'M' ? "남성" : "여성");
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
          prompt.println("무효한 번호입니다.");
      }
    }
  }

}
