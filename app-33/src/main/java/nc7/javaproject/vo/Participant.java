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
  
//  public static Participant fromCsv(String csv) {
//    String[] values = csv.split(",");
//    
//    // 유효성 검사: values[0]이 공백 문자를 포함하는지 확인하고 있다면 trim()을 사용하여 앞뒤 공백 제거
////    String value0 = values[0].trim();
////
////    // 유효성 검사: value0이 유효한 숫자인지 확인
////    if (value0.isEmpty() || !value0.matches("\\d+")) {
////      throw new IllegalArgumentException("Invalid value for participant number: " + value0);
////    }
//    
//    
//    Participant participant = new Participant(Integer.parseInt(values[0]));
////    Participant participant = new Participant(Integer.parseInt(values[0].trim()));
//    participant.setName(values[1]);
//    participant.setAge(Integer.parseInt(values[2]));
//    participant.setMovieAttendance(values[3]);
//    participant.setGender(values[4].charAt(0));
//    participant.setMovieRating(Integer.parseInt(values[5]));
//    participant.setAdditionalInfo(values[6]);
//    
//    if(Participant.userId <= participant.getNo()) {
//      Participant.userId = participant.getNo() + 1;
//    }
//    
//    return participant;
//  }
  public static Participant fromCsv(String csv) {
    String[] values = csv.split(",");

    // 유효성 검사: values[0]이 공백 문자를 포함하는지 확인하고 있다면 trim()을 사용하여 앞뒤 공백 제거
    String value0 = values[0].trim();

    // 유효성 검사: value0이 유효한 숫자인지 확인
    if (value0.isEmpty() || !value0.matches("\\d+")) {
      throw new IllegalArgumentException("Invalid value for participant number: " + value0);
    }

    int number = Integer.parseInt(value0);
    
    Participant participant = new Participant(number);
    participant.setName(values[1]);
    
    // age 필드에 대한 유효성 검사 추가
    String value2 = values[2].trim();
    if (value2.isEmpty() || !value2.matches("\\d+")) {
      throw new IllegalArgumentException("Invalid value for participant age: " + value2);
    }
    int age = Integer.parseInt(value2);
    participant.setAge(age);

    participant.setMovieAttendance(values[3]);
    participant.setGender(values[4].charAt(0));

    // movieRating 필드에 대한 유효성 검사 추가
    String value5 = values[5].trim();
    if (value5.isEmpty() || !value5.matches("\\d+")) {
      throw new IllegalArgumentException("Invalid value for movie rating: " + value5);
    }
    int movieRating = Integer.parseInt(value5);
    participant.setMovieRating(movieRating);
    
    if (values.length >= 7) {
      participant.setAdditionalInfo(values[6]);
    }


//    participant.setAdditionalInfo(values[6]);

    if (Participant.userId <= participant.getNo()) {
      Participant.userId = participant.getNo() + 1;
    }

    return participant;
  }
  
  
  @Override
  public String toCsvString() {
    return String.format("%d,%s,%d,%s,%c,%d,%s",
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


