package nc7.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    try(PreparedStatement stmt = con.prepareStatement(
        "insert into JavaProject_participant(name, age, movieAttendance, gender, movieRating, additionalInfo, password)"
        + " values(?,?,?,?,?,?,sha1(?))")) {
          
          stmt.setString(1, participant.getName());
          stmt.setInt(2, participant.getAge());
          stmt.setString(3, participant.getMovieAttendance());
          stmt.setString(4, String.valueOf(participant.getGender()));
          stmt.setInt(5, participant.getMovieRating());
          stmt.setString(6, participant.getAdditionalInfo());
          stmt.setString(7, participant.getPassword());
        
      
      stmt.executeUpdate();

          
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Participant> list() {
    try (PreparedStatement stmt = con.prepareStatement(
        "select participant_no, name, age, movieAttendance, gender, movieRating, additionalInfo"
        + " from JavaProject_participant"
        + " order by name asc");
        
        ResultSet rs = stmt.executeQuery()) {
      
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
    try (PreparedStatement stmt = con.prepareStatement(
        "select participant_no, name, age, movieAttendance, gender, movieRating, additionalInfo, created_date"
        + " from JavaProject_participant where participant_no=?")){
      
      
        stmt.setInt(1, no);
        
        try (ResultSet rs = stmt.executeQuery()) {
          if(rs.next()) {
            Participant p = new Participant();
            p.setNo(rs.getInt("participant_no"));
            p.setName(rs.getString("name"));
            p.setAge(rs.getInt("age"));
            p.setMovieAttendance(rs.getString("movieAttendance"));
            p.setGender(rs.getString("gender").charAt(0));
            p.setMovieRating(rs.getInt("movieRating"));
            p.setAdditionalInfo(rs.getString("additionalInfo"));
            p.setCreatedDate(rs.getDate("created_date"));
            return p;
          }
          return null;
        }
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
      
      

  @Override
  public int update(Participant participant) {
    try (PreparedStatement stmt = con.prepareStatement(
        
        "update JavaProject_participant set"
        + "name = '?', "
        + "age = '?', "
        + "movieAttendance = '?', "
        + "gender = '?', "
        + "movieRating = '?', "
        + "additionalInfo = '?' "
        + "password = 'sha1(?)', "
        + "where participant_no =?")) {
    
      stmt.setString(1, participant.getName());
      stmt.setInt(2, participant.getAge());
      stmt.setString(3, participant.getMovieAttendance());
      stmt.setString(4, String.valueOf(participant.getGender()));
      stmt.setInt(5, participant.getMovieRating());
      stmt.setString(6, participant.getAdditionalInfo());
      stmt.setString(7, participant.getPassword());
      stmt.setInt(8, participant.getNo());
      
      
      return stmt.executeUpdate();
      
      
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  @Override
  public int delete(int no) {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from JavaProject_participant where participant_no=?")) {
      
      stmt.setInt(1, no);
      return stmt.executeUpdate();
      
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}