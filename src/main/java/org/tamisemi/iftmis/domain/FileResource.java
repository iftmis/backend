package org.tamisemi.iftmis.domain;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The FileResource entity.\n@author Chris
 */
@ApiModel(description = "The FileResource entity.\n@author Chris")
@Entity
@Table(name = "file_resources")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FileResource extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 2, max = 200)
    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Size(max = 300)
    @Column(name = "path", length = 300)
    private String path;

    @Size(max = 200)
    @Column(name = "content_type", length = 200)
    private String contentType;

    @NotNull
    @Size(max = 32)
    @Column(name = "context_md_5", length = 32, nullable = false)
    private String contextMd5;

    @NotNull
    @Column(name = "size", nullable = false)
    private Double size;

    @Column(name = "is_assigned")
    private Boolean isAssigned;

    @NotNull
    @Size(max = 100)
    @Column(name = "type", length = 100, nullable = false)
    private String type;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public FileResource name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public FileResource path(String path) {
        this.path = path;
        return this;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContentType() {
        return contentType;
    }

    public FileResource contentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContextMd5() {
        return contextMd5;
    }

    public FileResource contextMd5(String contextMd5) {
        this.contextMd5 = contextMd5;
        return this;
    }

    public void setContextMd5(String contextMd5) {
        this.contextMd5 = contextMd5;
    }

    public Double getSize() {
        return size;
    }

    public FileResource size(Double size) {
        this.size = size;
        return this;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Boolean isIsAssigned() {
        return isAssigned;
    }

    public FileResource isAssigned(Boolean isAssigned) {
        this.isAssigned = isAssigned;
        return this;
    }

    public void setIsAssigned(Boolean isAssigned) {
        this.isAssigned = isAssigned;
    }

    public String getType() {
        return type;
    }

    public FileResource type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FileResource)) {
            return false;
        }
        return id != null && id.equals(((FileResource) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FileResource{" +
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
