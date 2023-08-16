package nc7.mapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class Src implements Serializable {
    private static final long serialVersionUID = 1L;

    private int srcNo;
    private String name;

    public Src() {
    }

    public Src(int srcNo, String name) {
        this.srcNo = srcNo;
        this.name = name;
    }

    public int getSrcNo() {
        return srcNo;
    }

    public void setSrcNo(int srcNo) {
        this.srcNo = srcNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Src src = (Src) o;
        return srcNo == src.srcNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(srcNo);
    }

    @Override
    public String toString() {
        return "Src{" +
                "srcNo=" + srcNo +
                ", name='" + name + '\'' +
                '}';
    }
}
