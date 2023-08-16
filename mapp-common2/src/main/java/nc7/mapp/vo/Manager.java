package nc7.mapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class Manager implements Serializable {
    private static final long serialVersionUID = 1L;

    private int usersNo;
    private String department;
    private String jobTitle;

    public Manager() {
    }

    public Manager(int usersNo, String department, String jobTitle) {
        this.usersNo = usersNo;
        this.department = department;
        this.jobTitle = jobTitle;
    }

    public int getUsersNo() {
        return usersNo;
    }

    public void setUsersNo(int usersNo) {
        this.usersNo = usersNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manager manager = (Manager) o;
        return usersNo == manager.usersNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersNo);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "usersNo=" + usersNo +
                ", department='" + department + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}
