package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.FileResource} entity.
 */
@ApiModel(description = "The FileResource entity.\n@author Chris")
public class FileResourceDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @NotNull
    @Size(min = 2, max = 200)
    private String name;

    @Size(max = 300)
    private String path;

    @Size(max = 200)
    private String contentType;

    @NotNull
    @Size(max = 32)
    private String contextMd5;

    @NotNull
    private Double size;

    private Boolean isAssigned;

    @NotNull
    @Size(max = 100)
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContextMd5() {
        return contextMd5;
    }

    public void setContextMd5(String contextMd5) {
        this.contextMd5 = contextMd5;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Boolean isIsAssigned() {
        return isAssigned;
    }

    public void setIsAssigned(Boolean isAssigned) {
        this.isAssigned = isAssigned;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileResourceDTO)) {
            return false;
        }

        return id != null && id.equals(((FileResourceDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FileResourceDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", path='" + getPath() + "'" +
            ", contentType='" + getContentType() + "'" +
            ", contextMd5='" + getContextMd5() + "'" +
            ", size=" + getSize() +
            ", isAssigned='" + isIsAssigned() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
