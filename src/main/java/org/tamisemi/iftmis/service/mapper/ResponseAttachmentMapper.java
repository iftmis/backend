package org.tamisemi.iftmis.service.mapper;

import org.mapstruct.*;
import org.tamisemi.iftmis.domain.*;
import org.tamisemi.iftmis.service.dto.ResponseAttachmentDTO;

/**
 * Mapper for the entity {@link ResponseAttachment} and its DTO {@link ResponseAttachmentDTO}.
 */
@Mapper(componentModel = "spring", uses = { FileResourceMapper.class, FindingResponseMapper.class })
public interface ResponseAttachmentMapper extends EntityMapper<ResponseAttachmentDTO, ResponseAttachment> {
    @Mapping(source = "attachment.id", target = "attachmentId")
    @Mapping(source = "attachment.path", target = "attachmentPath")
    @Mapping(source = "response.id", target = "responseId")
    ResponseAttachmentDTO toDto(ResponseAttachment responseAttachment);

    @Mapping(source = "attachmentId", target = "attachment")
    @Mapping(source = "responseId", target = "response")
    ResponseAttachment toEntity(ResponseAttachmentDTO responseAttachmentDTO);

    default ResponseAttachment fromId(Long id) {
        if (id == null) {
            return null;
        }
        ResponseAttachment responseAttachment = new ResponseAttachment();
        responseAttachment.setId(id);
        return responseAttachment;
    }
}
