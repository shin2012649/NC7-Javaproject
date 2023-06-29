package nc7.javaproject.PreferenceSurveyApp.Handler;

import nc7.javaproject.util.ActionListener;
import nc7.javaproject.util.BreadcrumbPrompt;

public class HelloListener implements ActionListener {
  
  @Override
  public void service(BreadcrumbPrompt prompt) {
    String name = prompt.inputString("이름은?");
    System.out.printf("%s 님 반가워욥!\n", name);
    
  }

}
