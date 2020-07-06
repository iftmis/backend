package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.MeetingAttachment} entity.
 */
@ApiModel(description = "The MeetingAttachment entity.\n@author Chris")
public class MeetingAttachmentDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @NotNull
    @Size(min = 2, max = 200)
    private String name;

    private Long meetingId;

    private Long attachmentId;

    private String attachmentPath;

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

    public Long getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Long meetingId) {
        this.meetingId = meetingId;
    }

    public Long getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Long fileResourceId) {
        this.attachmentId = fileResourceId;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String fileResourcePath) {
        this.attachmentPath = fileResourcePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MeetingAttachmentDTO)) {
            return false;
        }

        return id != null && id.equals(((MeetingAttachmentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MeetingAttachmentDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", meetingId=" + getMeetingId() +
            ", attachmentId=" + getAttachmentId() +
            ", attachmentPath='" + getAttachmentPath() + "'" +
            "}";
    }
}
