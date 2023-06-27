package nc7.javaproject.PreferenceSurveyApp.Handler;
import nc7.javaproject.util.BreadcrumbPrompt;
import nc7.javaproject.util.List;
import nc7.javaproject.vo.Participant;


public class ParticipantDeleteListener extends AbstractParticipantListener {

  public ParticipantDeleteListener(List<Participant> list){
    super(list);
  }

  
  @Override
  public void service(BreadcrumbPrompt prompt) {
    if (!this.list.remove(new Participant(prompt.inputInt("번호? ")))) {
      System.out.println("해당 번호의 회원이 없습니다!");
    }
  }
    
  }


