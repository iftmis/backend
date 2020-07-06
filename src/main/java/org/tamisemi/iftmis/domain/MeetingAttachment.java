package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The MeetingAttachment entity.\n@author Chris
 */
@Entity
@Table(name = "meeting_attachments")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MeetingAttachment extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 2, max = 200)
    @Column(name = "name", length = 200, nullable = false, unique = true)
    private String name;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "meetingAttachments", allowSetters = true)
    private Meeting meeting;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "meetingAttachments", allowSetters = true)
    private FileResource attachment;

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

    public MeetingAttachment name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public MeetingAttachment meeting(Meeting meeting) {
        this.meeting = meeting;
        return this;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public FileResource getAttachment() {
        return attachment;
    }

    public MeetingAttachment attachment(FileResource fileResource) {
        this.attachment = fileResource;
        return this;
    }

    public void setAttachment(FileResource fileResource) {
        this.attachment = fileResource;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MeetingAttachment)) {
            return false;
        }
        return id != null && id.equals(((MeetingAttachment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MeetingAttachment{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
