package nc7.javaproject.Handler;

import nc7.util.ActionListener;
import nc7.util.BreadcrumbPrompt;

public class FootListener implements ActionListener {
  @Override
  public void service(BreadcrumbPrompt prompt) {
    prompt.println("Copyright \u00a9 by 네클7기 신현우----------------------------");
  }
}
