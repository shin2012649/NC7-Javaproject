package nc7.mapp.vo;

import java.io.Serializable;
import java.util.Objects;

public class Actor implements Serializable {
    private static final long serialVersionUID = 1L;

    private int actorsNo;
    private String name;
    private String imageUrl;

    public Actor() {
    }

    public Actor(int actorsNo, String name, String imageUrl) {
        this.actorsNo = actorsNo;
        this.name = name;
        this.imageUrl = imageUrl;
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
        Actor actor = (Actor) o;
        return actorsNo == actor.actorsNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorsNo);
    }

    @Override
    public String toString() {
        return "Actor{" +
                "actorsNo=" + actorsNo +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
