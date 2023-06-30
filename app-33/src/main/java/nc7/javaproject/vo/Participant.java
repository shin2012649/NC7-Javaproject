package nc7.javaproject.vo;

import java.io.Serializable;

public class Participant implements Serializable, CsvObject {

  private static final long serialVersionUID = 1L;
  
  public static int userId = 1;
  
  public static final char MALE = 'M';
  public static final char FEMALE = 'W';

  private int no;
  private String name;
  private int age;
  private String movieAttendance;
  private char gender;
  private int movieRating;
  private String additionalInfo;

  public Participant(){
    this.no = userId++;
  }
  
  public Participant(int no) {
    this.no = no;
  }
  
  public static Participant fromCsv(String csv) {
    String[] values = csv.split(",");
    
    Participant participant = new Participant(Integer.parseInt(values[0]));
    participant.setName(values[1]);
    participant.setAge(Integer.parseInt(values[2]));
    participant.setMovieAttendance(values[3]);
    participant.setGender(values[4].charAt(0));
    participant.setMovieRating(Integer.parseInt(values[5]));
    participant.setAdditionalInfo(values[6]);
    
    if(Participant.userId <= participant.getNo()) {
      Participant.userId = participant.getNo() + 1;
    }
    
    return participant;
  }
    
//  }
  
//  public static Participant fromCsv(String csv) {
//    String[] values = csv.split(",");
//    
//    if (values.length >= 7) {
//        Participant participant = new Participant(Integer.parseInt(values[0]));
//        participant.setName(values[1]);
//        participant.setAge(Integer.parseInt(values[2]));
//        participant.setMovieAttendance(values[3]);
//        participant.setGender(values[4].charAt(0));
//
//        if (!values[5].isEmpty()) {
//            try {
//                participant.setMovieRating(Integer.parseInt(values[5]));
//            } catch (NumberFormatException e) {
//                // 처리할 예외 발생 시 작업을 수행하세요.
//                // 예를 들어, 기본값을 설정하거나 오류 메시지를 출력할 수 있습니다.
//                participant.setMovieRating(0); // 기본값 설정 예시
//                System.out.println("잘못된 영화 평점 값입니다."); // 오류 메시지 출력 예시
//            }
//        }
//
//        participant.setAdditionalInfo(values[6]);
//
//        if (Participant.userId <= participant.getNo()) {
//          Participant.userId = participant.getNo() + 1;
//      }
//
//      return participant;
//  } else {
//      // values 배열이 충분한 요소를 갖지 않는 경우 처리
//      throw new IllegalArgumentException("잘못된 CSV 형식입니다.");
//  }
//}

  
  @Override
  public String toCsvString() {
    return String.format("%d,%s,%d,%s,%c,%d,%s\n",
        this.getNo(),
        this.getName(),
        this.getAge(),
        this.getMovieAttendance(),
        this.getGender(),
        this.getMovieRating(),
        this.getAdditionalInfo());

  }
  
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
  }

    if (this.getClass() != obj.getClass()) {
      return false;
  }
    
  Participant p = (Participant)obj;
  
  if (this.getNo() != p.getNo()) {
    return false;
  }
  return true;
  }
  
  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getMovieAttendance() {
    return movieAttendance;
  }

  public void setMovieAttendance(String movieAttendance) {
    this.movieAttendance = movieAttendance;
  }

  public char getGender() {
    return gender;
  }

  public void setGender(char gender) {
    this.gender = gender;
  }

  public int getMovieRating() {
    return movieRating;
  }

  public void setMovieRating(int movieRating) {
    this.movieRating = movieRating;
  }

  public String getAdditionalInfo() {
    return additionalInfo;
  }

  public void setAdditionalInfo(String additionalInfo) {
    this.additionalInfo = additionalInfo;
  }
}


