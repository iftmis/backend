package org.tamisemi.iftmis.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.FundingManagement} entity.
 */
public class FundingManagementDTO extends AbstractAuditingDTO implements Serializable {
    
    private Long id;

    private String title;

    private String description;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        if (!(o instanceof FundingManagementDTO)) {
            return false;
        }

        return id != null && id.equals(((FundingManagementDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FundingManagementDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
