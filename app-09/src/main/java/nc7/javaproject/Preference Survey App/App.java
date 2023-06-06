package nc7.javaproject.Preference Survey App;

import nc7.javaproject.Preference Survey App.ParticipantHandler;
import nc7.javaproject.util.Prompt;

public class App {

  public static void main(String[] args) {

    printTitle();

    while (ParticipantHandler.available()) {
      ParticipantHandler.inputParticipant();
      if (!promptContinue()) {
        break;
      }
    }

    ParticipantHandler.printParticipants();

    Prompt.close();
  }

  static void printTitle() {
    System.out.println("Movie A Preference Survey");
    System.out.println("----------------------------------");
  }

  static boolean promptContinue() {
    String response = Prompt.inputString("Continue? (Y/n) ");
    if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
      return false;
    }
    return true;
  }
}







