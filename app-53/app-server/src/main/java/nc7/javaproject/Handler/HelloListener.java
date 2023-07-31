package nc7.javaproject.Handler;

import nc7.util.ActionListener;
import nc7.util.BreadcrumbPrompt;

public class HelloListener implements ActionListener {

  @Override
  public void service(BreadcrumbPrompt prompt) {
    try {
      String name = prompt.inputString("이름은? ");
      prompt.printf("%s 님 반가워요!\n", name);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
