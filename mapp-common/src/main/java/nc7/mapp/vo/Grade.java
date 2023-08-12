package nc7.mapp.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Grade implements Serializable {
    private static final long serialVersionUID = 1L;

    private int gradeNo;
    private String name;
    private Date addAt;

    public Grade() {
    }

    public Grade(int gradeNo, String name, Date addAt) {
        this.gradeNo = gradeNo;
        this.name = name;
        this.addAt = addAt;
    }

    public int getGradeNo() {
        return gradeNo;
    }

    public void setGradeNo(int gradeNo) {
        this.gradeNo = gradeNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAddAt() {
        return addAt;
    }

    public void setAddAt(Date addAt) {
        this.addAt = addAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return gradeNo == grade.gradeNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gradeNo);
    }

    @Override
    public String toString() {
        return "Grade{" +
                "gradeNo=" + gradeNo +
                ", name='" + name + '\'' +
                ", addAt=" + addAt +
                '}';
    }
}
