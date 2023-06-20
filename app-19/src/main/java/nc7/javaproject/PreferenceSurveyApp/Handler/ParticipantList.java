package nc7.javaproject.PreferenceSurveyApp.Handler;
import nc7.javaproject.vo.Participant;

public class ParticipantList {
  private static final int DEFAULT_SIZE = 3;
  private Participant[] participants = new Participant[DEFAULT_SIZE];
  private int length;
  
  

public boolean add(Participant p){
  if (this.length == participants.length) {
    increase();
  }
  this.participants[this.length++] = p;
  
  return true;
}

private void increase(){
  Participant[] arr = new Participant[participants.length +(participants.length >> 1)];
  for (int i = 0; i < participants.length; i++){
    arr[i] = participants[i];
  }
  participants = arr;
  System.out.println("배열확장:" + participants.length);
}


private int[] ratingCounts = new int[5];
private int totalScore = 0;
private int participantCount = 0;

public int[] getRatingCounts() {
  return ratingCounts;
}

public int getTotalScore() {
  return totalScore;
}

public void setTotalScore(int totalScore) {
  this.totalScore = totalScore;
}

public int getParticipantCount() {
  return participantCount;
}

public void setParticipantCount(int participantCount) {
  this.participantCount = participantCount;
}



public Participant[] list() {
  Participant[] arr = new Participant[this.length];
  for (int i = 0; i < this.length; i++) {
    arr[i] = this.participants[i];
  } 
  return arr;
}

public Participant get(int no){
  for (int i = 0; i < this.length; i++) {
    Participant p = this.participants[i];
    if(p.getNo() == no){
      return p;
    }
  }
  return null;
}

public boolean delete(int no){
  int deletedIndex = indexOf(no);
    if (deletedIndex == -1) {
      return false;
    }

    for (int i = deletedIndex; i < this.length - 1; i++) {
      this.participants[i] = this.participants[i + 1];
    }
    this.participants[--this.length] = null;
    return true;
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
  
}
