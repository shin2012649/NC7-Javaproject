package nc7.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import nc7.javaproject.dao.ParticipantDao;
import nc7.javaproject.vo.Participant;

public class MySQLParticipantDao implements ParticipantDao {
  
  Connection con;
  
  public MySQLParticipantDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Participant participant) {
    try(Statement stmt = con.createStatement()) {
      
      stmt.executeUpdate(String.format(
          "insert into JavaProject_participant(name, age, movieAttendance, gender, movieRating, additionalInfo) values('%s','%d','%s','%c','%d','%s')",
           participant.getName(),
           participant.getAge(),
           participant.getMovieAttendance(),
           participant.getGender(),
           participant.getMovieRating(),
           participant.getAdditionalInfo()));
          
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Participant> list() {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
           "select participant_no, name, age, movieAttendance, gender, movieRating, additionalInfo"
           + " from JavaProject_participant order by name asc")){
      
      List<Participant> list = new ArrayList<>();
      
      while (rs.next()) {
        Participant p = new Participant();
        p.setNo(rs.getInt("participant_no"));
        p.setName(rs.getString("name"));
        p.setAge(rs.getInt("age"));
        p.setMovieAttendance(rs.getString("movieAttendance"));
        p.setGender(rs.getString("gender").charAt(0));
        p.setMovieRating(rs.getInt("movieRating"));
        p.setAdditionalInfo(rs.getString("additionalInfo"));
        
        list.add(p);
      }
      
      return list;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
   }

  @Override
  public Participant findBy(int no) {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select participant_no, name, age, movieAttendance, gender, movieRating, additionalInfo"
            + " from JavaProject_participant where participant_no=" + no)){
      
      if(rs.next()) {
        Participant p = new Participant();
        p.setNo(rs.getInt("participant_no"));
        p.setName(rs.getString("name"));
        p.setAge(rs.getInt("age"));
        p.setMovieAttendance(rs.getString("movieAttendance"));
        p.setGender(rs.getString("gender").charAt(0));
        p.setMovieRating(rs.getInt("movieRating"));
        p.setAdditionalInfo(rs.getString("additionalInfo"));
        return p;
      }
      return null;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Participant participant) {
    try (Statement stmt = con.createStatement()) {
      
      return stmt.executeUpdate(String.format(
      "update JavaProject_participant set"
          + "name = '%s', "
          + "age = '%d', "
          + "movieAttendance = '%s', "
          + "gender = '%c', "
          + "movieRating = '%d', "
          + "additionalInfo = '%s' "
          + "where participant_no =%d",
          participant.getName(),
          participant.getAge(),
          participant.getMovieAttendance(),
          participant.getGender(),
          participant.getMovieRating(),
          participant.getAdditionalInfo(),
          participant.getNo()));
      
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  public int delete(int no) {
    try (Statement stmt = con.createStatement()) {
      return stmt.executeUpdate(String.format(
          "delete from JavaProject_participant where participant_no=%d",
          no));
      
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}