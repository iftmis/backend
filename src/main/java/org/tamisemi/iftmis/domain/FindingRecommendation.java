package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.tamisemi.iftmis.domain.enumeration.ImplementationStatus;

/**
 * The FindingRecommendation(finding_recommendations) entity.\n@author Chris
 */
@Entity
@Table(name = "finding_recommendations")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FindingRecommendation extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "implementation_status", nullable = false)
    private ImplementationStatus implementationStatus;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "findingRecommendations", allowSetters = true)
    private Finding finding;

    @OneToMany(mappedBy = "recommendation", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("recommendation")
    private Set<FindingResponse> findingResponses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public FindingRecommendation description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImplementationStatus getImplementationStatus() {
        return implementationStatus;
    }

    public FindingRecommendation implementationStatus(ImplementationStatus implementationStatus) {
        this.implementationStatus = implementationStatus;
        return this;
    }

    public void setImplementationStatus(ImplementationStatus implementationStatus) {
        this.implementationStatus = implementationStatus;
    }

    public Finding getFinding() {
        return finding;
    }

    public FindingRecommendation finding(Finding finding) {
        this.finding = finding;
        return this;
    }

    public void setFinding(Finding finding) {
        this.finding = finding;
    }

    public Set<FindingResponse> getFindingResponses() {
        return findingResponses;
    }

    public void setFindingResponses(Set<FindingResponse> findingResponses) {
        this.findingResponses = findingResponses;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FindingRecommendation)) {
            return false;
        }
        return id != null && id.equals(((FindingRecommendation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FindingRecommendation{" +
            "id=" + id +
            ", description='" + description + '\'' +
            ", implementationStatus=" + implementationStatus +
            ", finding=" + finding +
            ", findingResponses=" + findingResponses +
            '}';
    }
}
