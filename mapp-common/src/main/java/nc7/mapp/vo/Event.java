package nc7.mapp.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Event implements Serializable {
    private static final long serialVersionUID = 1L;

    private int eventNo;
    private int filmsNo;
    private Date screeningDate;
    private String screeningLocation;
    private Date entryPeriodStart;
    private Date entryPeriodEnd;
    private Date announcementDate;
    private int winnersCount;
    private String notice;

    public Event() {
    }

    public Event(int eventNo, int filmsNo, Date screeningDate, String screeningLocation,
                 Date entryPeriodStart, Date entryPeriodEnd, Date announcementDate,
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

    public Date getScreeningDate() {
        return screeningDate;
    }

    public void setScreeningDate(Date screeningDate) {
        this.screeningDate = screeningDate;
    }

    public String getScreeningLocation() {
        return screeningLocation;
    }

    public void setScreeningLocation(String screeningLocation) {
        this.screeningLocation = screeningLocation;
    }

    public Date getEntryPeriodStart() {
        return entryPeriodStart;
    }

    public void setEntryPeriodStart(Date entryPeriodStart) {
        this.entryPeriodStart = entryPeriodStart;
    }

    public Date getEntryPeriodEnd() {
        return entryPeriodEnd;
    }

    public void setEntryPeriodEnd(Date entryPeriodEnd) {
        this.entryPeriodEnd = entryPeriodEnd;
    }

    public Date getAnnouncementDate() {
        return announcementDate;
    }

    public void setAnnouncementDate(Date announcementDate) {
        this.announcementDate = announcementDate;
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
}
