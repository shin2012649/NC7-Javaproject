package nc7.mapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class Staff implements Serializable {
    private static final long serialVersionUID = 1L;

    private int staffNo;
    private String name;
    private String imageUrl;

    public Staff() {
    }

    public Staff(int staffNo, String name, String imageUrl) {
        this.staffNo = staffNo;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(int staffNo) {
        this.staffNo = staffNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return staffNo == staff.staffNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(staffNo);
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffNo=" + staffNo +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
