package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.ResponseAttachment} entity.
 */
@ApiModel(description = "The InspectionResponseAttachment entity.\n@author Chris")
public class ResponseAttachmentDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @NotNull
    @Size(max = 100)
    private String name;

    private Long attachmentId;

    private String attachmentPath;

    private Long responseId;

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

    public Long getResponseId() {
        return responseId;
    }

    public void setResponseId(Long findingResponseId) {
        this.responseId = findingResponseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResponseAttachmentDTO)) {
            return false;
        }

        return id != null && id.equals(((ResponseAttachmentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResponseAttachmentDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", attachmentId=" + getAttachmentId() +
            ", attachmentPath='" + getAttachmentPath() + "'" +
            ", responseId=" + getResponseId() +
            "}";
    }
}
