package nc7.mapp.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int usersNo;
    private String name;
    private String password;
    private String email;
    private String profileImageUrl;
    private Date dateOfBirth;
    private Date createdAt;
    private boolean isManager;

    public User() {
    }

    public User(int usersNo, String name, String password, String email, String profileImageUrl,
                Date dateOfBirth, Date createdAt) {
        this.usersNo = usersNo;
        this.name = name;
        this.password = password;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = createdAt;
    }

    public int getUsersNo() {
        return usersNo;
    }

    public void setUsersNo(int usersNo) {
        this.usersNo = usersNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return usersNo == user.usersNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersNo);
    }

    @Override
    public String toString() {
        return "User{" +
                "usersNo=" + usersNo +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", createdAt=" + createdAt +
                '}';
    }

    public void setIsManager(boolean isManager) {
      this.isManager = isManager;
  }

    public boolean isManager() {
      return isManager;
  }

    
}
