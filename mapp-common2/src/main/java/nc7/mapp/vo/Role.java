package nc7.mapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    private int rolesNo;
    private String name;

    public Role() {
    }

    public Role(int rolesNo, String name) {
        this.rolesNo = rolesNo;
        this.name = name;
    }

    public int getRolesNo() {
        return rolesNo;
    }

    public void setRolesNo(int rolesNo) {
        this.rolesNo = rolesNo;
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
        Role role = (Role) o;
        return rolesNo == role.rolesNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rolesNo);
    }

    @Override
    public String toString() {
        return "Role{" +
                "rolesNo=" + rolesNo +
                ", name='" + name + '\'' +
                '}';
    }
}
