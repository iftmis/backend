package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The InspectionResponseAttachment entity.\n@author Chris
 */
@ApiModel(description = "The InspectionResponseAttachment entity.\n@author Chris")
@Entity
@Table(name = "response_attachments")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ResponseAttachment extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "responseAttachments", allowSetters = true)
    private FileResource attachment;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "responseAttachments", allowSetters = true)
    private FindingResponse response;

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

    public ResponseAttachment name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileResource getAttachment() {
        return attachment;
    }

    public ResponseAttachment attachment(FileResource fileResource) {
        this.attachment = fileResource;
        return this;
    }

    public void setAttachment(FileResource fileResource) {
        this.attachment = fileResource;
    }

    public FindingResponse getResponse() {
        return response;
    }

    public ResponseAttachment response(FindingResponse findingResponse) {
        this.response = findingResponse;
        return this;
    }

    public void setResponse(FindingResponse findingResponse) {
        this.response = findingResponse;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResponseAttachment)) {
            return false;
        }
        return id != null && id.equals(((ResponseAttachment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResponseAttachment{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
