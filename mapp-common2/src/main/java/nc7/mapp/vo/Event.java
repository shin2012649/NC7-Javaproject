package nc7.mapp.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Event implements Serializable {
    private static final long serialVersionUID = 1L;

    private int eventNo;
    private int filmsNo;
    private String screeningLocation;
    private LocalDateTime entryPeriodStart;
    private LocalDateTime entryPeriodEnd;
    private LocalDateTime announcementDate;
    private int winnersCount;
    private String notice;
    private LocalDateTime screeningDate;

    public Event() {
    }

    public Event(int eventNo, int filmsNo, LocalDateTime screeningDate, String screeningLocation,
        LocalDateTime entryPeriodStart, LocalDateTime entryPeriodEnd, LocalDateTime announcementDate,
        int winnersCount, String notice) {
        this.eventNo = eventNo;
        this.filmsNo = filmsNo;
        this.screeningDate = screeningDate;
        this.screeningLocation = screeningLocation;
        this.entryPeriodStart = entryPeriodStart;
        this.entryPeriodEnd = entryPeriodEnd;
        this.announcementDate = announcementDate;
        this.winnersCount = winnersCount;
        this.notice = notice;
    }

    public int getEventNo() {
        return eventNo;
    }

    public void setEventNo(int eventNo) {
        this.eventNo = eventNo;
    }

    public int getFilmsNo() {
        return filmsNo;
    }

    public void setFilmsNo(int filmsNo) {
        this.filmsNo = filmsNo;
    }


    public String getScreeningLocation() {
        return screeningLocation;
    }

    public void setScreeningLocation(String screeningLocation) {
        this.screeningLocation = screeningLocation;
    }



    public int getWinnersCount() {
        return winnersCount;
    }

    public void setWinnersCount(int winnersCount) {
        this.winnersCount = winnersCount;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
    
    

    public LocalDateTime getEntryPeriodStart() {
      return entryPeriodStart;
    }

    public void setEntryPeriodStart(LocalDateTime entryPeriodStart) {
      this.entryPeriodStart = entryPeriodStart;
    }

    public LocalDateTime getEntryPeriodEnd() {
      return entryPeriodEnd;
    }

    public void setEntryPeriodEnd(LocalDateTime entryPeriodEnd) {
      this.entryPeriodEnd = entryPeriodEnd;
    }

    public LocalDateTime getAnnouncementDate() {
      return announcementDate;
    }

    public void setAnnouncementDate(LocalDateTime announcementDate) {
      this.announcementDate = announcementDate;
    }

    public void setScreeningDate(LocalDateTime screeningDate) {
      this.screeningDate = screeningDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return eventNo == event.eventNo &&
                filmsNo == event.filmsNo &&
                winnersCount == event.winnersCount &&
                Objects.equals(screeningDate, event.screeningDate) &&
                Objects.equals(screeningLocation, event.screeningLocation) &&
                Objects.equals(entryPeriodStart, event.entryPeriodStart) &&
                Objects.equals(entryPeriodEnd, event.entryPeriodEnd) &&
                Objects.equals(announcementDate, event.announcementDate) &&
                Objects.equals(notice, event.notice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventNo, filmsNo, screeningDate, screeningLocation,
                entryPeriodStart, entryPeriodEnd, announcementDate, winnersCount, notice);
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventNo=" + eventNo +
                ", filmsNo=" + filmsNo +
                ", screeningDate=" + screeningDate +
                ", screeningLocation='" + screeningLocation + '\'' +
                ", entryPeriodStart=" + entryPeriodStart +
                ", entryPeriodEnd=" + entryPeriodEnd +
                ", announcementDate=" + announcementDate +
                ", winnersCount=" + winnersCount +
                ", notice='" + notice + '\'' +
                '}';
    }

    public LocalDateTime getScreeningDate() {
      // TODO Auto-generated method stub
      return screeningDate;
    }
}
