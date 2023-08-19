package nc7.javaproject.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Event implements Serializable {

  private static final long serialVersionUID = 1L;

  private int eventId;
  private String name;
  private String entryPeriod;
  private Timestamp announcementDate;
  private int winnersCount;
  private Timestamp screeningDate;
  private String screeningLocation;
  private String notice;

  @Override
  public int hashCode() {
    return Objects.hash(eventId);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Event other = (Event) obj;
    return eventId == other.eventId;
  }

  public int getEventId() {
    return eventId;
  }

  public void setEventId(int eventId) {
    this.eventId = eventId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEntryPeriod() {
    return entryPeriod;
  }

  public void setEntryPeriod(String entryPeriod) {
    this.entryPeriod = entryPeriod;
  }

  public Timestamp getAnnouncementDate() {
    return announcementDate;
  }

  public void setAnnouncementDate(Timestamp announcementDate) {
    this.announcementDate = announcementDate;
  }

  public int getWinnersCount() {
    return winnersCount;
  }

  public void setWinnersCount(int winnersCount) {
    this.winnersCount = winnersCount;
  }

  public Timestamp getScreeningDate() {
    return screeningDate;
  }

  public void setScreeningDate(Timestamp screeningDate) {
    this.screeningDate = screeningDate;
  }

  public String getScreeningLocation() {
    return screeningLocation;
  }

  public void setScreeningLocation(String screeningLocation) {
    this.screeningLocation = screeningLocation;
  }

  public String getNotice() {
    return notice;
  }

  public void setNotice(String notice) {
    this.notice = notice;
  }
  public void setAnnouncementDateFromString(String announcementDateStr) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime parsedDate = LocalDateTime.parse(announcementDateStr, formatter);
    this.announcementDate = Timestamp.valueOf(parsedDate);
  }

  public void setScreeningDateFromString(String screeningDateStr) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime parsedDate = LocalDateTime.parse(screeningDateStr, formatter);
    this.screeningDate = Timestamp.valueOf(parsedDate);
  }
}
