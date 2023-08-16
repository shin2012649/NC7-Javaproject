package nc7.mapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class FilmActor implements Serializable {
    private static final long serialVersionUID = 1L;

    private int filmsActorsNo;
    private int filmsNo;
    private int rolesNo;
    private int actorsNo;
    private String name;

    public FilmActor() {
    }

    public FilmActor(int filmsActorsNo, int filmsNo, int rolesNo, int actorsNo, String name) {
        this.filmsActorsNo = filmsActorsNo;
        this.filmsNo = filmsNo;
        this.rolesNo = rolesNo;
        this.actorsNo = actorsNo;
        this.name = name;
    }

    public int getFilmsActorsNo() {
        return filmsActorsNo;
    }

    public void setFilmsActorsNo(int filmsActorsNo) {
        this.filmsActorsNo = filmsActorsNo;
    }

    public int getFilmsNo() {
        return filmsNo;
    }

    public void setFilmsNo(int filmsNo) {
        this.filmsNo = filmsNo;
    }

    public int getRolesNo() {
        return rolesNo;
    }

    public void setRolesNo(int rolesNo) {
        this.rolesNo = rolesNo;
    }

    public int getActorsNo() {
        return actorsNo;
    }

    public void setActorsNo(int actorsNo) {
        this.actorsNo = actorsNo;
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
        FilmActor filmActor = (FilmActor) o;
        return filmsActorsNo == filmActor.filmsActorsNo &&
                filmsNo == filmActor.filmsNo &&
                rolesNo == filmActor.rolesNo &&
                actorsNo == filmActor.actorsNo &&
                Objects.equals(name, filmActor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmsActorsNo, filmsNo, rolesNo, actorsNo, name);
    }

    @Override
    public String toString() {
        return "FilmActor{" +
                "filmsActorsNo=" + filmsActorsNo +
                ", filmsNo=" + filmsNo +
                ", rolesNo=" + rolesNo +
                ", actorsNo=" + actorsNo +
                ", name='" + name + '\'' +
                '}';
    }
}
