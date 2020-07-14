package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.Objective} entity.
 */
@ApiModel(description = "The Objective(objectives) entity.\n@author Chris")
public class ObjectiveDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @NotNull
    @Size(min = 1, max = 64)
    private String code;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ObjectiveDTO)) {
            return false;
        }

        return id != null && id.equals(((ObjectiveDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ObjectiveDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
