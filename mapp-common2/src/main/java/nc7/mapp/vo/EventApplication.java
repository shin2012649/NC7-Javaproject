package nc7.mapp.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class EventApplication implements Serializable {
    private static final long serialVersionUID = 1L;

    private int usersNo;
    private int eventNo;
    private Date createdDate;
    private String state;

    public EventApplication() {
    }

    public EventApplication(int usersNo, int eventNo, Date createdDate, String state) {
        this.usersNo = usersNo;
        this.eventNo = eventNo;
        this.createdDate = createdDate;
        this.state = state;
    }

    public int getUsersNo() {
        return usersNo;
    }

    public void setUsersNo(int usersNo) {
        this.usersNo = usersNo;
    }

    public int getEventNo() {
        return eventNo;
    }

    public void setEventNo(int eventNo) {
        this.eventNo = eventNo;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventApplication that = (EventApplication) o;
        return usersNo == that.usersNo &&
                eventNo == that.eventNo &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersNo, eventNo, createdDate, state);
    }

    @Override
    public String toString() {
        return "EventApplication{" +
                "usersNo=" + usersNo +
                ", eventNo=" + eventNo +
                ", createdDate=" + createdDate +
                ", state='" + state + '\'' +
                '}';
    }
}
