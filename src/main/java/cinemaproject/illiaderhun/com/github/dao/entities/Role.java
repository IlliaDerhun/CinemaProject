package cinemaproject.illiaderhun.com.github.dao.entities;

import java.util.Objects;

public class Role {

    private Integer id;
    private String access;

    public Role(Integer id, String access) {
        this.id = id;
        this.access = access;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(access, role.access);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, access);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", access='" + access + '\'' +
                '}';
    }
}
