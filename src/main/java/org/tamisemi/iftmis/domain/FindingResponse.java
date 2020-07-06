package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.tamisemi.iftmis.domain.enumeration.ResponseType;

/**
 * The FindingResponse(inspection_responses) entity.\n@author Chris
 */
@Entity
@Table(name = "finding_responses")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FindingResponse extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "source", nullable = false)
    private ResponseType source;

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "findingResponses", allowSetters = true)
    private FindingRecommendation recommendation;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResponseType getSource() {
        return source;
    }

    public FindingResponse source(ResponseType source) {
        this.source = source;
        return this;
    }

    public void setSource(ResponseType source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public FindingResponse description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FindingRecommendation getRecommendation() {
        return recommendation;
    }

    public FindingResponse recommendation(FindingRecommendation findingRecommendation) {
        this.recommendation = findingRecommendation;
        return this;
    }

    public void setRecommendation(FindingRecommendation findingRecommendation) {
        this.recommendation = findingRecommendation;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FindingResponse)) {
            return false;
        }
        return id != null && id.equals(((FindingResponse) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FindingResponse{" +
            "id=" + getId() +
            ", source='" + getSource() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
