package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(description = "The Authority entity. User Management\n@author Kachinga")
public class AuthorityDTO extends AbstractAuditingDTO implements Serializable {
    @NotNull
    @Size(max = 50)
    @Column(length = 50)
    private String name;

    public AuthorityDTO() {}

    public AuthorityDTO(@NotNull @Size(max = 50) String name) {
        this.name = name;
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
        if (!(o instanceof AuthorityDTO)) return false;
        AuthorityDTO that = (AuthorityDTO) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "AuthorityDTO{" + "name='" + name + '\'' + '}';
    }
}
