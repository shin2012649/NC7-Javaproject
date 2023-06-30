package nc7.javaproject.vo;

import java.io.Serializable;

public class Participant implements Serializable {

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


