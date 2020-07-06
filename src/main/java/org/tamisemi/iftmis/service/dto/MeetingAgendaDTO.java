package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.MeetingAgenda} entity.
 */
@ApiModel(description = "The MeetingAgenda entity.\n@author Chris")
public class MeetingAgendaDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    private String description;

    private Long meetingId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MeetingAgendaDTO)) {
            return false;
        }

        return id != null && id.equals(((MeetingAgendaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MeetingAgendaDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", meetingId=" + getMeetingId() +
            "}";
    }
}
