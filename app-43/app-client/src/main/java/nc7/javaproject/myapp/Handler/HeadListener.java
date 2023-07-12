package nc7.javaproject.myapp.Handler;

import nc7.javaproject.util.ActionListener;
import nc7.javaproject.util.BreadcrumbPrompt;

public class HeadListener implements ActionListener{

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("=============================[오호]==");
  }
}
